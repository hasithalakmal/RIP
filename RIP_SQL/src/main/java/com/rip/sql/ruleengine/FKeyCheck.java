package com.rip.sql.ruleengine;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class FKeyCheck {

    public String controler(String sample_string) {
        String msg_fk = "success";
        JSONObject example_1 = new JSONObject(sample_string);
        JSONArray rip_sql_tables = example_1.getJSONArray("tables");
        JSONArray rip_sql_forign_keys = example_1.getJSONArray("foreign_keys");

        msg_fk = this.fk_check(rip_sql_tables, rip_sql_forign_keys);
        return msg_fk;
    }

    public String fk_check(JSONArray rip_sql_tables, JSONArray rip_sql_forign_keys) {
        String msg_fk = "success";

        for (int i = 0; i < rip_sql_forign_keys.length(); i++) {
            JSONObject rip_sql_forign_key = rip_sql_forign_keys.getJSONObject(i);
            String forign_key_bt = rip_sql_forign_key.getString("base_table");
            String forign_key_rt = rip_sql_forign_key.getString("reference_table");
            String forign_key_bf = rip_sql_forign_key.getString("bt_field_name");
            String forign_key_rf = rip_sql_forign_key.getString("rt_field_name");

            //check base table attributes
            boolean isBasetableExist = false;
            boolean isBaseFeildExist = false;
            String baseTableDataType = "base";
            for (int j = 0; j < rip_sql_tables.length(); j++) {

                JSONObject rip_sql_table = rip_sql_tables.getJSONObject(j);
                String table_name = rip_sql_table.getString("table_name");
                JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");

                if (forign_key_bt.equals(table_name)) {
                    isBasetableExist = true;
                    for (int k = 0; k < rip_sql_fileds.length(); k++) {
                        JSONObject rip_sql_field = rip_sql_fileds.getJSONObject(k);
                        String field_name = rip_sql_field.getString("field_name");
                        if (field_name.equals(forign_key_bf)) {
                            isBaseFeildExist = true;
                            baseTableDataType = rip_sql_field.getString("data_type");
                        }
                    }

                }

            }

            //reference table check
            boolean isReftableExsist = false;
            boolean isRefFeildExsist = false;
            String errorTable = "";
            String referenceTableDataType = "ref";
            for (int j = 0; j < rip_sql_tables.length(); j++) {
                JSONObject rip_sql_table = rip_sql_tables.getJSONObject(j);
                String table_name = rip_sql_table.getString("table_name");
                JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");

                if (forign_key_rt.equals(table_name)) {
                    isReftableExsist = true;
                    for (int k = 0; k < rip_sql_fileds.length(); k++) {
                        JSONObject rip_sql_field = rip_sql_fileds.getJSONObject(k);
                        String field_name = rip_sql_field.getString("field_name");

                        if (field_name.equals(forign_key_rf)) {
                            isRefFeildExsist = true;
                            referenceTableDataType = rip_sql_field.getString("data_type");

                        }
                    }

                }

            }

            if (!isBasetableExist) {
                return "Base Table Does not Exsist";
            } else if (!isBaseFeildExist) {
                return "Base Table Colomn Does not Exsist";
            } else if (!isReftableExsist) {
                return "Refence Table Does not Exsist";
            } else if (!isRefFeildExsist) {
                return "Refence Table Colomn Does not Exsist";
            } else if (!baseTableDataType.equals(referenceTableDataType)) {
                return "Data Types Should be Same in " + forign_key_bt + " " + forign_key_bf + " and " + forign_key_rt + " " + forign_key_rf;
            }

            return msg_fk;
        }
        return msg_fk;
    }
}
