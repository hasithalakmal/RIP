/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.models;

import com.rip.rip_ui.application.wizard.templates.ProjectTemplate;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Supun
 */
public class ProjectHandler {
    
    private String id = "RIP_PRO_001";
    private ProjectTemplate project;
    private FileHandler fileHandler;
    
    //initialize a project
    public void createNewProject(String[] reqDetails, String[] techSpec){
        project = new ProjectTemplate(id, reqDetails[2]);
        
        //set current date time
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        
        project.setReqDetails(id,reqDetails[0], reqDetails[1],datetime);
        project.setTechSpec(techSpec);
        
        fileHandler = new FileHandler();
        fileHandler.writeToJSON(project, id);
    }
    
}
