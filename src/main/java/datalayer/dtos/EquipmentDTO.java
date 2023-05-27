package datalayer.dtos;

import businesslayer.entities.Equipment;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentDTO {
    
    private String name;
    private String description;
    
    public EquipmentDTO (Equipment equipment){
        this.name = equipment.getName();
        this.description = equipment.getDescription();
    }
    
    public EquipmentDTO(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    

    
}