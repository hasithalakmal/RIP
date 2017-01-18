package com.rip.sql.service;

import com.rip.sql.ruleengine.PIMValidator;
import com.rip.sql.scriptgen.DDL_Genarator;
import com.rip.sql.util.MavenProjectRunner;
import com.rip.sql.util.WriteToJSONFile;
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
    DDLManagementServiceImpl(DDL_Genarator dDL_Genarator, WriteToJSONFile writeToJSONFile, MavenProjectRunner MavenProjectRunner) {
        this.dDL_Genarator = dDL_Genarator;
        this.writeToJSONFile = writeToJSONFile;
        this.MavenProjectRunner = MavenProjectRunner;
    }

    @Override
    public String genarate(String ddlJSON) {
        JSONObject platformIndependentModel = new JSONObject(ddlJSON);
        JSONObject databaseDesign = platformIndependentModel.getJSONObject("Database_Design");
        System.out.println(databaseDesign.toString());
        String msg = PIMValidator.validatJSON(databaseDesign.toString());
        if (!msg.equals("success")) {
            System.out.println(msg);
            return msg;
        } else {
            writeToJSONFile.createJsonFile("pim", platformIndependentModel.toString());
            String script = MavenProjectRunner.runMavenProject("MySQLScriptGen");
            System.out.println("##################################################################");
            System.out.println(script);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            String startString = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_BEGIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
            int start = script.indexOf(startString);
            int start1 = startString.length();
            System.out.println("start = " + start + " start1 = " + start1);

            String endString = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RIP_SQL_GEN_END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
            int end = script.indexOf(endString);
            int end1 = endString.length();
            System.out.println();
            System.out.println("end = " + end + " end1 = " + end1);
            CharSequence sub = script.subSequence((start + start1), (end));
//        String script = javaMainFileRunner.runClass("MySQLScriptGen");
//        System.out.println(script);
            return sub.toString();
        }

    }

}
