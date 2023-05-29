/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rest;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Selina A.S.
 */


@Path("sharemates")

public class ShareMatesResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo(){
        return "{\"msg\":\"ShareMates endpoint\"}";
    }
}
