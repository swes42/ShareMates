    
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.JOSEException;
import dtos.EquipmentDTO;
import dtos.LendDTO;
import errorhandling.MissingInput;
import entities.Lend;
import errorhandling.AuthenticationException;
import facades.LendFacade;
import java.text.ParseException;
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
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.HeaderParam;
import security.JWTAuthenticationFilter;
import security.UserPrincipal;

/**
 *
 * @author Selina A.S.
 */

//startcode database

@Path("lend")
public class LendResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final LendFacade FACADE =  LendFacade.getLendFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static JWTAuthenticationFilter JWT = new JWTAuthenticationFilter();

    @Context
    private UriInfo context;
        
        
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"lend endpoint\"}";
    }
    
    //http://localhost:8080/ShareMates/api/lend/allLends
    @Path("allLends")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllLends() {
        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<Lend> query = em.createQuery("SELECT l FROM Lend l", entities.Lend.class);
            List<Lend> lends = query.getResultList();
            return "[" + lends.size() + "]";
        } finally {
            em.close();
        }
    }

    @POST
    @RolesAllowed({"user", "admin"}) 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createLend")
    public String createLend(String lend, @HeaderParam("x-access-token") String token) 
        throws MissingInput, ParseException, JOSEException, AuthenticationException {
        
        if (lend == null || lend.isEmpty() || token == null || token.isEmpty()) {
            throw new MissingInput("No data input?");
        }
        
        UserPrincipal user = JWT.getUserPrincipalFromTokenIfValid(token);
        if (user == null){
            throw new AuthenticationException("Maybe your token is invalid or expired"); 
        }
        
        LendDTO lendDTO = GSON.fromJson(lend, LendDTO.class);
        LendDTO createLend = FACADE.createLend(user.getName(), lendDTO.getEquipment());
        return GSON.toJson(createLend);
    } 
    
    
}
