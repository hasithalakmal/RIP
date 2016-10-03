/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.diagram_tool.templates;

import com.rip.rip_ui.application.wizard.templates.Template;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class ResourceTemplate extends Template {

    private String resource_name;
    private String uri;
    private String[] methods;
    private ArrayList<String> class_references;
    private ArrayList<String> request_references;
    
    
    public ResourceTemplate(String id,int resId){
        this.createId(id);
        this.setId(this.getId()+"-"+Integer.toString(resId)+"-R");
        class_references = new ArrayList<String>();
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

    public void addResourceReference(String className) {
        class_references.add(className);
    }

    public void addRequestResourceReference(String request) {
        request_references.add(request);
    }
    
}
