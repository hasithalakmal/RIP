//Database name = table name
//Database name = field name
//Field name = Table name
package com.rip.sql.ruleengine;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class DBRuleValidator {

    public String controler(String sample_string) {
        String msg = "success";
        JSONObject example_1 = new JSONObject(sample_string);
        String rip_sql_database_name = example_1.getString("database_name");
        JSONArray rip_sql_tables = example_1.getJSONArray("tables");

        msg = DBRuleValidator.name_validation(rip_sql_database_name, rip_sql_tables);
        return msg;

    }

    public static String name_validation(String db_name, JSONArray rip_sql_tables) {
        String msg = "success";
        //db name == table name
        for (int i = 0; i < rip_sql_tables.length(); i++) {
            JSONObject rip_sql_table = rip_sql_tables.getJSONObject(i);
            String table_name = rip_sql_table.getString("table_name");
            JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("fields");

            for (int j = 0; j < rip_sql_fileds.length(); j++) {

                JSONObject rip_sql_filed = rip_sql_fileds.getJSONObject(j);
                String rip_sql_feild_name = rip_sql_filed.getString("field_name");

                if (db_name.equals(rip_sql_feild_name)) {
                    msg = "Database name should not equal to field name. Error field is " + rip_sql_feild_name;
                    return msg;
                }
                if (db_name.equals(table_name)) {
                    msg = "Database name should not equal to table name. Error table is " + table_name;
                    return msg;
                }
                if (rip_sql_feild_name.equals(table_name)) {
                    msg = "Field name should not equal to table name. Error table is " + table_name;
                    return msg;
                }

            }

        }

        return msg;
    }

}
