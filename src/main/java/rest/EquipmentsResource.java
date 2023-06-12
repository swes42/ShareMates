    
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EquipmentDTO;
import errorhandling.MissingInput;
import facades.EquipmentFacade;
import entities.Equipment;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Selina A.S.
 */

//startcode database

@Path("equipment")
public class EquipmentsResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final EquipmentFacade FACADE =  EquipmentFacade.getEquipmentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;
        
        
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"equipment endpoint\"}";
    }
    
    //http://localhost:8080/ShareMates/api/equipment/all
    @Path("allEquipments")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allEquipments() {
        List<EquipmentDTO> listDTO = FACADE.getAllEquipments();
        return GSON.toJson(listDTO);
        }

    @POST
    //@RolesAllowed({"user"}) //admin senere
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("addEquipment")
    public String addEquipment(String equipment) throws MissingInput {
        EquipmentDTO equipmentDTO = GSON.fromJson(equipment, EquipmentDTO.class);
        EquipmentDTO addEquipment = FACADE.addEquipment(equipmentDTO.getEquipmentName(), equipmentDTO.getEquipmentDescription());
        return GSON.toJson(addEquipment);
    } 
    
    
}
