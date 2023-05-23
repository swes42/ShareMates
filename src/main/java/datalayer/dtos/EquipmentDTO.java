package datalayer.dtos;

import datalayer.entities.Equipment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentDTO {
    
    private int id; 
    private String name;
    private String description;
    private String username; 

    public EquipmentDTO(int e_id, String name, String description, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.username = username; 
    }
    
    public EquipmentDTO(Equipment equipment){
        this.id = id;
        this.name = name;
        this.description = description;
        this.username = username;
    }
    
    public EquipmentDTO(String name, String description){
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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