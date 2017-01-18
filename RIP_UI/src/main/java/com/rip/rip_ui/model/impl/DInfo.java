/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import java.util.HashMap;

/**
 *
 * Template to gather related Project details
 * for REST API Code generation
 * 
 */
public class DInfo {
    
    private String version;
    private String title;
    private String description;
    private HashMap<String, String> license;
    
    public DInfo(){
        license = new HashMap();
        license.put("name", "MIT");
        license.put("url", "http://opensource.org/licenses/MIT");
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getLicense() {
        return license;
    }

    public void setLicense(HashMap<String, String> license) {
        this.license = license;
    }
    
    
    
}
