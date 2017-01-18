/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

/**
 *
 * 
 */
public class DParameter {
    
    private String name;
    private String in;
    private String description;
    private String required;
    private String type;
    private DSchema schema;
    
    public DParameter(String name, String in, String type){
        this.name = name;
        this.in = in;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DSchema getSchema() {
        return schema;
    }

    public void setSchema(DSchema schema) {
        this.schema = schema;
    }
    
    
    
    
}
