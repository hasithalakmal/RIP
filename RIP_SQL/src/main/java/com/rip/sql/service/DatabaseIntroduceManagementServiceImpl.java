package com.rip.sql.service;

import com.rip.sql.scriptgen.DDL_Genarator;
import com.rip.sql.util.WriteToJavaFile;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DatabaseIntroduceManagementService")
public class DatabaseIntroduceManagementServiceImpl implements DatabaseIntroduceManagementService {

    @Autowired
    WriteToJavaFile writeToJavaFile;
    
    @Override
    public String introduceDatabse(String databseModel) {
        JSONObject databaseTechonolgy = new JSONObject(databseModel);
        String databse_name = databaseTechonolgy.getString("databse_name");
        String java_code = databaseTechonolgy.getString("java_code");
        int state = writeToJavaFile.createJavaFile(databse_name, java_code);
        return "success";
    }

    

}
