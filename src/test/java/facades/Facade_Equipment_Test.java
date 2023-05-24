package facades;

import businesslayer.facades.EquipmentFacade;
import datalayer.dtos.EquipmentDTO;
import datalayer.utils.EMF_Creator;
import datalayer.entities.Equipment;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

//Uncomment the line below, to temporarily disable this test


public class Facade_Equipment_Test {

    private static EntityManagerFactory emf;
    private static EquipmentFacade facade;
    private static Equipment e;
    private static EquipmentDTO eDTO;


    public Facade_Equipment_Test() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EquipmentFacade.getEquipmentFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Equipment.deleteAllRows").executeUpdate();
            em.getTransaction();
        } finally {
            em.close();
        }

    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        e = new Equipment("EquipmentFacade", "FacadeDescription");
        
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            eDTO = new EquipmentDTO(e);
                        
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        e = null;
        eDTO = null;
        
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Equipment.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

   
}
