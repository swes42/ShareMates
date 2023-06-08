package facades;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import errorhandling.AuthenticationException;
import errorhandling.MissingInputException;
import errorhandling.UserAlreadyExistsException;
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

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    private EntityManager getEntityManager() {
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

    public List<UserDTO> getAllUsers() {
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

    public UserDTO addUser(String username, String password) throws MissingInputException, UserAlreadyExistsException {
        EntityManager em = getEntityManager();
        User addedUser = new User(username, password);

        if (username.length() == 0 || password.length() == 0) { //Checks to see if our inputs are empty
            throw new MissingInputException("Username and/or password is missing");
        }

        try {
            em.getTransaction().begin();
            TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.roleName = :role_name", Role.class).setParameter("role_name", "user");
            List<Role> roles = query.getResultList();

            Role addUserRole;
            if (roles.isEmpty()) {
                addUserRole = new Role("user");
                em.persist(addUserRole);
            } else {
                addUserRole = roles.get(0);
            }

            TypedQuery<User> userQuery = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class).setParameter("username", username);
            List<User> users = userQuery.getResultList();
            if (!users.isEmpty()){
                throw new UserAlreadyExistsException("User already exists, please try another username");
            } 
            
            addedUser.addRole(addUserRole);
            em.persist(addedUser);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return new UserDTO(addedUser);
    }
}
