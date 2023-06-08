
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Selina A.S.
 */



@Entity
@Table (name = "lends")
@NamedQueries ({
    @NamedQuery(name = "Lend.deleteAllRows", query = "DELETE FROM Lend"),
    @NamedQuery(name = "Lend.findLendByUser", query = "SELECT l FROM Lend l WHERE l.user.userName LIKE :username"),
    @NamedQuery(name = "Lend.getAllActiveLends", query = "SELECT l FROM Lend l WHERE l.endDate >= CURRENT_TIMESTAMP")
        
})
public class Lend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     @JoinTable(name = "user_lends", joinColumns = {
        @JoinColumn(name = "lend_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_name", referencedColumnName = "user_name")})
  
    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "equipment_name")
    private Equipment equipment;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    private Date endDate;
    
  
    public Lend () {
    }
    
    public Lend(User user, Equipment equipment, Date date) {
        this.user = user;
        this.equipment = equipment;
        this.startDate = new Date();
        this.endDate = new Date();
    }
    
    public Lend(User user, Equipment equipment){
        this.user = user;
        this.equipment = equipment;
        this.startDate = new Date();
        this.endDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
     

}
