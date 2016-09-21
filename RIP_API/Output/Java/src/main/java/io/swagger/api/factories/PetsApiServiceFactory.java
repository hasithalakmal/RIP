package io.swagger.api.factories;

import io.swagger.api.PetsApiService;
import io.swagger.api.impl.PetsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-09-21T10:19:02.285+05:30")
public class PetsApiServiceFactory {

   private final static PetsApiService service = new PetsApiServiceImpl();

   public static PetsApiService getPetsApi()
   {
      return service;
   }
}
