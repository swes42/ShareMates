package facades;

import dtos.EquipmentDTO;
import entities.Equipment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentFacade {

    private static EquipmentFacade instance;
    private static EntityManagerFactory emf;

    private EquipmentFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static EquipmentFacade getEquipmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EquipmentFacade();
        }
        return instance;
    }

    public EquipmentDTO addEquipment(String equipment_name, String equipment_description) throws NotFoundException {
        EntityManager em = getEntityManager();
        Equipment equipment;

        try {

            if (equipment_name.length() > 0 && equipment_description.length() > 0) {
                equipment = new Equipment(equipment_name, equipment_description);

                em.getTransaction().begin();
                em.persist(equipment);
                em.getTransaction().commit();
            } else {
                throw new NotFoundException("No input?");
            }
        } finally {
            em.close();
        }

        return new EquipmentDTO(equipment);
    }

    public List<EquipmentDTO> getAllEquipments() {
        EntityManager em = emf.createEntityManager();
        List<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("SELECT e FROM Equipment e", Equipment.class
            );
            List<Equipment> equipments = query.getResultList();
            for (Equipment equipment : equipments) {
                equipmentDTOS.add(new EquipmentDTO(equipment));
            }
        } finally {
            em.close();
        }
        return equipmentDTOS;
    }
}
