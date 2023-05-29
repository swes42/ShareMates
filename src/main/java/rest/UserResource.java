
package rest;

import facades.UserFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.User;
import errorhandling.MissingInput;
import utils.EMF_Creator;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Selina A.S.
 */

//http://localhost:8080/ShareMates/api/info/
@Path("user")
public class UserResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade FACADE =  UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private UriInfo context;
    
@Context
SecurityContext securityContext;

   
//http://localhost:8080/ShareMates/api/user
@GET
@Produces({MediaType.APPLICATION_JSON})
public String demo() {
    return "{\"msg\":\"user endpoint\"}";
}
    

    
 //http://localhost:8080/ShareMates/api/user/all   
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("all")
 public String getAllUsers(){
     EntityManager em = EMF.createEntityManager();
     try {
         TypedQuery<User> query = em.createQuery ("SELECT u FROM User u", entities.User.class);
         List<User> users = query.getResultList();
         return "[" + users.size() + "]";
     } finally {
         em.close();
     }
 }
 

//In Postman, http://localhost:8080/ShareMates/api/user/helloUser
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("helloUser")
 @RolesAllowed("user")
 public String sayHelloUser(){
     String helloUser = securityContext.getUserPrincipal().getName();
     return "{\"msg\": \"Hello" + helloUser + "\"}";
 }
 
 
 //In Postman, http://localhost:8080/ShareMates/api/user/helloAdmin
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("helloAdmin")
 @RolesAllowed("admin")
 public String sayHelloAdmin(){
     String helloAdmin = securityContext.getUserPrincipal().getName();
     return "{\"msg\": \"Hello" + helloAdmin + "\"}";
 }
 
 //In Postman, http://localhost:8080/ShareMates/api/user/add
 @POST
 @Produces(MediaType.APPLICATION_JSON)
 @Consumes(MediaType.APPLICATION_JSON)
 @Path("add")
 public String addUser(String user) throws MissingInput {
     UserDTO userDTO = GSON.fromJson(user, UserDTO.class);
     UserDTO addUser = FACADE.addUser(userDTO.getUsername(), userDTO.getPassword());
     return GSON.toJson(addUser);
 }

        
}
