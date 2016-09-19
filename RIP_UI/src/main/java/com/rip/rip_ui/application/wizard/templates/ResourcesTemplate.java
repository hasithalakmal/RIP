/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ResourceTemplate;
import java.util.ArrayList;
import javax.annotation.Resource;

/**
 *
 * @author Supun
 */
public class ResourcesTemplate extends Template{
    
    private ArrayList<ResourceTemplate> resources = new ArrayList<ResourceTemplate>();
    
    public ResourcesTemplate(String id){
        this.createId(id);
    }
    
    public void addResources(int id, ResourceTemplate resource){
        resources.add(id,resource);
    }
    
    //giving the size of the resources list
    public int getResourceListSize(){
        return resources.size();
    }

    @Override
    public void createId(String id) {
        this.setId(id+"-R");
    }
}
