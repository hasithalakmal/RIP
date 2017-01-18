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
public class DForeignKey {
    
    private String fk_name;
    private String base_table;
    private String bt_field_name;
    private String reference_table;
    private String rt_field_name;
    private String on_delete;
    private String on_update;
    private String relation;
    
    public DForeignKey(HashMap<String,String> fkDetails){
        
        this.fk_name = fkDetails.get(DataLabel.FK_NAME);
        this.base_table = fkDetails.get(DataLabel.FK_BASE_TABLE);
        this.bt_field_name = fkDetails.get(DataLabel.FK_BASE_TABLE_FIELD);
        this.reference_table = fkDetails.get(DataLabel.FK_REFERENCE_TABLE);
        this.rt_field_name = fkDetails.get(DataLabel.FK_REFERENCE_TABLE_FIELD);
        this.on_delete = fkDetails.get(DataLabel.FK_ON_DELETE);
        this.on_update = fkDetails.get(DataLabel.FK_ON_UPDATE);
        this.relation = fkDetails.get(DataLabel.FK_RELATION);
    
    }

    public String getFk_name() {
        return fk_name;
    }

    public void setFk_name(String fk_name) {
        this.fk_name = fk_name;
    }

    public String getBase_table() {
        return base_table;
    }

    public void setBase_table(String base_table) {
        this.base_table = base_table;
    }

    public String getBt_field_name() {
        return bt_field_name;
    }

    public void setBt_field_name(String bt_field_name) {
        this.bt_field_name = bt_field_name;
    }

    public String getReference_table() {
        return reference_table;
    }

    public void setReference_table(String reference_table) {
        this.reference_table = reference_table;
    }

    public String getRt_field_name() {
        return rt_field_name;
    }

    public void setRt_field_name(String rt_field_name) {
        this.rt_field_name = rt_field_name;
    }

    public String getOn_delete() {
        return on_delete;
    }

    public void setOn_delete(String on_delete) {
        this.on_delete = on_delete;
    }

    public String getOn_update() {
        return on_update;
    }

    public void setOn_update(String on_update) {
        this.on_update = on_update;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
    
    
    
}
