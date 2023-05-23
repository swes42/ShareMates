
package datalayer.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Selina A.S.
 */

@Entity
@Table (name = "users")
@NamedQueries({
    @NamedQuery(name = "User.deleteAllRows", query = "DELETE from User"),
    @NamedQuery(name = "User.getByUsername", query = "SELECT u from User u WHERE u.username LIKE :username"),
    @NamedQuery(name = "User.getAllRows", query = "SELECT u from User u")
     
})


public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "username", length = 50)
    private String username; 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 8, max = 100)
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Equipment> equipments = new ArrayList();
    
    public User(){
    }
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Equipment equipment) {
        if (equipment != null) {
            equipments.add(equipment);
        }
    }  
}
