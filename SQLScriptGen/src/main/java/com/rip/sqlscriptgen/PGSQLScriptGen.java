/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sqlscriptgen;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Hasitha Lakmal
 */
public class PGSQLScriptGen implements DatabasePulgginInterface {

    HashMap dataTypes = new HashMap();

    public PGSQLScriptGen() {
        dataTypes.put("short_string", "text");
        dataTypes.put("medium_string", "text");
        dataTypes.put("long_string", "text");
        dataTypes.put("small_lnteger", "integer");
        dataTypes.put("medium_integer", "integer");
        dataTypes.put("big_integer", "integer");
        dataTypes.put("float", "double precision");
        dataTypes.put("date_with_tz", "date");
        dataTypes.put("date_without_tz", "date");
        dataTypes.put("date", "date");
        dataTypes.put("time", "time with time zone");
        dataTypes.put("timestamp", "timestamp with time zone");
        dataTypes.put("small_blob", "bytea");
        dataTypes.put("medium_blob", "bytea");
        dataTypes.put("large_blob", "bytea");
        dataTypes.put("xml", "xml");
        dataTypes.put("boolean", "boolean");

    }

    @Override
    public String createDDLScript(JSONObject platformIndependentModel) {
        String msg = "";
        JSONObject Database_Design = platformIndependentModel.getJSONObject("Database_Design");
        String rip_sql_database_name = Database_Design.getString("database_name");
        msg = msg + "CREATE DATABASE " + rip_sql_database_name + ";\n\n\n";

        JSONArray rip_sql_tables = Database_Design.getJSONArray("tables");

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = (JSONObject) rip_sql_tables.get(i);
            String table_script = this.createTable(rip_sql_table);
            msg = msg + table_script + "\n\n";
        }

        JSONArray rip_sql_forign_keys = Database_Design.getJSONArray("foreign_keys");
        for (int i = 0; i < rip_sql_forign_keys.length(); i++) {
            JSONObject rip_sql_forign_key = (JSONObject) rip_sql_forign_keys.get(i);
            String fkry_script = this.foreignKeys(rip_sql_forign_key);
            msg = msg + fkry_script + "\n\n";
        }

