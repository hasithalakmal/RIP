package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;


import io.swagger.model.ErrorModel;
import io.swagger.model.Pet;
import io.swagger.model.NewPet;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-09-21T11:23:43.699+05:30")
public abstract class PetsApiService {
      public abstract Response addPet(NewPet pet,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response deletePet(Long id,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response findPetById(Long id,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response findPets(List<String> tags,Integer limit,SecurityContext securityContext)
      throws NotFoundException;
}
