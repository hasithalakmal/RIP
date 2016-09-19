package com.rip.framework.service;

import java.util.ArrayList;



public interface ProjectTechnologyManagementService {

	String addProjectTechnologySpeck(String proTechnologySpeck);
        
        String updateProjectTechnologySpeck(String proTechnologySpeck);
        
        String deleetProjectTechnologySpeck(String appName, String version);
        
        String getProjectTechnologySpeck(String appName, String version);

}
