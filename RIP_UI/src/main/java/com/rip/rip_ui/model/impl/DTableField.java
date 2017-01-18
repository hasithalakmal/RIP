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
public class DTableField {
    
    private String field_name;
    private String data_type;
    private String primary_key;
    private String auto_increment;
    private String not_null;
    private String unique;
    
    public DTableField(HashMap<String,String> fieldData){
        this.field_name = fieldData.get(DataLabel.TABLE_FIELD_NAME);
        this.data_type = fieldData.get(DataLabel.TABLE_FIELD_TYPE);
        this.primary_key = fieldData.get(DataLabel.TABLE_FIELD_PK);
        this.auto_increment = fieldData.get(DataLabel.TABLE_FIELD_AI);
        this.not_null = fieldData.get(DataLabel.TABLE_FIELD_NN);
        this.unique = fieldData.get(DataLabel.TABLE_FIELD_UQ);
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
    
    
    
}
