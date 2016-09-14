package com.rip.framework.mongodao;

import java.util.ArrayList;



public interface ProjectManagementDao {

	String createProject(String project);
        
        String deleteProject(String projectName, String version);
        
        String selectProject(String projectName, String version);
        
        String selectProjectByName(String projectName);
        
        ArrayList selectProjectByUser(String userName);

        ArrayList selectAllProjects();
}
