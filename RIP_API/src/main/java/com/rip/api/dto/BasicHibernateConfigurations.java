package com.rip.api.dto;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Kasun Eranga on 10/3/2016.
 */
public class BasicHibernateConfigurations {

    private String database;
    private String databaseName;
    private String resourcesPath;
    private String projectUtilPath;
    private String packageName;
    private Map<String, Object> hibernateConfigData;
    private JSONObject tables;

    public BasicHibernateConfigurations() {
    }

    public BasicHibernateConfigurations(String database, String databaseName, String resourcesPath, String projectUtilPath, String packageName, Map<String, Object> hibernateConfigData, JSONObject tables) {
        this.database = database;
        this.databaseName = databaseName;
        this.resourcesPath = resourcesPath;
        this.projectUtilPath = projectUtilPath;
        this.packageName = packageName;
        this.hibernateConfigData = hibernateConfigData;
        this.tables = tables;
    }

    public JSONObject getTables() {
        return tables;
    }

    public void setTables(JSONObject tables) {
        this.tables = tables;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    public String getProjectUtilPath() {
        return projectUtilPath;
    }

    public void setProjectUtilPath(String projectUtilPath) {
        this.projectUtilPath = projectUtilPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Map<String, Object> getHibernateConfigData() {
        return hibernateConfigData;
    }

    public void setHibernateConfigData(Map<String, Object> hibernateConfigData) {
        this.hibernateConfigData = hibernateConfigData;
    }
}
