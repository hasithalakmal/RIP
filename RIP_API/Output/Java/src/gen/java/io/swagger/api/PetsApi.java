package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.PetsApiService;
import io.swagger.api.factories.PetsApiServiceFactory;

import io.swagger.model.ErrorModel;
import io.swagger.model.Pet;
import io.swagger.model.NewPet;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/pets")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-09-21T10:33:35.196+05:30")
public class PetsApi  {
   private final PetsApiService delegate = PetsApiServiceFactory.getPetsApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response addPet( NewPet pet,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addPet(pet,securityContext);
    }
    @DELETE
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response deletePet( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deletePet(id,securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/xml", "text/xml", "text/html" })
    public Response findPetById( @PathParam("id") Long id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findPetById(id,securityContext);
    }
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/xml", "text/xml", "text/html" })
    public Response findPets( @QueryParam("tags") List<String> tags, @QueryParam("limit") Integer limit,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findPets(tags,limit,securityContext);
    }
}
