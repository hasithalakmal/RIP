/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.diagram_tool.controllers;

import com.rip.rip_ui.application.wizard.models.ProjectHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Supun
 */
public class DiagramToolController implements Initializable {

    private ProjectHandler projectHandler;
    private String commandLine;
    private String responseString;
    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField commandInput;

    @FXML
    private Label responseLabel;

    @FXML
    private Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //receive projectHandler instance from previous scene
    public void getProjectHandler(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    @FXML
    public void executeQuery(ActionEvent event) {

        warningLabel.setText("");
        responseLabel.setText("");

        commandLine = commandInput.getText();
        commandLine.toLowerCase();
        String[] splitArray = commandLine.split("\\s+");

        responseString = projectHandler.analyzeCommand(splitArray);

        if (!responseString.equals("")) {
            responseLabel.setText(responseString);
        } else {
            warningLabel.setText("Invalid Input");
        }

        commandInput.clear();
    }

    @FXML
    public void createProject(ActionEvent event) {
        String json = "{\n"
                + "  \"Request_details\": {\n"
                + "    \"project_name\": \"testapp\",\n"
                + "    \"userName\": \"ghasithalakmal@gmail.com\",\n"
                + "    \"version\": \"2.0.5\",\n"
                + "    \"date_time\": \"2015-05-05 22:21:02\"\n"
                + "  },\n"
                + "  \"Technical_Spec\": {\n"
                + "    \"app_id\": 125,\n"
                + "    \"app_name\": \"testapp\",\n"
                + "    \"lanauge\": \"java\",\n"
                + "    \"build_tool\": \"maven\",\n"
                + "    \"rest_framework\": \"play\",\n"
                + "    \"orm_framework\": \"hibanate\",\n"
                + "    \"dbms\": \"mysql\",\n"
                + "    \"test_framework\": \"junit\",\n"
                + "    \"css_framework\": \"bootstrap\",\n"
                + "    \"js_framework\": \"anguler\",\n"
                + "    \"UI_theam\": \"theam1\",\n"
                + "    \"architecture\": \"template3\",\n"
                + "    \"server\": \"apache\",\n"
                + "    \"api_doccumentation_type\": \"type1\",\n"
                + "    \"technical_doccumentation_type\": \"type3\",\n"
                + "    \"test_report_type\": \"type2\",\n"
                + "    \"authontication_type\": \"Oauth2\",\n"
                + "    \"is_need_CRUD_operations_for_all\": true,\n"
                + "    \"is_need_CRUD_forms_for_all\": true\n"
                + "  },\n"
                + "  \"Database_Design\": {\n"
                + "  \"db_name\": \"testdb\",\n"
                + "  \"tables\": [\n"
                + "    {\n"
                + "      \"table_name\": \"student\",\n"
                + "      \"fileds\": [\n"
                + "        {\n"
                + "          \"feild_name\": \"stu_id\",\n"
                + "          \"data_type\": \"int\",\n"
                + "          \"primary_key\": true,\n"
                + "          \"auto_incriment\": true,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": true\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"stu_name\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": false\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"stu_city\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": false\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"phone_number\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": false,\n"
                + "          \"unique\": true\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"address\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": false,\n"
                + "          \"unique\": false\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"stu_school\",\n"
                + "          \"data_type\": \"int\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": false,\n"
                + "          \"unique\": false\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"stu_clz\",\n"
                + "          \"data_type\": \"int\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": false,\n"
                + "          \"unique\": false\n"
                + "        }\n"
                + "      ]\n"
                + "    },\n"
                + "    {\n"
                + "      \"table_name\": \"school\",\n"
                + "      \"fileds\": [\n"
                + "        {\n"
                + "          \"feild_name\": \"sch_id\",\n"
                + "          \"data_type\": \"int\",\n"
                + "          \"primary_key\": true,\n"
                + "          \"auto_incriment\": true,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": true\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"sch_name\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": false\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"sch_city\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": false,\n"
                + "          \"unique\": false\n"
                + "        }\n"
                + "      ]\n"
                + "    },\n"
                + "    {\n"
                + "      \"table_name\": \"class\",\n"
                + "      \"fileds\": [\n"
                + "        {\n"
                + "          \"feild_name\": \"clz_id\",\n"
                + "          \"data_type\": \"int\",\n"
                + "          \"primary_key\": true,\n"
                + "          \"auto_incriment\": true,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": true\n"
                + "        },\n"
                + "        {\n"
                + "          \"feild_name\": \"clz_name\",\n"
                + "          \"data_type\": \"varchar(45)\",\n"
                + "          \"primary_key\": false,\n"
                + "          \"auto_incriment\": false,\n"
                + "          \"not_null\": true,\n"
                + "          \"unique\": false\n"
                + "        }\n"
                + "      ]\n"
                + "    }\n"
                + "  ],\n"
                + "  \"forign_keys\": [\n"
                + "    {\n"
                + "      \"fk_name\": \"stu_sch_fk\",\n"
                + "      \"base_table\": \"student\",\n"
                + "      \"bt_feild_name\": \"stu_school\",\n"
                + "      \"reference_table\": \"school\",\n"
                + "      \"rt_feild_name\": \"sch_id\",\n"
                + "      \"on_delete\": \"CASCADE\",\n"
                + "      \"on_update\": \"CASCADE\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"fk_name\": \"stu_clz_fk\",\n"
                + "      \"base_table\": \"student\",\n"
                + "      \"bt_feild_name\": \"stu_clz\",\n"
                + "      \"reference_table\": \"class\",\n"
                + "      \"rt_feild_name\": \"clz_id\",\n"
                + "      \"on_delete\": \"CASCADE\",\n"
                + "      \"on_update\": \"CASCADE\"\n"
                + "    }\n"
                + "  ]\n"
                + "},\n"
                + "  \"QueryDesign\": {\n"
                + "    \"select_queries\": [\n"
                + "      {\n"
                + "        \"queary_id\": \"s1\",\n"
                + "        \"query\": \"select * from student where stuname = ? and age > ?\",\n"
                + "        \"parameterTypes\": [\n"
                + "          \"varchar(45)\",\n"
                + "          \"int(11)\"\n"
                + "        ],\n"
                + "        \"parameterNames\": [\n"
                + "          \"stu_name\",\n"
                + "          \"stu_age\"\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"s2\",\n"
                + "        \"query\": \"select * from student\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"s3\",\n"
                + "        \"query\": \"select * from student\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"s4\",\n"
                + "        \"query\": \"select * from student\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"s5\",\n"
                + "        \"query\": [\n"
                + "          {\n"
                + "            \"table_name\": \"student\",\n"
                + "            \"table_alise\": \"stu\",\n"
                + "            \"feilds\": [\n"
                + "              {\n"
                + "                \"feild_name\": \"stuid\",\n"
                + "                \"output_name\": \"student_id\"\n"
                + "              },\n"
                + "              {\n"
                + "                \"feild_name\": \"stuname\",\n"
                + "                \"output_name\": \"student_name\"\n"
                + "              }\n"
                + "            ]\n"
                + "          },\n"
                + "          {\n"
                + "            \"table_name\": \"schools\",\n"
                + "            \"table_alise\": \"sch\",\n"
                + "            \"feilds\": [\n"
                + "              {\n"
                + "                \"feild_name\": \"schid\",\n"
                + "                \"output_name\": \"school_id\"\n"
                + "              },\n"
                + "              {\n"
                + "                \"feild_name\": \"schname\",\n"
                + "                \"output_name\": \"school_name\"\n"
                + "              }\n"
                + "            ]\n"
                + "          }\n"
                + "        ],\n"
                + "        \"where_clause\": [\n"
                + "          {}\n"
                + "        ]\n"
                + "      }\n"
                + "    ],\n"
                + "    \"insert_queries\": [\n"
                + "      {\n"
                + "        \"queary_id\": \"i1\",\n"
                + "        \"query\": \"insert into student values('a','a','a');\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"i2\",\n"
                + "        \"query\": \"insert into student values('a','a','a');\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"i3\",\n"
                + "        \"query\": \"insert into student values('a','a','a');\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"i4\",\n"
                + "        \"query\": \"insert into student values('a','a','a');\"\n"
                + "      }\n"
                + "    ],\n"
                + "    \"update_queries\": [\n"
                + "      {\n"
                + "        \"queary_id\": \"u1\",\n"
                + "        \"query\": \"update student set age = 25 where stuid = 1;\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"u2\",\n"
                + "        \"query\": \"update student set age = 25 where stuid = 1;\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"queary_id\": \"u3\",\n"
                + "        \"query\": \"update student set age = 25 where stuid = 1;\"\n"
                + "      }\n"
                + "    ],\n"
                + "    \"delete_queries\": [\n"
                + "      {\n"
                + "        \"queary_id\": \"d1\",\n"
                + "        \"query\": \"delete from student where stuid = 5;\"\n"
                + "      }\n"
                + "    ]\n"
                + "  },\n"
                + "  \"RestAPI_Design\": {\n"
                + "    \"rootURI\": \"{app_name}/myapi\"\n"
                + "  },\n"
                + "  \"UI_Design\": {},\n"
                + "  \"Reload_details\": {}\n"
                + "}";

        try {
            URL url = new URL("http://localhost:8084/RIP_Framework/rest-in-peace");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = json;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ Output from RIP_SQL_GEN ~~~~  >>>>>>>>>>>>>>>>>>>>>>>\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ~~~~ END RIP_SQL_GEN Output  ~~~~ >>>>>>>>>>>>>>>>>>>>>>>\n");
            conn.disconnect();

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        projectHandler.writeToProject();
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

}
