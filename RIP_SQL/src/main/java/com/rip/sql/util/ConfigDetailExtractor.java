/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import org.json.JSONObject;

/**
 *
 * @author Hasitha Lakmal
 */
public class ConfigDetailExtractor {

    public static ConfigDetails getConfigDetails(String ripJsonString) {
        JSONObject ripJson = new JSONObject(ripJsonString);
        ConfigDetails configDetails = new ConfigDetails();
        configDetails.setAppDetails(ripJson.getJSONObject("Request_Details"));
        configDetails.setTechnicalSpecification(ripJson.getJSONObject("Technical_Spec"));
        configDetails.setRestAPISpecification(ripJson.getJSONObject("RestAPI_Design"));
        configDetails.setDatabaseDesign(ripJson.getJSONObject("Database_Design"));
        configDetails.setAppId(configDetails.getTechnicalSpecification().getInt("app_id"));
        configDetails.setPackageName(configDetails.getAppDetails().getString("package_name"));
        configDetails.setModelPackageName(configDetails.getPackageName().substring(0, configDetails.getPackageName().lastIndexOf('.') + 1) + "model");
        configDetails.setProjectName(configDetails.getAppDetails().getString("project_name"));
        configDetails.setVersion(configDetails.getAppDetails().getString("version"));
        configDetails.setProgrammingLanguage(configDetails.getTechnicalSpecification().getString("language"));
        configDetails.setRestFramework(configDetails.getTechnicalSpecification().getString("rest_framework"));
        configDetails.setOutputPath(Constants.PROJECT_OUTPUT_PATH_PREFIX + configDetails.getAppId() + "\\" + configDetails.getVersion() + "\\" + configDetails.getProjectName());
        configDetails.setOutputPathDocs(Constants.PROJECT_OUTPUT_PATH_PREFIX + configDetails.getAppId() + "\\" + configDetails.getVersion() + "\\" + configDetails.getProjectName()+ "\\Docs");
        configDetails.setPostmanCollectionPath(configDetails.getOutputPathDocs() + "\\Postman\\");
        configDetails.setResourcesPath(configDetails.getOutputPath() + "\\" + "resources");
        configDetails.setInputJsonPath(configDetails.getOutputPathDocs() + "\\" + configDetails.getProjectName() + ".json");
        configDetails.setConfigJsonPath(configDetails.getOutputPathDocs() + "\\config.json");
        configDetails.setProjectUtilPath(configDetails.getOutputPath() + "\\src\\main\\java\\" + configDetails.getPackageName().substring(0, configDetails.getPackageName().lastIndexOf('.') + 1).replace('.', '\\') + "util\\");
        configDetails.setDbName(ripJson.getJSONObject("Database_Design").getString("database_name"));
        configDetails.setDbSQLPath(configDetails.getOutputPath() + "\\DatabaseScript-and-Documentation\\");
        return configDetails;
    }
}
