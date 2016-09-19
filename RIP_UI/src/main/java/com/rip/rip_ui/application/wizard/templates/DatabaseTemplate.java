/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.ForeignKey;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.Table;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.View;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class DatabaseTemplate extends Template {
    
    private String db_name;
    
    //list of tables in db
    private ArrayList<Table> tables = new ArrayList<Table>();
    
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
    
    
    
    
}
