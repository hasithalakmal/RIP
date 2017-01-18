/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hasitha Lakmal
 */
@Service("WordFileGenarator")
public class WordFileGenarator {

    public static void main(String[] args) throws IOException {

        String sample_string1 = "{\n"
                + "    \"rip_sql_database_name\": \"testdb\",\n"
                + "    \"rip_sql_tables\": [\n"
                + "      {\n"
                + "        \"rip_sql_table_name\": \"employee\",\n"
                + "        \"rip_sql_fileds\": [\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"emp_id\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": true,\n"
                + "            \"rip_sql_auto_incriment\": true,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"emp_name\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"emp_phone\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": false,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"emp_address\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": false,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"emp_dep_id\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          }\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_table_name\": \"vision\",\n"
                + "        \"rip_sql_fileds\": [\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"vision_id\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": true,\n"
                + "            \"rip_sql_auto_incriment\": true,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"vision_name\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"vision_des\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          }\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_table_name\": \"department\",\n"
                + "        \"rip_sql_fileds\": [\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"dep_id\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": true,\n"
                + "            \"rip_sql_auto_incriment\": true,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"dep_name\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"dep_des\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"dep_vis\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          }\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_table_name\": \"project\",\n"
                + "        \"rip_sql_fileds\": [\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"pro_id\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": true,\n"
                + "            \"rip_sql_auto_incriment\": true,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"pro_name\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"pro_des\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_string\",\n"
                + "            \"rip_sql_primary_key\": false,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": false\n"
                + "          }\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_table_name\": \"employee_workson_project\",\n"
                + "        \"rip_sql_fileds\": [\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"emp_id_ref\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": true,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          },\n"
                + "          {\n"
                + "            \"rip_sql_feild_name\": \"pro_id_ref\",\n"
                + "            \"rip_sql_data_type\": \"rip_sql_mediam_integer\",\n"
                + "            \"rip_sql_primary_key\": true,\n"
                + "            \"rip_sql_auto_incriment\": false,\n"
                + "            \"rip_sql_not_null\": true,\n"
                + "            \"rip_sql_unique\": true\n"
                + "          }\n"
                + "        ]\n"
                + "      }\n"
                + "    ],\n"
                + "    \"rip_sql_forign_keys\": [\n"
                + "      {\n"
                + "        \"rip_sql_fk_name\": \"emp_fk\",\n"
                + "        \"rip_sql_base_table\": \"employee_workson_project\",\n"
                + "        \"rip_sql_bt_feild_name\": \"emp_id_ref\",\n"
                + "        \"rip_sql_reference_table\": \"employee\",\n"
                + "        \"rip_sql_rt_feild_name\": \"emp_id\",\n"
                + "        \"rip_sql_on_delete\": \"CASCADE\",\n"
                + "        \"rip_sql_on_update\": \"CASCADE\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_fk_name\": \"pro_fk\",\n"
                + "        \"rip_sql_base_table\": \"employee_workson_project\",\n"
                + "        \"rip_sql_bt_feild_name\": \"pro_id_ref\",\n"
                + "        \"rip_sql_reference_table\": \"project\",\n"
                + "        \"rip_sql_rt_feild_name\": \"pro_id\",\n"
                + "        \"rip_sql_on_delete\": \"CASCADE\",\n"
                + "        \"rip_sql_on_update\": \"CASCADE\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_fk_name\": \"dep_fk\",\n"
                + "        \"rip_sql_base_table\": \"employee\",\n"
                + "        \"rip_sql_bt_feild_name\": \"emp_dep_id\",\n"
                + "        \"rip_sql_reference_table\": \"department\",\n"
                + "        \"rip_sql_rt_feild_name\": \"dep_id\",\n"
                + "        \"rip_sql_on_delete\": \"CASCADE\",\n"
                + "        \"rip_sql_on_update\": \"CASCADE\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"rip_sql_fk_name\": \"vision_fk\",\n"
                + "        \"rip_sql_base_table\": \"department\",\n"
                + "        \"rip_sql_bt_feild_name\": \"dep_vis\",\n"
                + "        \"rip_sql_reference_table\": \"vision\",\n"
                + "        \"rip_sql_rt_feild_name\": \"vision_id\",\n"
                + "        \"rip_sql_on_delete\": \"CASCADE\",\n"
                + "        \"rip_sql_on_update\": \"CASCADE\"\n"
                + "      }\n"
                + "    ]\n"
                + "  }";

