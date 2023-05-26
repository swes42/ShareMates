/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package businesslayer.facades;

import datalayer.dtos.EquipmentDTO;
import javax.persistence.EntityManagerFactory;
import junit.framework.TestCase;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentFacadeTest extends TestCase {
    
    public EquipmentFacadeTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getEquipmentFacade method, of class EquipmentFacade.
     */
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

    /**
     * Test of missingInput method, of class EquipmentFacade.
     */
    public void testMissingInput() {
        System.out.println("missingInput");
        String name = "";
        String description = "";
        EquipmentFacade instance = null;
        instance.missingInput(name, description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEquipments method, of class EquipmentFacade.
     */
    public void testGetAllEquipments() {
        System.out.println("getAllEquipments");
        EquipmentFacade instance = null;
        EquipmentDTO expResult = null;
        EquipmentDTO result = instance.getAllEquipments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
