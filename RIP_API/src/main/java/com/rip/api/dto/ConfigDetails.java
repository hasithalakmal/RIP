package com.rip.api.dto;

import org.json.JSONObject;

/**
 * Created by Kasun Eranga on 10/15/2016.
 */
public class ConfigDetails {

    private JSONObject appDetails;
    private JSONObject technicalSpecification;
    private JSONObject restAPISpecification;
    private JSONObject databaseDesign;

    private Integer appId;
    private String packageName;
    private String modelPackageName;
    private String projectName;
    private String version;
    private String programmingLanguage;
    private String restFramework;
    private String outputPath;
    private String postmanCollectionPath;
    private String resourcesPath;
    private String inputJsonPath;
    private String configJsonPath;
    private String generatorPath;
    private String postmanGeneratorPath;
    private String projectUtilPath;
    private String outputPathDocs;

    public ConfigDetails() {
    }

    public JSONObject getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(JSONObject appDetails) {
        this.appDetails = appDetails;
    }

    public JSONObject getTechnicalSpecification() {
        return technicalSpecification;
    }

    public void setTechnicalSpecification(JSONObject technicalSpecification) {
        this.technicalSpecification = technicalSpecification;
    }

    public JSONObject getRestAPISpecification() {
        return restAPISpecification;
    }

    public void setRestAPISpecification(JSONObject restAPISpecification) {
        this.restAPISpecification = restAPISpecification;
    }

    public JSONObject getDatabaseDesign() {
        return databaseDesign;
    }

    public void setDatabaseDesign(JSONObject databaseDesign) {
        this.databaseDesign = databaseDesign;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getRestFramework() {
        return restFramework;
    }

    public void setRestFramework(String restFramework) {
        this.restFramework = restFramework;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getPostmanCollectionPath() {
        return postmanCollectionPath;
    }

    public void setPostmanCollectionPath(String postmanCollectionPath) {
        this.postmanCollectionPath = postmanCollectionPath;
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    public String getInputJsonPath() {
        return inputJsonPath;
    }

    public void setInputJsonPath(String inputJsonPath) {
        this.inputJsonPath = inputJsonPath;
    }

    public String getConfigJsonPath() {
        return configJsonPath;
    }

    public void setConfigJsonPath(String configJsonPath) {
        this.configJsonPath = configJsonPath;
    }

    public String getGeneratorPath() {
        return generatorPath;
    }

    public void setGeneratorPath(String generatorPath) {
        this.generatorPath = generatorPath;
    }

    public String getPostmanGeneratorPath() {
        return postmanGeneratorPath;
    }

    public void setPostmanGeneratorPath(String postmanGeneratorPath) {
        this.postmanGeneratorPath = postmanGeneratorPath;
    }

    public String getProjectUtilPath() {
        return projectUtilPath;
    }

    public void setProjectUtilPath(String projectUtilPath) {
        this.projectUtilPath = projectUtilPath;
    }

    public String getOutputPathDocs() {
        return outputPathDocs;
    }

    public void setOutputPathDocs(String outputPathDocs) {
        this.outputPathDocs = outputPathDocs;
    }
}
