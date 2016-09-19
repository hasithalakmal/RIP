/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.templates;

import com.rip.rip_ui.application.wizard.diagram_tool.templates.DeleteQuery;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.InsertQuery;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.SelectQuery;
import com.rip.rip_ui.application.wizard.diagram_tool.templates.UpdateQuery;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class QueryTemplate extends Template {
    
    //keeps select queries
    private ArrayList<SelectQuery> select_queries = new ArrayList<SelectQuery>();
    
    //keeps insert queries
    private ArrayList<InsertQuery> insert_queries = new ArrayList<InsertQuery>();
    
    //keeps update queries
    private ArrayList<UpdateQuery> update_queries = new ArrayList<UpdateQuery>();
    
    //keeps delete queries
    private ArrayList<DeleteQuery> delete_queries = new ArrayList<DeleteQuery>();

    QueryTemplate(String id) {
        this.createId(id);
    }

    @Override
    public void createId(String id) {
        this.setId(id+"-QD");
    }
}
