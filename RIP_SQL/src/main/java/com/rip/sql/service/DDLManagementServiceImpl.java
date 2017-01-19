package com.rip.sql.service;

import com.rip.sql.ruleengine.PIMValidator;
import com.rip.sql.scriptgen.DDL_Genarator;
import com.rip.sql.util.MavenProjectRunner;
import com.rip.sql.util.WordFileGenarator;
import com.rip.sql.util.WriteToJSONFile;
import com.rip.sql.util.WriteToSQLFile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DDLManagementService")
public class DDLManagementServiceImpl implements DDLManagementService {

    @Autowired
    DDL_Genarator dDL_Genarator;

    @Autowired
    WriteToJSONFile writeToJSONFile;

    @Autowired
    MavenProjectRunner MavenProjectRunner;

    @Autowired
    PIMValidator PIMValidator;

    @Autowired
    WriteToSQLFile writeToSQLFile;

    @Autowired
    WordFileGenarator wordFileGenarator;

    @Autowired
    DDLManagementServiceImpl(DDL_Genarator dDL_Genarator, WriteToJSONFile writeToJSONFile, MavenProjectRunner MavenProjectRunner) {
        this.dDL_Genarator = dDL_Genarator;
        this.writeToJSONFile = writeToJSONFile;
        this.MavenProjectRunner = MavenProjectRunner;
    }

    @Override
    public String genarate(String ddlJSON) {
        JSONObject platformIndependentModel = new JSONObject(ddlJSON);
        JSONObject Technical_Spec = platformIndependentModel.getJSONObject("Technical_Spec");
        String dbms = Technical_Spec.getString("dbms");
        String executionClass = dbms + "ScriptGen";
        JSONObject databaseDesign = platformIndependentModel.getJSONObject("Database_Design");
        System.out.println(databaseDesign.toString());
        String msg = PIMValidator.validatJSON(databaseDesign.toString());
        if (!msg.equals("success")) {
            return msg;
        } else {
            writeToJSONFile.createJsonFile("pim", platformIndependentModel.toString());
            String script = MavenProjectRunner.runMavenProject(executionClass);
            System.out.println(script);
            String startString = "RIP_SQL_GEN_DDL_BEGIN";
            int start = script.indexOf(startString);
            int start1 = startString.length();
            String endString = "RIP_SQL_GEN_DDL_END";
            int end = script.indexOf(endString);
            CharSequence sub = script.subSequence((start + start1), (end));
            String ddl_script = sub.toString();
            writeToSQLFile.createSQLFile("DDL_Script", ddl_script,ddlJSON);
            startString = "RIP_SQL_GEN_DML_BEGIN";
            start = script.indexOf(startString);
            start1 = startString.length();
            endString = "RIP_SQL_GEN_DML_END";
            end = script.indexOf(endString);
            sub = script.subSequence((start + start1), (end));
            String dml_script = sub.toString();
            writeToSQLFile.createSQLFile("DML_Script", dml_script,ddlJSON);
            startString = "RIP_SQL_GEN_DQL_BEGIN";
            start = script.indexOf(startString);
            start1 = startString.length();
            endString = "RIP_SQL_GEN_DQL_END";
            end = script.indexOf(endString);
            sub = script.subSequence((start + start1), (end));
            String dql_script = sub.toString();
            writeToSQLFile.createSQLFile("DQL_Script", dql_script,ddlJSON);
            wordFileGenarator.createWordFile(ddlJSON.toString());
        }
        return msg;
    }

}
