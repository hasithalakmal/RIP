/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.text.DataLabel;
import java.util.HashMap;

/**
 *
 * @author Supun
 */
public class DPath {

    private DHttpMethod get;
    private DHttpMethod post;
    private DHttpMethod put;
    private DHttpMethod delete;
    
    private HashMap<String, DHttpMethod> methods;
    
    public DPath(HashMap<String, String> paramDetails){
        methods = new HashMap();
        createMethods(paramDetails);
    }

    
    private void createMethods(HashMap<String, String> paramDetails){
        
        if(paramDetails.get(DataLabel.GET).equals("true")){
            get = new DHttpMethod(paramDetails);
            methods.put("get", get);
        }
        
        if(paramDetails.get(DataLabel.POST).equals("true")){
            post = new DHttpMethod(paramDetails);
            methods.put("post", post);
        }
        
        if(paramDetails.get(DataLabel.PUT).equals("true")){
            put = new DHttpMethod(paramDetails);
            methods.put("put", put);
        }
        
        if(paramDetails.get(DataLabel.DELETE).equals("true")){
            delete = new DHttpMethod(paramDetails);
            methods.put("delete", delete);
        }
    
    }

//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }

    public DHttpMethod getGet() {
        return get;
    }

    public void setGet(DHttpMethod get) {
        this.get = get;
    }

    public DHttpMethod getPost() {
        return post;
    }

    public void setPost(DHttpMethod post) {
        this.post = post;
    }

    public DHttpMethod getPut() {
        return put;
    }

    public void setPut(DHttpMethod put) {
        this.put = put;
    }

    public DHttpMethod getDelete() {
        return delete;
    }

    public void setDelete(DHttpMethod delete) {
        this.delete = delete;
    }

    public HashMap<String, DHttpMethod> getMethods() {
        return methods;
    }

    
    
    
            
}
