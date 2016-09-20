/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassAttrTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ClassTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.ResourceTemplate;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class RestApiTemplate extends Template {
    
    private ArrayList<ResourceTemplate> resources;
    private ArrayList<ClassTemplate> classes;

    RestApiTemplate(String id) {
        
        this.createId(id);
        resources = new ArrayList<ResourceTemplate>();
        classes = new ArrayList<ClassTemplate>();
    }

    //giving the size of the resources list
    public int getResourcesListSize(){
        return resources.size();
    }
    
    //addvResource instance to resources array
    public void addResource(int id, ResourceTemplate resource){
        resources.add(id, resource);
    }    
    
    @Override
    public void createId(String id) {
        this.setId(id+"-API");
    }

    public int getClasseListSize() {
        return classes.size();
    }

    void addClass(int classId, ClassTemplate classObj) {
        classes.add(classId, classObj);
    }

    int getAttrListSize(String className) {
        for(int i=0;i<classes.size();i++){
            ClassTemplate classObj = classes.get(i);
            if(classObj.getClass_name().equals(className)){
                return classObj.getAttrListSize();
               
            }
        }
        
        return -1;
    }

    void addClassAttr(String className, int classAttrId, ClassAttrTemplate classAttrObj) {
        for(int i=0;i<classes.size();i++){
            ClassTemplate classObj = classes.get(i);
            if(classObj.getClass_name().equals(className)){
                classObj.addClassAttr(classAttrId,classAttrObj);
               
            }
        }
        
        
    }

    
    
}
