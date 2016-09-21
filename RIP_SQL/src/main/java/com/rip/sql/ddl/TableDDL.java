/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.ddl;

import java.util.ArrayList;

/**
 *
 * @author Hasitha Lakmal
 */
public interface TableDDL {

    String CreateDDLSQL(String ddlJSON);

    String addForeignKey(String fk_name, String base_table, String bt_feild_name, String reference_table, String rt_feild_name, String onDeleteAction, String onUpdteAction);

    String createFeild(String FeildName, String data_type, boolean is_auto_incriment, boolean is_not_null);

    String createPrimeryKey(ArrayList FeildNames);

    String createTable(String table_JSON);

    String createUniqueKey(ArrayList FeildNames);

    String dropTable(String tableName);
    
}
