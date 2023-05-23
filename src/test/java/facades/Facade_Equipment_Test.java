package facades;

import businesslayer.facades.EquipmentFacade;
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

    public Facade_Equipment_Test() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EquipmentFacade.getEquipmentFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Equipment.deleteAllRows").executeUpdate();
            em.persist(new Equipment("Some txt", "More text"));
            em.persist(new Equipment("aaa", "bbb"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

   
}
