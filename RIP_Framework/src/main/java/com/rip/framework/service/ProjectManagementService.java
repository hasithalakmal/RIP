package com.rip.framework.service;

import java.util.ArrayList;



public interface ProjectManagementService {

	String createProject(String project);
        
        String deleteProject(String projectName, String version);
        
        String selectProject(String projectName, String version);
        
        ArrayList selectProjectByName(String projectName);
        
        ArrayList selectProjectByUser(String userName);

        ArrayList selectAllProjects();
}
