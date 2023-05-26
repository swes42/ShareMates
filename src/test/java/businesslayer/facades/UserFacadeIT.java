/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package businesslayer.facades;

import datalayer.dtos.UserDTO;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Selina A.S.
 */
public class UserFacadeIT {
    
    public UserFacadeIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserFacade method, of class UserFacade.
     */
    @Test
    public void testGetUserFacade() {
        System.out.println("getUserFacade");
        EntityManagerFactory _emf = null;
        UserFacade expResult = null;
        UserFacade result = UserFacade.getUserFacade(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UserFacade.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String username = "";
        String password = "";
        UserFacade instance = null;
        UserDTO expResult = null;
        UserDTO result = instance.addUser(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
