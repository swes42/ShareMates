
package facades;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import errorhandling.AuthenticationException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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

public User getVeryfiedUser(String userName, String password) throws AuthenticationException {
    EntityManager em = emf.createEntityManager();
    User user;
    
    try {
        user = em.find(User.class, userName);
        if (user == null && !user.verifyPassword(password)) {
            throw new AuthenticationException("Username and/or password is invalid");            
        }
    } finally {
        em.close();
    }
    return user;
}

public List<UserDTO> getAllUsers(){
    EntityManager em = emf.createEntityManager();
    List<UserDTO> userDTOS = new ArrayList<>();
    
    try {
        em.getTransaction().begin();
        TypedQuery query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        
        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }
    } finally {
        em.close();
    }
    return userDTOS;
}

public UserDTO addUser(String userName, String userPass) throws NotFoundException {
    EntityManager em = getEntityManager();
    User user;
    
    try {
        user = em.find(User.class, userName);
    
        if (user == null && userName.length() > 0 && userPass.length()> 0){
        user = new User(userName, userPass);
        //Role addRoleUser = em.find(Role.class, "user");
        //user.addRole(addRoleUser);
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    } else {
            if ((userName.length() == 0 || userPass.length() == 0)) {
                throw new NotFoundException("No input?");
            }
            if (user.getUsername().equalsIgnoreCase(userName)) {
                throw new NotFoundException("User already exist");
            }
        }
        
    } finally {
        em.close();
    }
    return new UserDTO(user);
    }
}