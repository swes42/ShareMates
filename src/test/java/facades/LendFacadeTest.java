/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package facades;

import dtos.LendDTO;
import entities.Equipment;
import entities.Lend;
import entities.Role;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import utils.EMF_Creator;



/**
 *
 * @author Selina A.S.
 */

public class LendFacadeTest {
    private static EntityManagerFactory emf;
    private static LendFacade facade;
    private static Lend lend;

    public LendFacadeTest() {
    }
    
     @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = LendFacade.getLendFacade(emf);
        
    }
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        lend = new Lend(); 
        
        User user = new User("Alison", "Password");
        Role role = new Role("user");
        user.getListOfRoles().add(role);
        
        Equipment equipment = new Equipment("Macbook", "2023");
        
        lend.setUser(user);
        lend.setEquipment(equipment);
        
        
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Lend").executeUpdate();
            em.persist(lend);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLendFacade method, of class LendFacade.
     */
    @Test
    public void testGetLendFacade() {
        System.out.println("getLendFacade");
        EntityManagerFactory _emf = null;
        LendFacade expResult = null;
        LendFacade result = LendFacade.getLendFacade(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAllLends method, of class LendFacade.
     */
    @Test
    public void testGetAllLends() {
        System.out.println("getAllLends");
        LendFacade instance = null;
        List<LendDTO> expResult = null;
        List<LendDTO> result = instance.getAllLends();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of createLend method, of class LendFacade.
     */
    @Test
    public void testCreateLend() {
        System.out.println("createLend");
        String username = "";
        String equipment_name = "";
        LendFacade instance = null;
        LendDTO expResult = null;
        LendDTO result = instance.createLend(username, equipment_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
