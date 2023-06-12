package facades;

import dtos.EquipmentDTO;
import entities.Equipment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */


//Startcode_test

public class EquipmentFacadeTest {

    private static EntityManagerFactory emf;
    private static EquipmentFacade facade;
    private static Equipment e1, e2;
            

    public EquipmentFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EquipmentFacade.getEquipmentFacade(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        e1 = new Equipment("EquipmentFacadeTest", "EquipmentFacadeBeskrivelse");
        e2 = new Equipment("EquipmentFacadeTest1", "EquipmentFacadeBeskrivelse1");
        
        
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Equipment").executeUpdate();
            em.persist(e1);
            em.persist(e2);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
    
    @Test
    public void getAllEquipments(){
        List<EquipmentDTO> all = facade.getAllEquipments();
        assertEquals(all.size(), 2);
    }

   
}
