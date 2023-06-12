/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package facades;

import dtos.UserDTO;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;
    private static User u1, u2; 

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        u1 = new User("UserFacadeTest1", "FacadeTestPassword");
        u2 = new User("UserFacadeTest2", "FacadeTestPassword");

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void getAllUsers(){
        List<UserDTO> all = facade.getAllUsers();
        assertEquals(all.size(), 2);
    }
}
