/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.scriptgen;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
public class DDL_GenaratorImpl implements DDL_Genarator {

    @Override
    public String createDDLScript(JSONObject platformIndependentModel, JSONObject domainSpecificModel) {
        String msg = "";
        JSONObject Database_Design = platformIndependentModel.getJSONObject("Database_Design");
        JSONArray rip_sql_tables = Database_Design.getJSONArray("tables");

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = (JSONObject) rip_sql_tables.get(i);
            String table_script = this.createTable(rip_sql_table, domainSpecificModel);
            msg = msg + table_script + "\n\n";
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(msg);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return msg;
    }

    @Override
    public String createTable(JSONObject rip_sql_table, JSONObject domainSpecificModel) {
        String script = "";
        String rip_sql_dsm_table = domainSpecificModel.getString("dsm_table");
        String rip_sql_table_name = rip_sql_table.getString("table_name");
        rip_sql_dsm_table.replaceFirst("rip_sql_table_name", rip_sql_table_name);
        JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("rip_sql_fileds");
        for (int i = 0; i < rip_sql_fileds.length(); i++) {
            JSONObject rip_sql_filed = (JSONObject) rip_sql_fileds.get(i);
            String rip_sql_feild_name = rip_sql_filed.getString("rip_sql_feild_name");
            String rip_sql_data_type = rip_sql_filed.getString("rip_sql_data_type");
            boolean rip_sql_primary_key = rip_sql_filed.getBoolean("rip_sql_primary_key");
            boolean rip_sql_auto_incriment = rip_sql_filed.getBoolean("rip_sql_auto_incriment");
            boolean rip_sql_not_null = rip_sql_filed.getBoolean("rip_sql_not_null");
            boolean rip_sql_unique = rip_sql_filed.getBoolean("rip_sql_unique");
        }

        script = rip_sql_dsm_table;
        return script;
    }

    @Override
    public String addForignKeys(JSONObject platformIndependentModel, JSONObject domainSpecificModel) {
        return "msg";
    }

}
