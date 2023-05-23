package datalayer.entities;

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
@Table (name = "equipment")
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
    @Column(name = "name", length = 300)
    private String e_name;
    
    @NotNull
    @Column (name = "description", length = 250)
    private String e_desc;
    
    @JoinColumn(name = "username")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    
    
    public Equipment() {
    }  
    

    public Equipment(String e_name, String e_desc, User user) {
        this.e_name = e_name;
        this.e_desc = e_desc;
        this.user = user;
    }
    
    public Equipment(String e_name, String e_desc){
        this.e_name = e_name;
        this.e_desc = e_desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_desc() {
        return e_desc;
    }

    public void setE_desc(String e_desc) {
        this.e_desc = e_desc;
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

