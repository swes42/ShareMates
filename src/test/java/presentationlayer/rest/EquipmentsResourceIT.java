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
public class EquipmentsResourceIT {
    
    public EquipmentsResourceIT() {
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
     * Test of demo method, of class EquipmentsResource.
     */
    @Test
    public void testDemo() {
        System.out.println("demo");
        EquipmentsResource instance = new EquipmentsResource();
        String expResult = "";
        String result = instance.demo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allEquipments method, of class EquipmentsResource.
     */
    @Test
    public void testAllEquipments() {
        System.out.println("allEquipments");
        EquipmentsResource instance = new EquipmentsResource();
        String expResult = "";
        String result = instance.allEquipments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEquipment method, of class EquipmentsResource.
     */
    @Test
    public void testAddEquipment() throws Exception {
        System.out.println("addEquipment");
        String equipment = "";
        EquipmentsResource instance = new EquipmentsResource();
        String expResult = "";
        String result = instance.addEquipment(equipment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
