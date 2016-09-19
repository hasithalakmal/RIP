/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

/**
 *
 * @author Supun
 */
public class RequestDetailsTemplate extends Template {
    
    
    private String project_id;
    private String project_name;
    private String version;
    private String created_time;
    private String last_modified;

    RequestDetailsTemplate(String id) {
        this.createId(id);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public void createId(String id) {
        this.setId(id+"-RD");
    }
    
    
}
