package rest;

import dtos.EquipmentDTO;
import entities.Equipment;
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
            
            e = new Equipment("LENOVO", "D24 FULL-45 23,8\" HD SKÆRM");
            e1 = new Equipment("ACER", "TN Full HD (1920 x 1080)");
            
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
    
    @Test
    public void getAllEquipments() throws Exception {
        System.out.println("Testing getAllEquipments:");
        
        List<EquipmentDTO> equDTO;
        equDTO = given()
                .contentType("application/json")
                .when()
                .get("/equipment/allEquipments")
                .then()
                .extract().body().jsonPath().getList("allEquipments", EquipmentDTO.class);
        
        List<String> resultList = new ArrayList();
        for(EquipmentDTO e : equDTO) {
            resultList.add(e.getEquipmentName());
        }
        
        EquipmentDTO eDTO = new EquipmentDTO(e);
        EquipmentDTO e1DTO = new EquipmentDTO(e1);
        
        assertThat(resultList, containsInAnyOrder(eDTO.getEquipmentName(), e1DTO.getEquipmentName()));
    }
}
