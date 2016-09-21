/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.RequestTemplate;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class RestClientTemplate extends Template{
    
    private ArrayList<RequestTemplate> requests;
    

    RestClientTemplate(String id) {
        this.createId(id);
        this.setId(id+"-CL");
        requests = new ArrayList<RequestTemplate>();
    }

    @Override
    public void createId(String id) {
        this.setId(id);
    }

    public int getRequestListSize() {
        return requests.size();
    }

    void addRequest(int requestId, RequestTemplate requestObj) {
        requests.add(requestObj);
    }

    void setResourceReference(String request, String resource) {
        for(int i=0;i<requests.size();i++){
            RequestTemplate requestObj = requests.get(i);
            if(requestObj.getRequest().equals(request)){
                requestObj.addResourceReference(resource);
                break;
            }
        }
    }


    
}
