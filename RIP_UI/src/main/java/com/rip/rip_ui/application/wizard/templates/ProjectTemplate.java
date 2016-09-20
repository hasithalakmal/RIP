/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassAttrTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ResourceTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.TableFieldTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.TableTemplate;


/**
 *
 * @author Supun
 */
public class ProjectTemplate extends Template {
    
    private RequestDetailsTemplate request_details;
    private TechSpecTemplate technical_spec;
    private DatabaseTemplate database;
    private RestApiTemplate rest_api;
    
    
    public ProjectTemplate(String id, String dbName){
        this.createId(id);
        this.request_details = new RequestDetailsTemplate(this.getId());
        this.technical_spec = new TechSpecTemplate(this.getId());
        this.database = new DatabaseTemplate(this.getId(),dbName);
        this.rest_api = new RestApiTemplate(this.getId());
    }
     
    public void setReqDetails(String projectId,String projectName, String version, String datetime){
        this.request_details.setProject_id(projectId);
        this.request_details.setProject_name(projectName);
        this.request_details.setVersion(version);
        this.request_details.setCreated_time(datetime);
    }
    
    public void setTechSpec(String[] array){         
        this.technical_spec.setLanguage(array[0]);
        this.technical_spec.setRest_framework(array[1]);
        this.technical_spec.setOrm_framework(array[2]);
        this.technical_spec.setDbms(array[3]);
        this.technical_spec.setTest_framework(array[4]);
        this.technical_spec.setCss_framework(array[5]);
        this.technical_spec.setJs_framework(array[6]);
        this.technical_spec.setUi_theme(array[7]);
        this.technical_spec.setArchitecture(array[8]);
        this.technical_spec.setServer(array[9]);
        this.technical_spec.setBuild_tool(array[10]);
        this.technical_spec.setApi_documentation_type(array[11]);
        this.technical_spec.setTechnical_documentation_type(array[12]);
        this.technical_spec.setTest_report_type(array[13]);
        this.technical_spec.setAuthentication_type(array[14]);
        this.technical_spec.setIs_need_crud_operations_for_all(array[15]);
        this.technical_spec.setIs_need_crud_forms_for_all(array[16]);
    }
    
    //giving the size of the resources list
    public int getResourcesSize(){
        return rest_api.getResourcesListSize();
    }
    
    //giving the size of the tables list
    public int getTablesSize() {
        return database.getTablesListSize();
    }
    
    public void addResource(int id, ResourceTemplate resource){
        rest_api.addResource(id, resource);
    }  
    
    public void addTable(int tableId, TableTemplate tableObj) {
        database.addTable(tableId,tableObj);
    }
    
    @Override
    public void createId(String id) {
        this.setId(id);
    }

    public void addTableField(String tableName, int tableFieldId, TableFieldTemplate tableFieldObj) {
        database.addTableField(tableName, tableFieldId,tableFieldObj);
    }

    public int getTableFieldSize(String tableName) {
        return database.getTableFieldSize(tableName);
    }

    public int getClassesSize() {
        return rest_api.getClasseListSize();
    }

    public void addClass(int classId, ClassTemplate classObj) {
        rest_api.addClass(classId,classObj);
    }

    public int getAttrListSize(String classAttr) {
        return rest_api.getAttrListSize(classAttr);
    }

    public void addClassAttr(String className, int classAttrId, ClassAttrTemplate classAttrObj) {
        rest_api.addClassAttr(className, classAttrId, classAttrObj);
    }

    

    
    
}
