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
    private String post;
    private String get;
    private String put;
    private String delete;
    private String patch;
    
    public ResourceTemplate(String id){
        this.createId(id);   
    }
    
    @Override
    public void createId(String id) {
        this.setId(id);
    }
    
}
