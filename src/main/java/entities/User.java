
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Selina A.S.
 */

@Entity
@Table (name = "users")
@NamedQueries({
    @NamedQuery(name = "User.deleteAllRows", query = "DELETE from User"),
    @NamedQuery(name = "User.getByUsername", query = "SELECT u from User u WHERE u.userName LIKE :userName"),
    @NamedQuery(name = "User.getAllRows", query = "SELECT u from User u")
     
})


public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 50)
    private String userName; 
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 8, max = 100)
    @Column(name = "user_pass")
    private String userPass;
    
    @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    
    @ManyToMany
    private List<Role> listOfRoles = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Equipment> equipments = new ArrayList();
    
      
    //der skal altiv være en no-argument constructor i entity-klasserne.
    //nødvendig for at den kan lave transformationerne mellem persistence layer og data layer. 
    public User(){
    }
    
    public User(String userName, String userPass){
        this.userName = userName;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt(12));
        
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return userPass;
    }

    public void setPassword(String userPass) {
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt(12));
    }
    
     public boolean verifyPassword(String pw) {
        return (BCrypt.checkpw(pw, this.userPass));
    }

    public void addRole(Role userRole) {
        listOfRoles.add(userRole);
    }
    
    public List<Role> getListOfRoles() {
        return listOfRoles;
    }
    
    public void setListOfRoles(List<Role> listOfRoles) {
        this.listOfRoles = listOfRoles;
    }
    
    public List<String> getRolesAsString(){
        if (listOfRoles.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        listOfRoles.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
    });
        return rolesAsStrings;
    }
    
    public void addEquipment(Equipment equipment){
        equipments.add(equipment);
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
