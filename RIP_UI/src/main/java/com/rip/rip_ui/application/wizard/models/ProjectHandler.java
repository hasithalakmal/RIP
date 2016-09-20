/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.models;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassAttrTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ResourceTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.TableFieldTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.TableTemplate;
import com.rip.rip_ui.application.wizard.templates.ProjectTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author Supun
 */
public class ProjectHandler {
    
    private String id = "RIP_PRO_001";
    private String projectName;
    private String dbName;
    
    private ArrayList<String> resourceElements = new ArrayList<String>();
    private ArrayList<String> tableElements = new ArrayList<String>();

    
    private ProjectTemplate project;
    private FileHandler fileHandler;
    
    private String[] commandArray;
    
    //initialize a project
    public void createNewProject(String[] reqDetails, String[] techSpec){
        this.setDbName(reqDetails[2]);
        this.setProjectName(reqDetails[1]);
        project = new ProjectTemplate(id, this.getDbName());
        
        //set current date time
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        
        project.setReqDetails(id,reqDetails[0], this.getProjectName(),datetime);
        project.setTechSpec(techSpec);
        
         
        
    }
    
    //analyze first word of command
    public String analyzeCommand(String[] commandArray) {
        
        this.commandArray = commandArray;
        
        if(commandArray[0].equals("create")){
            return analyzeCreateElement();
        }
        
        else if(commandArray[0].equals("edit")){
            return "";
        }
        
        else if(commandArray[0].equals("delete")){
            return "";
        }
        
        else if(commandArray[0].equals("add")){
            return analyzeAddElement();
        }
        
        else{
            return "";
        }
    }

    //analyze the creation string of command
    private String analyzeCreateElement() {
                
        if(commandArray[1].equals("resource")){
            return this.createResourceObject();
        }
        
        else if(commandArray[1].equals("table")){
            return this.createTableObject();
        }
        
        else if(commandArray[1].equals("class")){
            return this.createClassObject();
        }
        
        else{
            return "";
        }
    }
    
    //analyze the addition string of command
    private String analyzeAddElement() {
                
        if(commandArray[1].equals("table_field")){
            return this.addTableFieldObject();
        }
        
        else if(commandArray[1].equals("link")){
            return this.createLinkObject();
        }
        
        else if(commandArray[1].equals("class_attr")){
            return this.addClassAttrObj();
        }
        
        else{
            return "";
        }
    }
    
    public String createResourceObject(){
            String resource = commandArray[2];
            String uri = commandArray[3];
                        
            int resourceId = project.getResourcesSize();
            ResourceTemplate resourceObj = new ResourceTemplate(id,resourceId);
            
            resourceObj.setResource_name(resource);
            resourceObj.setUri(uri);
            resourceObj.setMethods(Arrays.copyOfRange(commandArray, 4, commandArray.length));
            
            project.addResource(resourceId, resourceObj);
            resourceElements.add(resourceId,resource);
            this.writeToProject();
            
            return "Resource: "+resource+" created";
    }
    
    private String createTableObject() {
        String table = commandArray[2];
        
        int tableId = project.getTablesSize();
        TableTemplate tableObj = new TableTemplate(id,tableId);
        tableObj.setTable_name(commandArray[2]);
        
        project.addTable(tableId, tableObj);
        resourceElements.add(tableId,table);
        this.writeToProject();
            
        return "Table: "+table+" created";
        
    }
    
    
    private String addTableFieldObject() {
        String tableField = commandArray[2];
        String tableName = commandArray[4];
       
        int tableFieldId = project.getTableFieldSize(tableName);
        TableFieldTemplate tableFieldObj = new TableFieldTemplate(id,tableFieldId);
        
        tableFieldObj.setField_name(commandArray[2]);
        tableFieldObj.setData_type(commandArray[3]);
        tableFieldObj.setSize(commandArray[4]);
        tableFieldObj.setPrimary_key(commandArray[5]);
        
        project.addTableField(commandArray[4], tableFieldId, tableFieldObj);
        this.writeToProject();
            
        return "Table Field: "+tableField+" created in "+tableName;
        
    }
    
    private String createLinkObject(){
        
        String tableName = commandArray[2];
        String resourceName = commandArray[3];
        return "";
    }
    
    private String createClassObject() {
        String className = commandArray[2];
        int classId = project.getClassesSize();
        ClassTemplate classObj = new ClassTemplate(id,classId);
        classObj.setClass_name(commandArray[2]);
        
        project.addClass(classId, classObj);
        //resourceElements.add(tableId,table);
        this.writeToProject();
        
        return "Class: "+className+" created";
    }
    
    
    public void writeToProject(){
        fileHandler = new FileHandler();
        fileHandler.writeToJSON(project, id);
    }
    
    
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String addClassAttrObj() {
        String classAttr = commandArray[4];
        String className = commandArray[6];
       
        int classAttrId = project.getAttrListSize(classAttr);
        ClassAttrTemplate classAttrObj = new ClassAttrTemplate(id,classAttrId);
        
        classAttrObj.setAttribute(commandArray[4]);
        classAttrObj.setAccess_modifier(commandArray[2]);
        classAttrObj.setType(commandArray[3]);
        
        
        project.addClassAttr(className, classAttrId, classAttrObj);
        this.writeToProject();
            
        return "Class Attribute: "+classAttr+" created in "+className;
    }

    

    
}
