
package facades;

import businesslayer.facades.UserFacade;
import datalayer.entities.User;
import datalayer.utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Selina A.S.
 */
public class UserFacadeTest {
    private static EntityManagerFactory emf;
    private static UserFacade facade;
    
    public UserFacadeTest(){
        
    }
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = UserFacade.getUserFacade(emf);
    }
    
    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.persist(new User("testUser", "testPassword"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
