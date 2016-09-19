/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.models;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ResourceTemplate;
import com.rip.rip_ui.application.wizard.templates.ProjectTemplate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author Supun
 */
public class ProjectHandler {
    
    private String id = "RIP_PRO_001";
    private String dbName;

    
    private ProjectTemplate project;
    private FileHandler fileHandler;
    
    private String[] commandArray;
    
    //initialize a project
    public void createNewProject(String[] reqDetails, String[] techSpec){
        this.setDbName(reqDetails[2]);
        project = new ProjectTemplate(id, this.getDbName());
        
        //set current date time
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        
        project.setReqDetails(id,reqDetails[0], reqDetails[1],datetime);
        project.setTechSpec(techSpec);
        
        fileHandler = new FileHandler();
        fileHandler.writeToJSON(project, id);
    }
    
    //analyze first word of command
    public String analyzeCommand(String[] commandArray) {
        
        this.commandArray = commandArray;
        
        if(commandArray[0].equals("create")){
            return analyzeElement();
        }
        
        else if(commandArray[0].equals("edit")){
            return "";
        }
        
        else if(commandArray[0].equals("delete")){
            return "";
        }
        
        else if(commandArray[0].equals("open")){
            return "";
        }
        
        else{
            return "";
        }
    }

    //analyze the second word of command
    private String analyzeElement() {
                
        if(commandArray[1].equals("resource")){
            
            String resource = commandArray[2];
            String uri = commandArray[3];
            Arrays.copyOfRange(commandArray, 4, commandArray.length);
            
            int resourceId = project.getResourcesSize();
            ResourceTemplate resourceObj = new ResourceTemplate(id,resourceId);
            
        }
        
        return "";
    }
    
    
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
