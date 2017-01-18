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
 * Template to gather all details related
 * to REST API Design Specification
 */
public class DRestApiDesign {
    
    private final String swagger = "2.0";
    private DInfo info;
    private String host;
    private String basePath;
    private ArrayList<DTag> tags;
    private ArrayList<String> schemes;
    private ArrayList<String> consumes;
    private ArrayList<String> produces;
    private HashMap<String, HashMap<String, DHttpMethod>> paths;
    private HashMap<String, DClass> definitions;
    
    public DRestApiDesign(){
        initializeContainers();
        setDetails();
    }
    
    private void initializeContainers(){
        info = new DInfo();
        tags = new ArrayList();
        schemes = new ArrayList();
        consumes = new ArrayList();
        produces = new ArrayList();
        paths = new HashMap();
        definitions = new HashMap();
    }
    
    private void setDetails(){
       schemes.add("http");
       consumes.add("application/json");
       produces.add("application/json");
    }

    public DInfo getInfo() {
        return info;
    }

    public void setInfo(DInfo info) {
        this.info = info;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public ArrayList<DTag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<DTag> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getSchemes() {
        return schemes;
    }

    public void setSchemes(ArrayList<String> schemes) {
        this.schemes = schemes;
    }

    public ArrayList<String> getConsumes() {
        return consumes;
    }

    public void setConsumes(ArrayList<String> consumes) {
        this.consumes = consumes;
    }

    public ArrayList<String> getProduces() {
        return produces;
    }

    public void setProduces(ArrayList<String> produces) {
        this.produces = produces;
    }

    public HashMap<String, DClass> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(HashMap<String, DClass> definitions) {
        this.definitions = definitions;
    }

    public HashMap<String, HashMap<String, DHttpMethod>> getPaths() {
        return paths;
    }

    public void setPaths(HashMap<String, HashMap<String, DHttpMethod>> paths) {
        this.paths = paths;
    }
    
    
    
    
    
    
}
