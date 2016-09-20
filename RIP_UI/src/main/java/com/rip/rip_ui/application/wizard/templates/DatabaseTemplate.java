/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ForeignKey;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.TableFieldTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.TableTemplate;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.View;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class DatabaseTemplate extends Template {
    
    private String db_name;
    
    //list of tables in db
    private ArrayList<TableTemplate> tables = new ArrayList<TableTemplate>();
    
    //list of foreign keys in db
    private ArrayList<ForeignKey> foreign_keys = new ArrayList<ForeignKey>();

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }
    
    //list of views in db
    private ArrayList<View> views = new ArrayList<View>();

    DatabaseTemplate(String id, String dbName) {
        this.createId(id);
        this.setDb_name(dbName);
    }

    @Override
    public void createId(String id) {
        this.setId(id+"-DB");
    }

    int getTablesListSize() {
        return tables.size();
    }

    void addTable(int id, TableTemplate table) {
        tables.add(id, table);
    }

    void addTableField(String tableName,int tableFieldId, TableFieldTemplate tableFieldObj) {
        
        for(int i=0;i<tables.size();i++){
            TableTemplate table = tables.get(i);
            if(table.getTable_name().equals(tableName)){
                table.addTableField(tableFieldId, tableFieldObj);
                break;
            }
        }
    }

    public int getTableFieldSize(String tableName) {
        for(int i=0;i<tables.size();i++){
            TableTemplate table = tables.get(i);
            if(table.getTable_name().equals(tableName)){
                return table.getTableFieldSize();
               
            }
        }
        
        return -1;
    }
    
    
    
    
}
