/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package facades;

import dtos.EquipmentDTO;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import rest.ApplicationConfig;
import utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */


@RunWith(Parameterized.class)
public class EquipmentFacadeTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    
    
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


    /**
     * Test of getEquipmentFacade method, of class EquipmentFacade.
     */
    @Test
    public void testGetEquipmentFacade() {
        System.out.println("getEquipmentFacade");
        EntityManagerFactory _emf = null;
        EquipmentFacade expResult = null;
        EquipmentFacade result = EquipmentFacade.getEquipmentFacade(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEquipment method, of class EquipmentFacade.
     */
    @Test
    public void testAddEquipment() {
        System.out.println("addEquipment");
        String name = "";
        String description = "";
        EquipmentFacade instance = null;
        EquipmentDTO expResult = null;
        EquipmentDTO result = instance.addEquipment(name, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
       return Arrays.asList(new Object[][]{
           {"HP", "256 SSD"},
           {"Dell", "i5"},
           {"Lenovo", "i3"}
       });
    }
    
    @Parameterized.Parameter(0)
    public String name;
    @Parameterized.Parameter(1)
    public String description;
    
    
}
