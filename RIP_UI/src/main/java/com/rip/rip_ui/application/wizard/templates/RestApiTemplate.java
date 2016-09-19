/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ResourceTemplate;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class RestApiTemplate extends Template {
    
    private ArrayList<ResourceTemplate> resources;

    RestApiTemplate(String id) {
        
        this.createId(id);
        resources = new ArrayList<ResourceTemplate>();
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

    
    
}
