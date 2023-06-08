package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Selina A.S.
 */

@Entity
@Table (name = "equipments")
@NamedQueries ({
    @NamedQuery(name = "Equipment.deleteAllRows", query = "DELETE from Equipment"),
    @NamedQuery(name = "Equipment.getAllRows", query = "SELECT e from Equipment e")
})

public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(name = "equipment_name", length = 300)
    private String equipment_name;
    
    @NotNull
    @Column (name = "equipment_description", length = 250)
    private String equipment_description;
    
   
    public Equipment() {
    }  
    

    public Equipment(String equipment_name, String equipment_description) {
        this.equipment_name = equipment_name;
        this.equipment_description = equipment_description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipment_name;
    }

    public void setEquipmentName(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipmentDescription() {
        return equipment_description;
    }

    public void setEquipmentDescription(String equipment_description) {
        this.equipment_description = equipment_description;
    }

   
}

