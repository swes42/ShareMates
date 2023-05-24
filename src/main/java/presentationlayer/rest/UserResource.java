
package presentationlayer.rest;

import businesslayer.facades.UserFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datalayer.dtos.UserDTO;
import datalayer.entities.User;
import datalayer.utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Selina A.S.
 */


@Path("info")
public class UserResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade FACADE =  UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   

    
    
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("all")
 public String getAllUsers(){
     EntityManager em = EMF.createEntityManager();
     try {
         TypedQuery<User> query = em.createQuery ("SELECT u FROM User u", datalayer.entities.User.class);
         List<User> users = query.getResultList();
         return "[" + users.size() + "]";
     } finally {
         em.close();
     }
 }
 
 @POST
 @Produces(MediaType.APPLICATION_JSON)
 @Consumes(MediaType.APPLICATION_JSON)
 public String addUser(String user) throws Exception {
     UserDTO userDTO = GSON.fromJson(user, UserDTO.class);
     UserDTO addUser = FACADE.addUser(userDTO.getUsername(), userDTO.getPassword());
     return GSON.toJson(addUser);
 }
}
