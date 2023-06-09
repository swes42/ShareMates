
package facades;

import dtos.LendDTO;
import entities.Equipment;
import entities.Lend;
import entities.User;
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
public class LendFacade {

    private static LendFacade instance;
    private static EntityManagerFactory emf;
    private EntityManager entityManager;

    LendFacade() {

    }

    public static LendFacade getLendFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LendFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    //Bruges til facade testen...
    public LendFacade(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<LendDTO> getAllLends() {
        EntityManager em = emf.createEntityManager();
        List<LendDTO> lendDTOS = new ArrayList<>();

        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("SELECT l FROM Lend l", Lend.class);
            List<Lend> lends = query.getResultList();

            for (Lend lend : lends) {
                lendDTOS.add(new LendDTO(lend));
            }
        } finally {
            em.close();
        }
        return lendDTOS;
    }

    public LendDTO createLend(String username, String equipment_name) throws NotFoundException {
        EntityManager em = getEntityManager();
        Equipment e;
        User u;

        try {
            em.getTransaction().begin();
            TypedQuery<User> userquery = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class)
                    .setParameter("username", username);
            List<User> users = userquery.getResultList();
            if (users.isEmpty()) {
                throw new NotFoundException("User not found");
            }
            u = users.get(0);

            TypedQuery<Equipment> query = em.createQuery("SELECT e FROM Equipment e WHERE e.equipment_name = :equipment_name", Equipment.class)
                    .setParameter("equipment_name", equipment_name);
            List<Equipment> equipments = query.getResultList();

            if (equipments.isEmpty()) {
                throw new NotFoundException("Equipment not found");
            }
            e = equipments.get(0);
            
            Lend lend = new Lend(u, e);
            em.persist(lend);
            em.getTransaction().commit();
            return new LendDTO(lend);

        } finally {

            em.close();
        }

    }
}
