/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.diagram_tool.templates;

import com.rip.rip_ui.application.wizard.templates.Template;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class TableTemplate extends Template {
    
    private String table_name;
    private ArrayList<TableFieldTemplate> fields;

    public TableTemplate(String id, int tableId) {
        this.createId(id);
        this.setId(id+tableId+"-T");
        fields = new ArrayList<TableFieldTemplate>();
    }
    
    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
    
    public void addTableField(int id,TableFieldTemplate tableField){
        this.fields.add(id,tableField);
    }
    
    public int getTableFieldSize(){
        return fields.size();
    }

    @Override
    public void createId(String id) {
        this.setId(id);
    }


    
}
