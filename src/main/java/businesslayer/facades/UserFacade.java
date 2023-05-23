
package businesslayer.facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Selina A.S.
 */
public class UserFacade {
    
    private static UserFacade instance;
    private static EntityManagerFactory emf;
    
    private UserFacade(){
    }
    
/**
 *
 * @param _emf
 * @return the instance of this facade.
 */
    
private EntityManager getEntityManager(){
    return emf.createEntityManager();
}

public static UserFacade getUserFacade(EntityManagerFactory _emf) {
    if (instance == null) {
        emf = _emf;
        instance = new UserFacade();
        }
        return instance;
    }


//tilføj exception

/*

public UserDTO addUser(String username, String password) {
    EntityManager em = getEntityManager();
    User userAdded = new User(username, password);
     
    if (username.length() == 0 || password.length() == 0){
        throw new MissingInput("Username missing");
        
    } 
    try {
        em.getTransaction().begin();
        TypedQuery tQuery = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).setParameter("username", username);
        List<User> users = tQuery.getResultList();
        
        if (users.size() > 0) {
            throw new UserAlreadyExistsException("Ooops. The username is already taken.");
        } else {
            userAdded(em.createQuery("SELECT u from User u", User.class));
            
        }
                
    
    }
    
}  */

    
}
