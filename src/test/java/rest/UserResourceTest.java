package rest;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 *
 * @author Selina A.S.
 */
//Startcode_test database
public class UserResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static User u, u1;
    private static Role r, r1;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            
            r = new Role("user");
            r1 = new Role("admin");
            u = new User("TestBruger1", "TestPassword");
            u1 = new User("TestBruger2", "TestPassword");
            
            u.addRole(r);
            u.addRole(r1);
            

            em.persist(r);
            em.persist(r1);
            em.persist(u);
            em.persist(u1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/user").then().statusCode(200);
    }

    @Test
    public void getAllUsers() throws Exception {
        System.out.println("Testing getAllUser:");
        
        List<UserDTO> usersDTO; 
        usersDTO = given()
                .contentType("application/json")
                .when()
                .get("/user/all")
                .then()
                .extract().body().jsonPath().getList("allUsers", UserDTO.class);
        
        List<String> resultList = new ArrayList();
        for (UserDTO u : usersDTO){
            resultList.add(u.getUsername());
        }
        
        UserDTO uDTO = new UserDTO(u);
        UserDTO u1DTO = new UserDTO(u1);
        
        assertThat(resultList, containsInAnyOrder(uDTO.getUsername(), u1DTO.getUsername()));
    }

}
