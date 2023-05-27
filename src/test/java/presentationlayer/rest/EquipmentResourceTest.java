package presentationlayer.rest;

import businesslayer.entities.Equipment;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
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
import datalayer.utils.EMF_Creator;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Selina A.S.
 */

//Startcode_test database


public class EquipmentResourceTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Equipment e, e1;

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

    //Sætter databasen op med 2 test equipments. 
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Equipment").executeUpdate();
            
            e = new Equipment("Macbook", "256 gb ssd");
            e1 = new Equipment("Lenovo", "8 RAM");
            
            em.persist(e);
            em.persist(e1);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/equipment").then().statusCode(200);
    }
    
    //JUnit test, bruger assertions til at sammenligne den forventede værdi med den faktiske værdi.
    @Test
    public void testAllEquipments() {
        System.out.println("allEquipments");
        EquipmentsResource instance = new EquipmentsResource();
        String expResult = "[2]";
        String result = instance.allEquipments();
        assertEquals(expResult, result);
    }

    
    @Test
    public void testAPIgetAll() throws Exception {
        Response response = given()
        .contentType("application/json")
        .get("/equipment/all");
        
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
    
        response.then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("[0]", equalTo(2));
}
    
}
