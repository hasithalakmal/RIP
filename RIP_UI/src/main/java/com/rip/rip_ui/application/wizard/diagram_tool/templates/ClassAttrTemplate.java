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
public class ClassAttrTemplate extends Template{

    private String access_modifier;
    private String type;
    private String attribute;

    public ClassAttrTemplate(String id, int classFieldId){
        this.createId(id);
        this.setId(id+classFieldId+"-A");
    }
    
    public String getAccess_modifier() {
        return access_modifier;
    }

    public void setAccess_modifier(String access_modifier) {
        this.access_modifier = access_modifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String field) {
        this.attribute = field;
    }
    
    @Override
    public void createId(String id) {
        
    }
    
}
