package datalayer.dtos;

import datalayer.entities.Equipment;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentDTO {
    
    private String name;
    private String description;
    private String username; 

    public EquipmentDTO(Equipment equipment){
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
    

    
}