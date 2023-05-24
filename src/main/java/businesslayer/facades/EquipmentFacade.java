package businesslayer.facades;

import datalayer.dtos.EquipmentDTO;
import datalayer.entities.Equipment;
import datalayer.entities.User;
import presentationlayer.errorhandling.UserNotFound;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.NotFoundException;

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
    
    public EquipmentDTO addEquipment(String name, String description) throws NotFoundException {
        EntityManager em = getEntityManager();
        Equipment equipment;
        
        try {
            equipment = em.find(Equipment.class, name);
            
            if (equipment == null && name.length() > 0 && description.length() > 0){
                equipment = new Equipment(name, description);
                
                em.getTransaction().begin();
                em.persist(equipment);
                em.getTransaction().commit();
            } else {
                if ((name.length() == 0 || description.length() == 0)) {
                    throw new NotFoundException("No input?");
                }
            }
        } finally {
            em.close();
        }
        
        
        return new EquipmentDTO(equipment);    
    }

    public void missingInput(String name, String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public EquipmentDTO getAllEquipments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
