package dtos;

import entities.Equipment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Selina A.S.
 */
public class EquipmentDTO {
    
    private long id;
    private String str1;
    private String str2;

    public EquipmentDTO(String dummyStr1, String dummyStr2) {
        this.str1 = dummyStr1;
        this.str2 = dummyStr2;
    }
    
    public static List<EquipmentDTO> getDtos(List<Equipment> rms){
        List<EquipmentDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new EquipmentDTO(rm)));
        return rmdtos;
    }


    public EquipmentDTO(Equipment ep) {
        this.id = ep.getId();
        this.str1 = ep.getDummyStr1();
        this.str2 = ep.getDummyStr2();
    }

    public String getDummyStr1() {
        return str1;
    }

    public void setDummyStr1(String dummyStr1) {
        this.str1 = dummyStr1;
    }

    public String getDummyStr2() {
        return str2;
    }

    public void setDummyStr2(String dummyStr2) {
        this.str2 = dummyStr2;
    }

    @Override
    public String toString() {
        return "EquipmentDTO{" + "id=" + id + ", str1=" + str1 + ", str2=" + str2 + '}';
    } 
}