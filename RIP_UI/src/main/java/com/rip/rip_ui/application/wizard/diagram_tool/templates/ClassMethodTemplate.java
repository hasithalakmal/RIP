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
public class ClassMethodTemplate extends Template{
    
    private String access_modifier;
    private String return_type;
    private String method;
    private ArrayList<String> parameters;
    private String isFinal = "false";
    private String isStatic = "false";
    private String isAbstract = "false";
       
    public ClassMethodTemplate(String id, String classMethodId){
        this.createId(id);
        this.setId(id+classMethodId+"-M");
    
    }
    
    public String getAccess_modifier() {
        return access_modifier;
    }

    public void setAccess_modifier(String access_modifier) {
        this.access_modifier = access_modifier;
    }

    public String getReturn_type() {
        return return_type;
    }

    public void setReturn_type(String return_type) {
        this.return_type = return_type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public String getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(String isStatic) {
        this.isStatic = isStatic;
    }

    public String getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(String isAbstract) {
        this.isAbstract = isAbstract;
    }

    @Override
    public void createId(String id) {
        this.setId(id);
    }
    
}