        FileOutputStream out = null;
        try {
            JSONObject example_1 = new JSONObject(sample_string1);
            String rip_sql_database_name = example_1.getString("rip_sql_database_name");
            JSONArray rip_sql_tables = example_1.getJSONArray("rip_sql_tables");
            JSONArray rip_sql_fks = example_1.getJSONArray("rip_sql_forign_keys");
            //Blank Document
            XWPFDocument document = new XWPFDocument();

            //create Heading
            XWPFParagraph heading = document.createParagraph();
            XWPFRun paragraphOneRunOne = heading.createRun();
            paragraphOneRunOne.setBold(true);
            paragraphOneRunOne.setItalic(false);
            paragraphOneRunOne.setUnderline(UnderlinePatterns.DASH);
            paragraphOneRunOne.setFontSize(20);
            paragraphOneRunOne.setUnderline(UnderlinePatterns.WAVE);
            paragraphOneRunOne.setText("Project Database Summary");
            paragraphOneRunOne.addBreak();

            XWPFParagraph s = document.createParagraph();
            XWPFRun S = s.createRun();
            S.setText("This Document Contain basic structure of the Database and contain all the table values ,foreign keys ,relations ,fields values and all the Databases which can use for this system. ");

            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Database name :  " + rip_sql_database_name);
            run.setBold(true);

            XWPFParagraph title = document.createParagraph();
            XWPFRun Title = title.createRun();
            Title.setText("Table List");
            Title.getUnderline();
            Title.setBold(true);
            Title.setTextPosition(10);

            XWPFParagraph q = document.createParagraph();
            XWPFRun Q = q.createRun();
            Q.setText("This table contain all the tables , Primary keys & no of fields it has.");

            //create topic 
//            XWPFParagraph heading1 = document.createParagraph();
//            XWPFRun paragraphOneRunOne1 = heading1.createRun();
//            paragraphOneRunOne1.setBold(true);
//            paragraphOneRunOne1.setFontSize(14);
//            paragraphOneRunOne1.setText("Table Name");
//            
//            XWPFParagraph heading2 = document.createParagraph();
//            XWPFRun paragraphOneRunOne2 = heading2.createRun();
//            paragraphOneRunOne2.setBold(true);
//            paragraphOneRunOne2.setFontSize(14);
//            paragraphOneRunOne2.setText("Number Of Feilds");
//            
//            XWPFParagraph heading3 = document.createParagraph();
//            XWPFRun paragraphOneRunOne3 = heading3.createRun();
//            paragraphOneRunOne3.setBold(true);
//            paragraphOneRunOne3.setFontSize(14);
//            paragraphOneRunOne3.setText("Primary Key");
//            
            //create table
            XWPFTable table = document.createTable();
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("Table Name");
            tableRowOne.addNewTableCell().setText("Number Of Feilds");
            tableRowOne.addNewTableCell().setText("Primary Key");

            for (int i = 1; i < rip_sql_tables.length() + 1; i++) {
                JSONObject rip_sql_table = rip_sql_tables.getJSONObject(i - 1);
                String table_name = rip_sql_table.getString("rip_sql_table_name");
                JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("rip_sql_fileds");
                String pkeyList = "";
                for (int j = 0; j < rip_sql_fileds.length(); j++) {
                    JSONObject rip_sql_filed = rip_sql_fileds.getJSONObject(j);
                    boolean rip_sql_primary_key = rip_sql_filed.getBoolean("rip_sql_primary_key");
                    if (rip_sql_primary_key) {
                        pkeyList = pkeyList + rip_sql_filed.getString("rip_sql_feild_name") + ",";
                    }
                }

                XWPFTableRow tableRowTwo = table.createRow();
                tableRowTwo.getCell(0).setText(table_name);
                tableRowTwo.getCell(1).setText(Integer.toString(rip_sql_fileds.length()));
                tableRowTwo.getCell(2).setText(pkeyList);
            }
            //create first row
//            XWPFTableRow tableRowOne = table.getRow(0);
//            //tableRowOne.getCell(0).setParagraph(heading);
//            tableRowOne.getCell(0).setText("table name");
//            tableRowOne.addNewTableCell().setText("no of fields");
//            tableRowOne.addNewTableCell().setText("primary key");
//            }
//            //create second row
//            XWPFTableRow tableRowTwo = table.createRow();
//            tableRowTwo.getCell(0).setText("col one, row two");
//            tableRowTwo.getCell(1).setText("col two, row two");
//            tableRowTwo.getCell(2).setText("col three, row two");
//
//            //create third row
//            XWPFTableRow tableRowThree = table.createRow();
//            tableRowThree.getCell(0).setText("col one, row three");
//            tableRowThree.getCell(1).setText("col two, row three");
//            tableRowThree.getCell(2).setText("col three, row three");

            //Write the Document in file system
            XWPFParagraph a = document.createParagraph();
            XWPFRun A = a.createRun();
            A.addBreak();
            //relation mapping
            A.setText("Mapping Of Relation");
            A.setBold(true);

            XWPFParagraph d = document.createParagraph();
            XWPFRun D = d.createRun();
            D.setText("This table contain Foreign key , Base table , Reference Table , Base colum , Reference colum . ");

            XWPFTable tble = document.createTable();
            XWPFTableRow row1 = tble.getRow(0);
            row1.getCell(0).setText("Forign key");
            row1.addNewTableCell().setText("Base Table");
            row1.addNewTableCell().setText("Reference Table");
            row1.addNewTableCell().setText("Base Colum");
            row1.addNewTableCell().setText("Reference Colum");

            for (int i = 0; i < rip_sql_fks.length(); i++) {

                JSONObject rip_sql_forign_key = rip_sql_fks.getJSONObject(i);
                String fk_name = rip_sql_forign_key.getString("rip_sql_fk_name");
                String forign_key_bt = rip_sql_forign_key.getString("rip_sql_base_table");
                String forign_key_rt = rip_sql_forign_key.getString("rip_sql_reference_table");
                String forign_key_bf = rip_sql_forign_key.getString("rip_sql_bt_feild_name");
                String forign_key_rf = rip_sql_forign_key.getString("rip_sql_rt_feild_name");

                XWPFTableRow row = tble.createRow();
                row.getCell(0).setText(fk_name);
                row.getCell(1).setText(forign_key_bt);
                row.getCell(2).setText(forign_key_rt);
                row.getCell(3).setText(forign_key_bf);
                row.getCell(4).setText(forign_key_rf);
            }
            //----------------------------------------------------------------------    
            XWPFParagraph b = document.createParagraph();
            XWPFRun B = b.createRun();
            B.addBreak();
            B.setText("Data Dictionary");
            B.setBold(true);

            XWPFParagraph z = document.createParagraph();
            XWPFRun Z = z.createRun();
            Z.setText("This Table contain Table name , Field and field value of the Database.");

            for (int i = 0; i < rip_sql_tables.length(); i++) {
                JSONObject rip_sql_table = rip_sql_tables.getJSONObject(i);
                String table_name = rip_sql_table.getString("rip_sql_table_name");
                JSONArray rip_sql_fileds = rip_sql_table.getJSONArray("rip_sql_fileds");

                XWPFParagraph tn = document.createParagraph();
                XWPFRun TN = tn.createRun();
                TN.setText("Strucrure of " + table_name);
                TN.setBold(true);

                XWPFTable tble2 = document.createTable();
                XWPFTableRow row2 = tble2.getRow(0);
                row2.getCell(0).setText("field name");
                row2.addNewTableCell().setText("data type");
                row2.addNewTableCell().setText("primary key");
                row2.addNewTableCell().setText("auto increment");
                row2.addNewTableCell().setText("not null");
                row2.addNewTableCell().setText("unique");

                for (int j = 0; j < rip_sql_fileds.length(); j++) {
                    JSONObject rip_sql_field = rip_sql_fileds.getJSONObject(j);
                    String field_name = rip_sql_field.getString("rip_sql_feild_name");
                    String data_type = rip_sql_field.getString("rip_sql_data_type");
                    boolean rip_sql_primary_key = rip_sql_field.getBoolean("rip_sql_primary_key");
                    boolean rip_sql_auto_increment = rip_sql_field.getBoolean("rip_sql_auto_incriment");
                    boolean rip_sql_not_null = rip_sql_field.getBoolean("rip_sql_not_null");
                    boolean rip_sql_unique = rip_sql_field.getBoolean("rip_sql_unique");

                    XWPFTableRow row3 = tble2.createRow();
                    row3.getCell(0).setText(field_name);
                    row3.getCell(1).setText(data_type);
                    row3.getCell(2).setText(Boolean.toString(rip_sql_primary_key));
                    row3.getCell(3).setText(Boolean.toString(rip_sql_auto_increment));
                    row3.getCell(4).setText(Boolean.toString(rip_sql_not_null));
                    row3.getCell(5).setText(Boolean.toString(rip_sql_unique));

                }

            }
            XWPFParagraph f = document.createParagraph();
            XWPFRun F = f.createRun();
            F.setText("DIM Data Types");
            F.setBold(true);

            XWPFParagraph g = document.createParagraph();
            XWPFRun G = g.createRun();
            G.setText("This table cointain all the Databases and its datatypes which can use for the system.");

            XWPFTable tble3 = document.createTable();
            XWPFTableRow row3 = tble3.getRow(0);
            row3.getCell(0).setText("DIM Data Type");
            row3.addNewTableCell().setText("MySql");
            row3.addNewTableCell().setText("MsSql");
            row3.addNewTableCell().setText("PgSql");
            row3.addNewTableCell().setText("Oracal");

            XWPFTableRow row5 = tble3.createRow();
            row5.getCell(0).setText("Short String");
            row5.getCell(1).setText("xxxxxxx");
            row5.getCell(2).setText("xxxxxxx");
            row5.getCell(3).setText("xxxxxxx");
            row5.getCell(4).setText("xxxxxxx");

            XWPFTableRow row6 = tble3.createRow();
            row6.getCell(0).setText("Mediam String");
            row6.getCell(1).setText("xxxxxxx");
            row6.getCell(2).setText("xxxxxxx");
            row6.getCell(3).setText("xxxxxxx");
            row6.getCell(4).setText("xxxxxxx");

            XWPFTableRow row7 = tble3.createRow();
            row7.getCell(0).setText("Long String");
            row7.getCell(1).setText("xxxxxxx");
            row7.getCell(2).setText("xxxxxxx");
            row7.getCell(3).setText("xxxxxxx");
            row7.getCell(4).setText("xxxxxxx");

            XWPFTableRow row8 = tble3.createRow();
            row8.getCell(0).setText("Small Integer");
            row8.getCell(1).setText("xxxxxxx");
            row8.getCell(2).setText("xxxxxxx");
            row8.getCell(3).setText("xxxxxxx");
            row8.getCell(4).setText("xxxxxxx");

            XWPFTableRow row9 = tble3.createRow();
            row9.getCell(0).setText("Mediam  Integer");
            row9.getCell(1).setText("xxxxxxx");
            row9.getCell(2).setText("xxxxxxx");
            row9.getCell(3).setText("xxxxxxx");
            row9.getCell(4).setText("xxxxxxx");

            XWPFTableRow row10 = tble3.createRow();
            row10.getCell(0).setText("Big Integer");
            row10.getCell(1).setText("xxxxxxx");
            row10.getCell(2).setText("xxxxxxx");
            row10.getCell(3).setText("xxxxxxx");
            row10.getCell(4).setText("xxxxxxx");

            XWPFTableRow row11 = tble3.createRow();
            row11.getCell(0).setText("Float(M,D)");
            row11.getCell(1).setText("xxxxxxx");
            row11.getCell(2).setText("xxxxxxx");
            row11.getCell(3).setText("xxxxxxx");
            row11.getCell(4).setText("xxxxxxx");

            XWPFTableRow row12 = tble3.createRow();
            row12.getCell(0).setText("Date With TZ");
            row12.getCell(1).setText("xxxxxxx");
            row12.getCell(2).setText("xxxxxxx");
            row12.getCell(3).setText("xxxxxxx");
            row12.getCell(4).setText("xxxxxxx");

            XWPFTableRow row13 = tble3.createRow();
            row13.getCell(0).setText("Date Without TZ");
            row13.getCell(1).setText("xxxxxxx");
            row13.getCell(2).setText("xxxxxxx");
            row13.getCell(3).setText("xxxxxxx");
            row13.getCell(4).setText("xxxxxxx");

            XWPFTableRow row14 = tble3.createRow();
            row14.getCell(0).setText("Date");
            row14.getCell(1).setText("xxxxxxx");
            row14.getCell(2).setText("xxxxxxx");
            row14.getCell(3).setText("xxxxxxx");
            row14.getCell(4).setText("xxxxxxx");

            XWPFTableRow row15 = tble3.createRow();
            row15.getCell(0).setText("Time");
            row15.getCell(1).setText("xxxxxxx");
            row15.getCell(2).setText("xxxxxxx");
            row15.getCell(3).setText("xxxxxxx");
            row15.getCell(4).setText("xxxxxxx");

            XWPFTableRow row16 = tble3.createRow();
            row16.getCell(0).setText("TimeStamp");
            row16.getCell(1).setText("xxxxxxx");
            row16.getCell(2).setText("xxxxxxx");
            row16.getCell(3).setText("xxxxxxx");
            row16.getCell(4).setText("xxxxxxx");

            XWPFTableRow row17 = tble3.createRow();
            row17.getCell(0).setText("Small Blob");
            row17.getCell(1).setText("xxxxxxx");
            row17.getCell(2).setText("xxxxxxx");
            row17.getCell(3).setText("xxxxxxx");
            row17.getCell(4).setText("xxxxxxx");

            XWPFTableRow row18 = tble3.createRow();
            row18.getCell(0).setText("Mediam Blob");
            row18.getCell(1).setText("xxxxxxx");
            row18.getCell(2).setText("xxxxxxx");
            row18.getCell(3).setText("xxxxxxx");
            row18.getCell(4).setText("xxxxxxx");

            XWPFTableRow row19 = tble3.createRow();
            row19.getCell(0).setText("Large Blob");
            row19.getCell(1).setText("xxxxxxx");
            row19.getCell(2).setText("xxxxxxx");
            row19.getCell(3).setText("xxxxxxx");
            row19.getCell(4).setText("xxxxxxx");

            XWPFTableRow row20 = tble3.createRow();
            row20.getCell(0).setText("XML");
            row20.getCell(1).setText("xxxxxxx");
            row20.getCell(2).setText("xxxxxxx");
            row20.getCell(3).setText("xxxxxxx");
            row20.getCell(4).setText("xxxxxxx");

            XWPFTableRow row21 = tble3.createRow();
            row21.getCell(0).setText("rip_sql_boolean");
            row21.getCell(1).setText("xxxxxxx");
            row21.getCell(2).setText("xxxxxxx");
            row21.getCell(3).setText("xxxxxxx");
            row21.getCell(4).setText("xxxxxxx");

            out = new FileOutputStream(new File("D:\\Accademic\\4thYearProject\\Dev\\Output\\createdocument.docx"));
            document.write(out);
            out.close();
            System.out.println("createdocument.docx written successully");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordFileGenarator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
