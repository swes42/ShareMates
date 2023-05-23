
package datalayer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datalayer.dtos.EquipmentDTO;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Selina A.S.
 */
public class Utility {
    private static Gson gson = new GsonBuilder().create();
    
    public static void printAllProperties() {
            Properties prop = System.getProperties();
            Set<Object> keySet = prop.keySet();
            for (Object obj : keySet) {
                    System.out.println("System Property: {" 
                                    + obj.toString() + "," 
                                    + System.getProperty(obj.toString()) + "}");
            }
    }
    
    public static EquipmentDTO json2DTO(String json) throws UnsupportedEncodingException{
            return gson.fromJson(new String(json.getBytes("UTF8")), EquipmentDTO.class);
    }
    
    public static String DTO2json(EquipmentDTO epDTO){
        return gson.toJson(epDTO, EquipmentDTO.class);
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
//        printAllProperties();
        
        //Test json2DTO and back again
        String str2 = "{'id':1, 'str1':'Dette er den første tekst', 'str2':'Her er den anden'}";
        EquipmentDTO epDTO = json2DTO(str2);
        System.out.println(epDTO);
        
        String backAgain = DTO2json(epDTO);
        System.out.println(backAgain);
    }

}