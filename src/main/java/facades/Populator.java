package facades;

import dtos.EquipmentDTO;
import entities.Equipment;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha + Selina A.S.
 */


public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        Facade_Equipment fe = Facade_Equipment.getFacade_Equipment(emf);
        fe.create(new EquipmentDTO(new Equipment("Computer", "Stationær")));
        fe.create(new EquipmentDTO(new Equipment("Computer", "Bærbar")));
        fe.create(new EquipmentDTO(new Equipment("Mus", "Trådløs")));
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}

