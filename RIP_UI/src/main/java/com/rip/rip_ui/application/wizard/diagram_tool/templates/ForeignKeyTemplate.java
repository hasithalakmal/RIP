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
public class ForeignKeyTemplate extends Template{
    
    private String fk_name;
    private String base_table;
    private String bt_field_name;
    private String reference_table;
    private String rt_field_name;

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
    

    
    public void createId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
