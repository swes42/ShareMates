
package dtos;

import entities.Lend;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Selina A.S.
 */


public class LendDTO {
    
    private Long id;
    private String username;
    private String equipment;
    private Date startDate;
    private Date endDate;
    private String status;
    List<LendDTO> allLends = new ArrayList();

    public LendDTO(Long id, String username, String equipment, Date startDate, Date endDate) {
        this.id = id;
        this.username = username;
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public LendDTO(Lend lend) {
        this.id = lend.getId();
        this.username = lend.getUser().getUsername();
        this.equipment = lend.getEquipment().getEquipmentName();
        this.startDate = lend.getStartDate();
        this.endDate = lend.getEndDate();
    }
    
    public LendDTO(List<Lend> lendList) {
        lendList.forEach((l) -> {
        allLends.add(new LendDTO(l));
    });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public List<LendDTO> getAllLends(){
        return allLends;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
    
    
}
