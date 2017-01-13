package com.rip.framework.mongodao;



public interface ProjectTechnologyManagementDao {

	String addProjectTechnologySpeck(String proTechnologySpeck);
        
        String updateProjectTechnologySpeck(String proTechnologySpeck);
        
        String deleteProjectTechnologySpeck(String appName, String version);
        
        String getProjectTechnologySpeck(String appName, String version);

}
