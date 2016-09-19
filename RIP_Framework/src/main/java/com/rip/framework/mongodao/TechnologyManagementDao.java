package com.rip.framework.mongodao;

import java.util.ArrayList;



public interface TechnologyManagementDao {
     
        String introduceNewTechnology(String technology);
        
        String removeExsistingTechnology(String userName, String user);
        
        ArrayList getExsistingTechnologies();
        
        ArrayList getExsistingTechnologies(String catogery);
        
        String getExsistingTechnology(String technologyName);


}
