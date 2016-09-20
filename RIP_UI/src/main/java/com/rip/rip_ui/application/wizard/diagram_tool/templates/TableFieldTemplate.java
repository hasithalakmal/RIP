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
public class TableFieldTemplate extends Template {
    
    private String field_name;
    private String data_type;
    private String size;
    private String primary_key = "false";
    private String auto_increment = "false";
    private String not_null = "true";
    private String unique = "false";
    private String binary = "false";
    private String zero_fill = "false";
    private String unsign = "false";
    private String generated = "false";
    private String index = "false";

    public TableFieldTemplate(String id, int tableFieldId) {
        this.createId(id);
        this.setId(id+tableFieldId+"-TF");
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(String primary_key) {
        this.primary_key = primary_key;
    }

    public String getAuto_increment() {
        return auto_increment;
    }

    public void setAuto_increment(String auto_increment) {
        this.auto_increment = auto_increment;
    }

    public String getNot_null() {
        return not_null;
    }

    public void setNot_null(String not_null) {
        this.not_null = not_null;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }

    public String getZero_fill() {
        return zero_fill;
    }

    public void setZero_fill(String zero_fill) {
        this.zero_fill = zero_fill;
    }

    public String getUnsign() {
        return unsign;
    }

    public void setUnsign(String unsign) {
        this.unsign = unsign;
    }

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    
    

    @Override
    public void createId(String id) {
        this.setId(id);
    }
    
}
