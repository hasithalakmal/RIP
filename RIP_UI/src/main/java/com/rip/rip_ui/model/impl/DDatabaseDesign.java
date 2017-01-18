/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import java.util.ArrayList;

/**
 *
 * Data template for database design
 */
public class DDatabaseDesign {
    
    private String db_name;
    
    //list of tables in db
    private ArrayList<DTable> tables = new ArrayList<DTable>();
    
    //list of foreign keys in db
    private ArrayList<DForeignKey> foreign_keys = new ArrayList<DForeignKey>();
    
    public DDatabaseDesign(String dbName){
        db_name = dbName;
    }

}