        return msg;
    }

    @Override
    public String createTable(JSONObject rip_sql_table) {
        String tble_script = "CREATE TABLE ";

        ArrayList pkey = new ArrayList();
        int pkey_counter = 0;

        ArrayList uniquekey = new ArrayList();
        int uniquekey_counter = 0;
        String rip_sql_table_name = rip_sql_table.getString("table_name");
        tble_script = tble_script + rip_sql_table_name + "(\n";
        JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");
        for (int i = 0; i < rip_sql_fileds.length() - 1; i++) {
            JSONObject rip_sql_filed = (JSONObject) rip_sql_fileds.get(i);
            String rip_sql_feild_name = rip_sql_filed.getString("field_name");

            String rip_sql_data_type = rip_sql_filed.getString("data_type");
            String dsm_datatype = (String) dataTypes.get(rip_sql_data_type);
            boolean rip_sql_auto_incriment = rip_sql_filed.getBoolean("auto_increment");
            if (rip_sql_auto_incriment) {
                dsm_datatype = " serial ";
            }

            tble_script = tble_script + rip_sql_feild_name + " " + dsm_datatype;

            boolean rip_sql_not_null = rip_sql_filed.getBoolean("not_null");
            if (rip_sql_not_null) {
                tble_script = tble_script + " NOT NULL ";
            }

            boolean rip_sql_primary_key = rip_sql_filed.getBoolean("primary_key");
            if (rip_sql_primary_key) {
                pkey.add(pkey_counter, rip_sql_feild_name);
                pkey_counter++;
            }

            boolean rip_sql_unique = rip_sql_filed.getBoolean("unique");
            if (rip_sql_unique) {
                uniquekey.add(uniquekey_counter, rip_sql_feild_name);
                uniquekey_counter++;
            }
            tble_script = tble_script + ",\n";
        }
        //loop pealing
        JSONObject rip_sql_filed = (JSONObject) rip_sql_fileds.get(rip_sql_fileds.length() - 1);
        String rip_sql_feild_name = rip_sql_filed.getString("field_name");

        String rip_sql_data_type = rip_sql_filed.getString("data_type");
        String dsm_datatype = (String) dataTypes.get(rip_sql_data_type);

        boolean rip_sql_auto_incriment = rip_sql_filed.getBoolean("auto_increment");
        if (rip_sql_auto_incriment) {
            dsm_datatype = " serial ";
        }

        tble_script = tble_script + rip_sql_feild_name + " " + dsm_datatype;

        boolean rip_sql_not_null = rip_sql_filed.getBoolean("not_null");
        if (rip_sql_not_null) {
            tble_script = tble_script + " NOT NULL ";
        }

        boolean rip_sql_primary_key = rip_sql_filed.getBoolean("primary_key");
        if (rip_sql_primary_key) {
            pkey.add(pkey_counter, rip_sql_feild_name);
            pkey_counter++;
        }

        boolean rip_sql_unique = rip_sql_filed.getBoolean("unique");
        if (rip_sql_unique) {
            uniquekey.add(uniquekey_counter, rip_sql_feild_name);
            uniquekey_counter++;
        }

        if (pkey_counter > 0) {
            tble_script = tble_script + ",\n";
        }
        //primery key adding
        tble_script = tble_script + "CONSTRAINT " + rip_sql_table_name + "_pkey PRIMARY KEY (";
        for (int i = 0; i < pkey_counter - 1; i++) {
            String pkeyFeild = (String) pkey.get(i);
            tble_script = tble_script + pkeyFeild + ",";
        }
        String pkeyFeild = (String) pkey.get(pkey_counter - 1);
        tble_script = tble_script + pkeyFeild + ")";
        if (uniquekey_counter > 0) {
            tble_script = tble_script + ",\n";

            for (int i = 0; i < uniquekey_counter - 1; i++) {
                String uniquekeyFeild = (String) uniquekey.get(i);
                tble_script = tble_script + "CONSTRAINT " + uniquekeyFeild + "_ukey UNIQUE (" + uniquekeyFeild + " ),\n";
            }
            String uniquekeyFeild = (String) uniquekey.get(uniquekey_counter - 1);
            tble_script = tble_script + "CONSTRAINT " + uniquekeyFeild + "_ukey UNIQUE (" + uniquekeyFeild + " )";
        }
        tble_script = tble_script + "\n);";

        return tble_script;
    }

    @Override
    public String foreignKeys(JSONObject rip_sql_forign_key) {
        String rip_sql_fk_name = rip_sql_forign_key.getString("fk_name");
        String rip_sql_base_table = rip_sql_forign_key.getString("base_table");
        String rip_sql_bt_feild_name = rip_sql_forign_key.getString("bt_field_name");
        String rip_sql_reference_table = rip_sql_forign_key.getString("reference_table");
        String rip_sql_rt_feild_name = rip_sql_forign_key.getString("rt_field_name");
        String rip_sql_on_delete = rip_sql_forign_key.getString("on_delete");
        String rip_sql_on_update = rip_sql_forign_key.getString("on_update");

        String foreignKey_script = "ALTER TABLE " + rip_sql_base_table + "\n"
                + "ADD CONSTRAINT " + rip_sql_fk_name + "\n"
                + "FOREIGN KEY ( " + rip_sql_bt_feild_name + ")\n"
                + "REFERENCES " + rip_sql_reference_table + "( " + rip_sql_rt_feild_name + " )"
                + "ON DELETE " + rip_sql_on_delete + "\n"
                + "ON UPDATE " + rip_sql_on_update + ";";
        ;

        return foreignKey_script;
    }

    @Override
    public String createDMLScript(JSONObject platformIndependentModel) {
        String script = "";
        JSONObject Database_Design = platformIndependentModel.getJSONObject("Database_Design");
        JSONArray rip_sql_tables = Database_Design.getJSONArray("tables");

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = (JSONObject) rip_sql_tables.get(i);
            String insert_script = this.createInsertQuery(rip_sql_table);
            script = script + insert_script + "\n";
        }

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = (JSONObject) rip_sql_tables.get(i);
            String update_script = this.createUpdateQuery(rip_sql_table);
            script = script + update_script + "\n";
        }

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = (JSONObject) rip_sql_tables.get(i);
            String delete_script = this.createDeleteQuery(rip_sql_table);
            script = script + delete_script + "\n";
        }

        return script;
    }

    @Override
    public String createInsertQuery(JSONObject rip_sql_table) {
        String insert_script = "INSERT INTO ";
        String rip_sql_table_name = rip_sql_table.getString("table_name");
        insert_script = insert_script + rip_sql_table_name + "( ";
        JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");
        int valueCounter = 0;
        for (int i = 0; i < rip_sql_fileds.length(); i++) {
            JSONObject rip_sql_filed = (JSONObject) rip_sql_fileds.get(i);
            String rip_sql_feild_name = rip_sql_filed.getString("field_name");

            boolean rip_sql_auto_incriment = rip_sql_filed.getBoolean("auto_increment");
            if (!rip_sql_auto_incriment) {
                insert_script = insert_script + rip_sql_feild_name + ", ";
                valueCounter++;
            }
        }
        insert_script = insert_script.substring(0, insert_script.length() - 2);
        insert_script = insert_script + ") VALUES ( ";
        for (int i = 0; i < valueCounter - 1; i++) {
            insert_script = insert_script + " ?,";
        }
        insert_script = insert_script + " ? );\n\n";
        return insert_script;
    }

    @Override
    public String createUpdateQuery(JSONObject rip_sql_table) {
        String script = "UPDATE ";
        String rip_sql_table_name = rip_sql_table.getString("table_name");
        script = script + rip_sql_table_name + " SET ";
        JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");
        ArrayList pkey = new ArrayList();

        int valueCounter = 0;
        int pkeyCounter = 0;
        for (int i = 0; i < rip_sql_fileds.length(); i++) {
            JSONObject rip_sql_filed = (JSONObject) rip_sql_fileds.get(i);
            String rip_sql_feild_name = rip_sql_filed.getString("field_name");

            boolean rip_sql_auto_incriment = rip_sql_filed.getBoolean("auto_increment");
            boolean rip_sql_primary_key = rip_sql_filed.getBoolean("primary_key");
            if (rip_sql_primary_key) {
                pkey.add(rip_sql_feild_name);
                pkeyCounter++;
            } else if (!rip_sql_auto_incriment) {
                script = script + rip_sql_feild_name + " = ? , ";
                valueCounter++;
            }
        }
        script = script.substring(0, script.length() - 2);

        if (!pkey.isEmpty()) {
            script = script + " WHERE  ";
            for (int j = 0; j < pkeyCounter; j++) {
                if (j == pkey.size() - 1) {
                    script = script + pkey.get(j).toString() + " = ? ";
                } else {
                    script = script + pkey.get(j).toString() + " = ? and ";
                }
            }
        }

        script = script + ";\n\n";
        if (valueCounter == 0) {
            script = "";
        }

        return script;
    }

    @Override
    public String createDeleteQuery(JSONObject rip_sql_table) {
        String script = "DELETE FROM ";
        String rip_sql_table_name = rip_sql_table.getString("table_name");
        script = script + rip_sql_table_name + " WHERE  ";
        JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");

        int pkeyCounter = 0;
        for (int i = 0; i < rip_sql_fileds.length(); i++) {
            JSONObject rip_sql_filed = (JSONObject) rip_sql_fileds.get(i);
            String rip_sql_feild_name = rip_sql_filed.getString("field_name");
            boolean rip_sql_primary_key = rip_sql_filed.getBoolean("primary_key");
            if (rip_sql_primary_key) {
                if (pkeyCounter == 0) {
                    script = script + rip_sql_feild_name + " = ? ";
                } else {
                    script = script + " and " + rip_sql_feild_name + " = ? ";
                }
                pkeyCounter++;
            }
        }
        script = script + ";\n\n";
        return script;
    }

    @Override
    public String createDQLScript(JSONObject platformIndependentModel) {
        String script = "";
        JSONObject Database_Design = platformIndependentModel.getJSONObject("Database_Design");
        JSONArray rip_sql_tables = Database_Design.getJSONArray("tables");

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = (JSONObject) rip_sql_tables.get(i);
            String insert_script = this.createInsertQuery(rip_sql_table);
            script = script + insert_script + "\n";
        }

        return script;

    }

    @Override
    public String createSelectQuery(JSONObject rip_sql_table) {
        String script = "Select * FROM ";
        String rip_sql_table_name = rip_sql_table.getString("table_name");
        script = script + rip_sql_table_name;
        return script;
    }

    public static void main(String[] args) {
        //create your class object name as databaseobject
        PGSQLScriptGen databaseobject = new PGSQLScriptGen();

        //this is same for every model
        JsonFileReader jsonFileReader = JsonFileReader.getInstance();
        JSONObject pim = jsonFileReader.getplatformIndependentModel();
        String ddl_script = databaseobject.createDDLScript(pim);
        String dml_script = databaseobject.createDMLScript(pim);
        String dql_script = databaseobject.createDQLScript(pim);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_BEGIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
        System.out.println(ddl_script);
        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_DML_BEGIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(dml_script);
        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_DML_END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_DQL_BEGIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(dql_script);
        System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_DQL_END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

}
