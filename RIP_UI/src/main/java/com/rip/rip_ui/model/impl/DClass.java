/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Template to store class details
 * about the client model
 */
public class DClass {
    
    private final String type = "object";
    private ArrayList<String> required;
    private HashMap<String, DAttributeType> properties;
    
    public void addRequiredAttr(String requiredAttr){
        if(required==null){
            required = new ArrayList();
        }
        
        required.add(requiredAttr);
    }
    
    public void addAttribute(String fieldName, String fieldType){
        if(properties==null){
            properties = new HashMap();
        }
        
        DAttributeType dAttrType = new DAttributeType(fieldType);
        properties.put(fieldName, dAttrType);
    
    }

    public String getType() {
        return type;
    }
    
    
    
}
