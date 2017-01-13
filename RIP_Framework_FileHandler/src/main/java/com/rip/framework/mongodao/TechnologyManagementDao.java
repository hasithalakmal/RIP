package com.rip.framework.mongodao;

import java.util.ArrayList;



public interface TechnologyManagementDao {
     
        String addNewTechnology(String technology);
        
        String deleteExsistingTechnology(String techname, String version);
        
        ArrayList getExsistingTechnologies();
        
        ArrayList getExsistingTechnologies(String catogery);
        
        String getExsistingTechnology(String techName, String version);


}
