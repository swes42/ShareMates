/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package presentationlayer.rest;

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
public class UserResourceIT {
    
    public UserResourceIT() {
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
     * Test of demo method, of class UserResource.
     */
    @Test
    public void testDemo() {
        System.out.println("demo");
        UserResource instance = new UserResource();
        String expResult = "";
        String result = instance.demo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserResource.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserResource instance = new UserResource();
        String expResult = "";
        String result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UserResource.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        String user = "";
        UserResource instance = new UserResource();
        String expResult = "";
        String result = instance.addUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
