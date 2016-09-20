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
public class ClassTemplate extends Template{
    
    private String class_name; 

    
    ArrayList<ClassAttrTemplate> attributes;
    ArrayList<ClassMethodTemplate> methods;

    public ClassTemplate(String id, int classId) {
        this.createId(id);
        this.setId(id+classId+"-C");
    }
    
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public void createId(String id) {
        this.setId(id);
    }

    public int getAttrListSize() {
        return attributes.size();
    }

    public void addClassAttr(int classAttrId,ClassAttrTemplate classAttrObj) {
        attributes.add(classAttrId,classAttrObj);
    }
    
}
