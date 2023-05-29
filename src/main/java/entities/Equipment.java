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
    private int id;
    
    @Id
    @NotNull
    @Column(name = "name", length = 300)
    private String name;
    
    @NotNull
    @Column (name = "description", length = 250)
    private String description;
    
    @JoinColumn(name = "username")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    
    
    public Equipment() {
    }  
    

    public Equipment(String name, String description) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user != null) {
            this.user = user;
            user.setEquipments(this);
        } else {
            this.user = null;
        }
    }
}

