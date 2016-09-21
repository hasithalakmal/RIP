/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.ddl;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
public class TableDDLImpl implements TableDDL {

    @Autowired
    DatabaseDDLImpl databaseDDLImpl;

    @Override
    public String createFeild(String FeildName, String data_type, boolean is_auto_incriment, boolean is_not_null) {
        String feild_query = "";
        feild_query = FeildName + " " + data_type + " ";

        if (is_auto_incriment) {
            feild_query = feild_query + " AUTO_INCREMENT";
        }
        if (is_not_null) {
            feild_query = feild_query + " NOT NULL ";
        }

        return feild_query;
    }

    @Override
    public String createPrimeryKey(ArrayList FeildNames) {
        String pk_query = "PRIMARY KEY ( ";

        for (int i = 0; i < FeildNames.size(); i++) {

            if (i == FeildNames.size() - 1) {
                pk_query = pk_query + FeildNames.get(i) + " )";
            } else {
                pk_query = pk_query + FeildNames.get(i) + " , ";
            }

        }

        return pk_query;
    }

    @Override
    public String createUniqueKey(ArrayList FeildNames) {
        String uk_query = "";

        for (int i = 0; i < FeildNames.size(); i++) {
            //uk_query = uk_query + "RIP_UNIQUE_FEILD_" + FeildNames.get(i)+ "  ( "+ FeildNames.get(i) + " ASC)";
            if (i == FeildNames.size() - 1) {
                uk_query = uk_query + "UNIQUE INDEX  RIP_UNIQUE_FEILD_" + FeildNames.get(i) + "  ( " + FeildNames.get(i) + " ASC)";
            } else {
                uk_query = uk_query + "UNIQUE INDEX  RIP_UNIQUE_FEILD_" + FeildNames.get(i) + "  ( " + FeildNames.get(i) + " ASC), \n";
            }
        }

        return uk_query;
    }

    @Override
    public String createTable(String table_JSON) {

        JSONObject jason = new JSONObject(table_JSON);
        String table_name = jason.get("table_name").toString();

        JSONArray fileds = jason.getJSONArray("fileds");

        ArrayList pklist = new ArrayList();
        ArrayList uklist = new ArrayList();

        String tbl_query = "CREATE TABLE " + table_name + " ( ";

        for (int i = 0; i < fileds.length(); i++) {
            JSONObject fld = fileds.getJSONObject(i);

            String fn = fld.getString("feild_name");
            boolean pk = fld.getBoolean("primary_key");
            boolean uk = fld.getBoolean("unique");

            if (pk) {
                pklist.add(fn);
            }

            if (uk) {
                uklist.add(fn);
            }

            String flsQuery = this.createFeild(fld.getString("feild_name"), fld.getString("data_type"), fld.getBoolean("auto_incriment"), fld.getBoolean("not_null"));

            if (i == fileds.length() - 1) {
                tbl_query = tbl_query + "\n" + flsQuery;
            } else {
                tbl_query = tbl_query + "\n" + flsQuery + ",";
            }

        }

        if (uklist.isEmpty()) {
            if (pklist.isEmpty()) {
                tbl_query = tbl_query + ");";
            } else {
                String pkQuery = this.createPrimeryKey(pklist);
                tbl_query = tbl_query + ", \n" + pkQuery + ");";
            }

        } else if (pklist.isEmpty()) {
            String ukQuery = this.createUniqueKey(uklist);
            tbl_query = ", \n" + ukQuery + ");";
        } else {
            String pkQuery = this.createPrimeryKey(pklist);
            String ukQuery = this.createUniqueKey(uklist);
            tbl_query = tbl_query + ", \n" + pkQuery + " ,\n" + ukQuery + ");";
        }

        return tbl_query;
    }

    @Override
    public String dropTable(String tableName) {

        String query = "DROP TABLE  " + tableName + " ;";

        return query;

    }

    @Override
    public String addForeignKey(String fk_name, String base_table, String bt_feild_name, String reference_table, String rt_feild_name, String onDeleteAction, String onUpdteAction) {
        String addFKQuery = "ALTER TABLE " + base_table + " \n ADD CONSTRAINT " + fk_name + " FOREIGN KEY ( " + bt_feild_name + " )\n"
                + "  REFERENCES " + reference_table + " ( " + rt_feild_name + " )\n"
                + "  ON DELETE " + onDeleteAction + " ON UPDATE  " + onUpdteAction + " ;";

        return addFKQuery;
    }

    @Override
    public String CreateDDLSQL(String ddlJSON) {
        String ddlQuery = "";

        JSONObject jason = new JSONObject(ddlJSON);
        String db_name = jason.get("db_name").toString();

       // ddlQuery = ddlQuery + databaseDDLImpl.createDatabase(db_name) + "\n\n";
       // ddlQuery = ddlQuery + databaseDDLImpl.useDatabase(db_name) + "\n\n";

        JSONArray tables = jason.getJSONArray("tables");

        for (int i = 0; i < tables.length(); i++) {
            ddlQuery = ddlQuery + this.createTable(tables.get(i).toString()) + "\n\n";
        }

        JSONArray forign_keys = jason.getJSONArray("forign_keys");
        for (int i = 0; i < forign_keys.length(); i++) {

            JSONObject keyObj = forign_keys.getJSONObject(i);
            String fk_name = keyObj.getString("fk_name");
            String base_table = keyObj.getString("base_table"); 
            String bt_feild_name = keyObj.getString("bt_feild_name"); 
            String reference_table = keyObj.getString("reference_table"); 
            String rt_feild_name = keyObj.getString("rt_feild_name"); 
            String onDeleteAction = keyObj.getString("on_delete");
            String onUpdteAction = keyObj.getString("on_update");
            
            
            ddlQuery = ddlQuery + this.addForeignKey(fk_name, base_table, bt_feild_name, reference_table, rt_feild_name, onDeleteAction, onUpdteAction)+ "\n\n";
        }

        return ddlQuery;
    }

}
