/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.models;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassAttrTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassMethodTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ForeignKeyTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.RequestTemplate;
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
            return "Invalid input";
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
        
        else if(commandArray[1].equals("request")){
            return this.createRequestObject();
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
        
        else if(commandArray[1].equals("ct_link")){
            return this.addReference();
        }
        
        else if(commandArray[1].equals("rc_link")){
            return this.addReference();
        }
        
        else if(commandArray[1].equals("rr_link")){
            return this.addReference();
        }
        
        else if(commandArray[1].equals("class_attr")){
            return this.addClassAttrObj();
        }
        
        else if(commandArray[1].equals("class_method")){
            return this.addClassMethodObj();
        }
        
        else if(commandArray[1].equals("fk")){
            return this.addFKObj();
        }
        
        else{
            return "Invalid input";
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
        tableFieldObj.setData_type(commandArray[5]);
        tableFieldObj.setSize(commandArray[6]);
        tableFieldObj.setPrimary_key(commandArray[7]);
        
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
    
    private String createRequestObject() {
        String requestName = commandArray[2];
        int requestId = project.getRequestListSize();
        
        RequestTemplate requestObj = new RequestTemplate(id,requestId);
        requestObj.setRequest(commandArray[2]);
        requestObj.setMethod(commandArray[3]);
        requestObj.setDest_uri(commandArray[4]);
        requestObj.addParameters(Arrays.copyOfRange(commandArray, 5, commandArray.length));
        
        project.addRequest(requestId, requestObj);
        
        this.writeToProject();
        
        return "Request: "+requestName+" created";
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
        String classAttr = commandArray[2];
        String className = commandArray[4];
       
        int classAttrId = project.getAttrListSize(className);
        ClassAttrTemplate classAttrObj = new ClassAttrTemplate(id,classAttrId);
        
        classAttrObj.setAttribute(commandArray[2]);
        classAttrObj.setAccess_modifier(commandArray[5]);
        classAttrObj.setType(commandArray[6]);
               
        project.addClassAttr(className, classAttrId, classAttrObj);
        this.writeToProject();
            
        return "Class Attribute: "+classAttr+" created in "+className;
    }

    private String addClassMethodObj() {
        String classMethod = commandArray[2];
        String className = commandArray[4];
        
        int classMethodId = project.getMethodListSize(className);
        ClassMethodTemplate classMethodObj = new ClassMethodTemplate(id,classMethodId);
        
        classMethodObj.setMethod(classMethod);
        classMethodObj.setAccess_modifier(commandArray[5]);
        classMethodObj.setReturn_type(commandArray[6]);
        classMethodObj.setParameters(Arrays.copyOfRange(commandArray, 7, commandArray.length));
        
        project.addClassMethodObj(className, classMethodId, classMethodObj);
        this.writeToProject();
            
        return "Class Method: "+classMethod+" created in "+className;
    }
    
    private String addFKObj() {
        String fk = commandArray[2];
        String table1 = commandArray[4];
        String table1Field = commandArray[6];
        String table2 = commandArray[8];
        String table2Field = commandArray[10];
        
        ForeignKeyTemplate fkObj = new ForeignKeyTemplate();
        fkObj.setFk_name(fk);
        fkObj.setBase_table(table1);
        fkObj.setBt_field_name(table1Field);
        fkObj.setReference_table(table2);
        fkObj.setRt_field_name(table2Field);
        
        project.addFKObj(fkObj);
        this.writeToProject();
            
        return "Foreign key: "+fk+" created";
    }

    private String addReference() {
        if(commandArray[2].equals("class")){
            project.setCTReference(commandArray[3],commandArray[5]);
            this.writeToProject();
            return "Reference created between Class: "+commandArray[3]+" Table: "+commandArray[5];
            
        }
        
        else if(commandArray[2].equals("resource")){
            project.setRCReference(commandArray[3],commandArray[5]);
            this.writeToProject();
            return "Reference created between Resource: "+commandArray[3]+" Class: "+commandArray[5];
        }
        
        else if(commandArray[2].equals("request")){
            project.setRRReference(commandArray[3],commandArray[5]);
            this.writeToProject();
            return "Reference created between Request: "+commandArray[3]+" Resource: "+commandArray[5];
        }
        
        else{
            return "";
        }
        
    }

    

    

    

    
}
