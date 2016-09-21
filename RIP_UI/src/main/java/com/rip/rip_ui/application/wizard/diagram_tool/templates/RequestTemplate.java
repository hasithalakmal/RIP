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
public class RequestTemplate extends Template{
    
    private String request;
    private String method;
    private String dest_uri;
    private String[] parameters;
    private ArrayList<String> resource_references;

    public String getDest_uri() {
        return dest_uri;
    }

    public void setDest_uri(String dest_uri) {
        this.dest_uri = dest_uri;
    }
    

    public RequestTemplate(String id, int requestId) {
        
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public void addParameters(String[] parameters){
        this.parameters = parameters;
    }
    
    
    

    @Override
    public void createId(String id) {
        this.setId(id);
    }

    public void addResourceReference(String resource) {
        resource_references.add(resource);
    }
    
}
