package com.rip.framework.mongodao;



public interface ProjectTechnologyManagementDao {

	String addProjectTechnologySpeck(String proTechnologySpeck);
        
        String updateProjectTechnologySpeck(String proTechnologySpeck);
        
        String deleetProjectTechnologySpeck(String appName, String version);
        
        String getProjectTechnologySpeck(String appName, String version);

}
