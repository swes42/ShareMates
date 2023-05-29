
package facades;

import dtos.EquipmentDTO;
import entities.Equipment;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import rest.ApplicationConfig;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentFacadeTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Equipment e, e1;
    private static EquipmentFacade equipmentFacade;


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
            
            e = new Equipment("FacadeTest1", "256 gb ssd");
            e1 = new Equipment("FacadeTest2", "8 RAM");
            
            em.persist(e);
            em.persist(e1);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testAddEquipment() {
        // Test af addEquipment-metoden i facade-klassen
        
        // Opsætning af testdata og inputværdier
        String name = "HP";
        String description = "core i7";
        
        // Udførelse af metoden
        EquipmentDTO result = equipmentFacade.addEquipment(name, description);
        
        // Forventet resultat
        EquipmentDTO expectedResult = new EquipmentDTO(name, description);
        
        // Sammenligning af resultatet med det forventede resultat
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getDescription(), result.getDescription());
    }
      /**
     * Test of addEquipment method, of class EquipmentFacade.
     
     * 
     * @Test
    public void testGetEquipmentFacade() {
        String name = "[]";
        String description = "[]";
        EquipmentFacade instance = EquipmentFacade.getEquipmentFacade(emf);
        EquipmentDTO expResult = new EquipmentDTO(name, description);
        EquipmentDTO result = instance.addEquipment(name, description);
        assertEquals(expResult, result);
    }

  
    @Test
    public void testAddEquipment() {
        System.out.println("addEquipment");       
        String name = "";
        String description = "";
        EquipmentFacade instance = EquipmentFacade.getEquipmentFacade(emf);
        EquipmentDTO expResult = null;
        EquipmentDTO result = instance.addEquipment(name, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
