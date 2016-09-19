package com.rip.framework.service;



public interface ProjectTechnologyManagementService {

	String addProjectTechnologySpeck(String proTechnologySpeck);
        
        String updateProjectTechnologySpeck(String proTechnologySpeck);
        
        String deleteProjectTechnologySpeck(String appName, String version);
        
        String getProjectTechnologySpeck(String appName, String version);

}
