//Table name check
package com.rip.sql.ruleengine;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class TableNameCheck {

    public String controler(String sample_string) {
        String msg = "success";
        JSONObject example_1 = new JSONObject(sample_string);
        JSONArray rip_sql_tables = example_1.getJSONArray("rip_sql_tables");
        msg = TableNameCheck.table_name_check(rip_sql_tables);
        return msg;
    }

    public static String table_name_check(JSONArray rip_sql_tables) {
        String msg = "success";

        for (int i = 0; i < rip_sql_tables.length()-1; i++) {
            JSONObject rip_sql_table = rip_sql_tables.getJSONObject(i);

            for (int j = i; j <= rip_sql_tables.length()-2; j++) {
                JSONObject next_table = rip_sql_tables.getJSONObject(j + 1);
                if (rip_sql_table == next_table) {
                    return msg = "Table name should not be duplicate";
                }

            }
        }
        return msg;
    }
}
