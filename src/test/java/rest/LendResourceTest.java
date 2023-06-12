package rest;

import entities.Equipment;
import entities.Lend;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */


public class LendResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static User u;
    private static Role r;
    private static Lend l;
    private static Equipment e;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
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

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Lend").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.createQuery("delete from Equipment").executeUpdate();
            
            
            r = new Role("user");
            u = new User("Alison", "Password");
            e = new Equipment("Macbook", "16 gb ram");
            u.addRole(r);
            l = new Lend(u, e);
            
            em.persist(r);
            em.persist(u);
            em.persist(e);
            em.persist(l);
                  
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

     @Test
    public void testServerIsUp() {
        given().when().get("/lend").then().statusCode(200);
    }
    
    @Test
    public void testCreateLend(){
        
    }
    
    
    
    @Test
    public void testGetAllLends() {
        Response response = given()
                .contentType("application/json")
                .get("/lend/allLends");

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        response.then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("[0]", equalTo(1));

    }
    
    

}
