package businesslayer.facades;

import datalayer.dtos.EquipmentDTO;
import datalayer.entities.Equipment;
import datalayer.entities.User;
import presentationlayer.errorhandling.UserNotFound;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentFacade {
    
    private static EquipmentFacade instance;
    private static EntityManagerFactory emf;
    
    private EquipmentFacade() {}
    
    public static EquipmentFacade getEquipmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EquipmentFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public EquipmentDTO addEquipment(String name, String description, String username) {
        EntityManager em = getEntityManager();
        missingInput(name, description);
        User user = null;
        Equipment equipment = null;
        
        try {
            em.getTransaction().begin();
            user = getUserFromDatabase(em, username);
            equipment = new Equipment(name, description, user);
            
            em.persist(equipment);
            em.getTransaction().commit();
            
        } catch (UserNotFound unf){
            Logger.getLogger(EquipmentFacade.class.getName()).log(Level.SEVERE, null, unf);
        } finally {
            em.close();
        }
        return new EquipmentDTO(equipment);    
    }

    public void missingInput(String name, String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public User getUserFromDatabase(EntityManager em, String username)throws UserNotFound {
        User user = em.find(User.class, username);
        if (user == null) {
            throw new UserNotFound(String.format("User with provided username not found", username));
        }else {
            return user; 
            
        }
    }

    public EquipmentDTO getAllEquipments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
