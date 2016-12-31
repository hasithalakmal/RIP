package com.rip.tester.common;

import com.rip.tester.dto.ConfigDetails;
import org.json.JSONObject;

/**
 * Created by Kasun Eranga on 10/15/2016.
 */
public class ConfigDetailExtractor {

    public static ConfigDetails getConfigDetails(String ripJsonString) {
        JSONObject ripJson = new JSONObject(ripJsonString);
        ConfigDetails configDetails = new ConfigDetails();
        configDetails.setAppDetails(ripJson.getJSONObject("Request_details"));
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
        configDetails.setOutputPath(Constants.PROJECT_OUTPUT_PATH_PREFIX + configDetails.getAppId() + "\\" + configDetails.getVersion() + "\\" + configDetails.getProjectName()+ "\\" + configDetails.getProjectName());
        configDetails.setOutputPathDocs(Constants.PROJECT_OUTPUT_PATH_PREFIX + configDetails.getAppId() + "\\" + configDetails.getVersion() + "\\" + configDetails.getProjectName()+ "\\Docs");
        configDetails.setPostmanCollectionPath(configDetails.getOutputPathDocs() + "\\Postman\\");
        configDetails.setResourcesPath(configDetails.getOutputPath() + "\\" + "resources");
        configDetails.setInputJsonPath(configDetails.getOutputPathDocs() + "\\" + configDetails.getProjectName() + ".json");
        configDetails.setConfigJsonPath(configDetails.getOutputPathDocs() + "\\config.json");
        configDetails.setGeneratorPath(Constants.REST_API_GENERATOR_PATH);
        configDetails.setPostmanGeneratorPath(Constants.POSTMAN_GENERATOR_PATH);
        configDetails.setProjectUtilPath(configDetails.getOutputPath() + "\\src\\main\\java\\" + configDetails.getPackageName().substring(0, configDetails.getPackageName().lastIndexOf('.') + 1).replace('.', '\\') + "util\\");
        configDetails.setDbName(ripJson.getJSONObject("Database_Design").getString("db_name"));
        configDetails.setDbSQLPath(configDetails.getOutputPathDocs() + "\\SQL\\");
        return configDetails;
    }
}
