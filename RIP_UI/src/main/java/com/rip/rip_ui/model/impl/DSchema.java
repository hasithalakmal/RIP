/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import java.util.HashMap;

/**
 * Template to store schema details of parameters in rest api design 
 */
public class DSchema {
    
    private String type;
    private HashMap<String, String> items;
    private String $ref;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getItems() {
        return items;
    }

    public void setItems(HashMap<String, String> items) {
        this.items = items;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }
    
    
    
    
}
