/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.diagram_tool.templates;

import com.rip.rip_ui.application.wizard.templates.Template;

/**
 *
 * @author Supun
 */
public class ResourceTemplate extends Template {

    private String resource_name;
    private String uri;
    private String[] methods;
    
    
    public ResourceTemplate(String id,int resId){
        this.createId(id);
        this.setId(this.getId()+Integer.toString(resId));
    }


    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String[] getMethods() {
        return methods;
    }

    public void setMethods(String[] methods) {
        this.methods = methods;
    }

    
    
    @Override
    public void createId(String id) {
        this.setId(id);
    }
    
}
