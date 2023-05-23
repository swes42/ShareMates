
package presentationlayer.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datalayer.dtos.EquipmentDTO;
import presentationlayer.errorhandling.MissingInput;
import businesslayer.facades.EquipmentFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import datalayer.utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */

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
        return "{\"msg\":\"Equipments Resource\"}";
    }
    
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String AllEquipments() {
        EquipmentDTO equipments = FACADE.getAllEquipments();
        return GSON.toJson(equipments);
    }

    @POST
    @RolesAllowed({"user"}) //admin senere
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public String addEquipment(String equipment) throws MissingInput {
        EquipmentDTO eDto = GSON.fromJson(equipment, EquipmentDTO.class);
        EquipmentDTO eDtoAdded = FACADE.addEquipment(eDto.getName(), eDto.getDescription(), eDto.getUsername());
        return GSON.toJson(eDtoAdded);
    } 
    
    
}
