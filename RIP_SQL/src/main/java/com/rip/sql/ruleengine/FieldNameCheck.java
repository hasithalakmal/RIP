/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.ruleengine;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class FieldNameCheck {

    public String controler(String sample_string) {
        String msg_fv = "success";
        JSONObject example_1 = new JSONObject(sample_string);
        String rip_sql_database_name = example_1.getString("rip_sql_database_name");
        JSONArray rip_sql_tables = example_1.getJSONArray("rip_sql_tables");
        msg_fv = FieldNameCheck.field_name_check(rip_sql_tables);
        return msg_fv;

    }

    public static String field_name_check(JSONArray rip_sql_tables) {
        String msg_fv = "success";

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = rip_sql_tables.getJSONObject(i);
            String table_name = rip_sql_table.getString("rip_sql_table_name");
            JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("rip_sql_fileds");

            for (int j = 0; j < rip_sql_fileds.length() - 1; j++) {

                JSONObject rip_sql_filed = rip_sql_fileds.getJSONObject(j);
                String rip_sql_feild_name = rip_sql_filed.getString("rip_sql_feild_name");

                for (int k = j + 1; k < rip_sql_fileds.length(); k++) {
                    JSONObject next_rip_sql_filed = rip_sql_fileds.getJSONObject(k);
                    String next_rip_sql_feild_name = next_rip_sql_filed.getString("rip_sql_feild_name");

                    if (rip_sql_feild_name.equals(next_rip_sql_feild_name)) {

                        msg_fv = "Field name should not equal to field name. Error table is " + table_name + rip_sql_feild_name;
                        return msg_fv;
                    }

                }

            }

        }

        return msg_fv;
    }
}
