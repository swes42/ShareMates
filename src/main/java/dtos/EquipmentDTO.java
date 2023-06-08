package dtos;

import entities.Equipment;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentDTO {
    
    private String e_name;
    private String e_description;
    
    public EquipmentDTO (Equipment equipment){
        this.e_name = equipment.getEquipmentName();
        this.e_description = equipment.getEquipmentName();
    }
    
    public EquipmentDTO(String e_name, String e_description){
        this.e_name = e_name;
        this.e_description = e_description;
    }

    public String getEquipmentName() {
        return e_name;
    }

    public void setEquipmentName(String e_name) {
        this.e_name = e_name;
    }

    public String getEquipmentDescription() {
        return e_description;
    }

    public void setEquipmentDescription(String e_description) {
        this.e_description = e_description;
    }
    
    
    
    
    

    
}