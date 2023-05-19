
package facades;

import dtos.EquipmentDTO;
import entities.Equipment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */
public class Facade_Equipment {
    private static Facade_Equipment instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Facade_Equipment() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade_Equipment getFacade_Equipment(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade_Equipment();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EquipmentDTO create(EquipmentDTO ep){
        Equipment eqp = new Equipment(ep.getDummyStr1(), ep.getDummyStr2());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(eqp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EquipmentDTO(eqp);
    }
    
    
    public EquipmentDTO getEquipmentById(long id){
        EntityManager em = emf.createEntityManager();
        return new EquipmentDTO(em.find(Equipment.class, id));
    }
    
    //TODO Remove/Change this before use
    public long getEquipmentCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long equipmentCount = (long)em.createQuery("SELECT COUNT(e) FROM Equipment e").getSingleResult();
            return equipmentCount;
        }finally{  
            em.close();
        }
    }
    
    public List<EquipmentDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Equipment> query = em.createQuery("SELECT e FROM Equipment e", Equipment.class);
        List<Equipment> eps = query.getResultList();
        return EquipmentDTO.getDtos(eps);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        Facade_Equipment fe = getFacade_Equipment(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }
}
