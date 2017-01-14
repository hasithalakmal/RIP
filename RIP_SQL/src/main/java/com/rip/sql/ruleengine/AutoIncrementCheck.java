package com.rip.sql.ruleengine;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class AutoIncrementCheck {

    public String controler(String sample_string) {
        String msg_ai = "success";
        JSONObject example_1 = new JSONObject(sample_string);
        JSONArray rip_sql_tables = example_1.getJSONArray("rip_sql_tables");
        msg_ai = this.auto_increment_check(rip_sql_tables);
        return msg_ai;

    }

    public String auto_increment_check(JSONArray rip_sql_tables) {
        String msg_ai = "success";

        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = rip_sql_tables.getJSONObject(i);
            String table_name = rip_sql_table.getString("rip_sql_table_name");
            JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("rip_sql_fileds");

            for (int j = 0; j < rip_sql_fileds.length(); j++) {
                JSONObject rip_sql_filed = rip_sql_fileds.getJSONObject(j);
                boolean rip_sql_auto_incriment = rip_sql_filed.getBoolean("rip_sql_auto_incriment");
                String rip_sql_data_type = rip_sql_filed.getString("rip_sql_data_type");
                boolean rip_sql_primary_key = rip_sql_filed.getBoolean("rip_sql_primary_key");
                boolean rip_sql_unique = rip_sql_filed.getBoolean("rip_sql_unique");

                if (rip_sql_auto_incriment == true) {
                    if (!("rip_sql_small_lnteger".equals(rip_sql_data_type) || "rip_sql_mediam_integer".equals(rip_sql_data_type) || "rip_sql_big_integer".equals(rip_sql_data_type))) {
                        return msg_ai = table_name + "_table data type should be integer type";
                    }
                    if (!(rip_sql_primary_key || rip_sql_unique)) {
                        return msg_ai = table_name + "_if it is auto increment then it should be unique or PK";
                    }
                }
            }

        }
        return msg_ai;
    }
}
