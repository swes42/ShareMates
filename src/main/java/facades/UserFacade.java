
package facades;

import dtos.UserDTO;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Selina A.S.
 */
public class UserFacade {
    
    private static UserFacade instance;
    private static EntityManagerFactory emf;
    
    private UserFacade(){}
    
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

/* Skal skrive denne metode, når jeg har lavet verify metode i USer. 
public User getVeryfiedUser(String username, String password){
    EntityManager em = getEntityManager();
    User user;
    
    try {
        user = em.find(User.class, username);
        if (user != null && user.verifyPassword(password) {
            
        })
    }
}*/

public UserDTO addUser(String username, String password) throws NotFoundException {
    EntityManager em = getEntityManager();
    User user;
    
    try {
        user = em.find(User.class, username);
        
        if (user == null && username.length() > 0 && password.length() > 0) {
            user = new User(username, password);
            //Role addRoleUser = em.find(Role.class, "user";
            //user.addRole(addRoleUser); 
            
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } else {
            if ((username.length() == 0 || password.length() == 0)) {
                throw new NotFoundException("No input?");
            } 
            if (user.getUsername().equalsIgnoreCase(username)) {
                throw new NotFoundException("User already has an account");
                }
            }
    } finally {
        em.close();
        }
    return new UserDTO(user);   
    }
}