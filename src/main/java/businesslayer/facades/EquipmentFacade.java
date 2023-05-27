package businesslayer.facades;

import datalayer.dtos.EquipmentDTO;
import businesslayer.entities.Equipment;
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
    
/**
 *
 * @param _emf
 * @return the instance of this facade.
 */
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }  
    
    public static EquipmentFacade getEquipmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EquipmentFacade();
        }
        return instance;
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
}
