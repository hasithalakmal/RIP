package com.rip.api.common;

import com.rip.api.dto.BasicHibernateConfigurations;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.jadira.usertype.spi.reflectionutils.ClassLoaderUtils.getClassLoader;

/**
 * Created by Kasun Eranga on 10/3/2016.
 */
public class HibernateGenerator {
    static ClassLoader classLoader = getClassLoader();

    public static void generateBasicHibernateConfigurations(BasicHibernateConfigurations configData) throws IOException {
        Map<String, Object> templateData = getHibernatePropertyMap(configData.getDatabase(), configData.getDatabaseName());
        Common.generateFileFromTemplate(Constants.HIBERNATE_PROPERTIES_MUSTACHE_FILE_PATH, configData.getResourcesPath() + "\\" + "hibernate.properties", templateData);
        Common.generateFileFromTemplate(Constants.CONSTANTS_MUSTACHE_FILE_PATH, configData.getProjectUtilPath() + "Constants.java", configData.getHibernateConfigData());
        Common.generateFileFromTemplate(Constants.HIBERNATE_CONNECTION_UTILITY_MUSTACHE_FILE_PATH, configData.getProjectUtilPath() + "HibernateConnectionUtility.java", configData.getHibernateConfigData());
        Common.generateFileFromTemplate(Constants.HIBERNATE_SESSION_MANAGER_MUSTACHE_FILE_PATH, configData.getProjectUtilPath() + "HibernateSessionManager.java", configData.getHibernateConfigData());
        Common.generateFileFromTemplate(Constants.HIBERNATE_SESSION_MANAGER_IMPL_MUSTACHE_FILE_PATH, configData.getProjectUtilPath() + "HibernateSessionManagerImpl.java", configData.getHibernateConfigData());
        Common.generateFileFromTemplate(Constants.HIBERNATE_CFG_XML_MUSTACHE_FILE_PATH, configData.getResourcesPath() + "\\" + "hibernate.cfg.xml", generateListOfEntities(configData.getPackageName().substring(0, configData.getPackageName().lastIndexOf('.') + 1) + "persistence.Entity", configData.getTables()));
        Common.generateFileFromTemplate(Constants.JSON_UTIL_FILE_PATH, configData.getProjectUtilPath() + "JsonUtility.java", configData.getHibernateConfigData());
    }

    public static void generateHibernateEntityClasses(String packageName, String outputPath, JSONObject databaseDesign, JSONObject paths) throws IOException {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("hibernateToolsPath", Constants.HIBERNATE_TOOLS_PATH);
        Common.generateFileFromTemplate(Constants.HIBERNATE_PROPERTIES_FOR_ENTITY_GENERATION_MUSTACHE_FILE_PATH, outputPath + "\\hbm\\" + "hibernate.properties", null);
        generateHibernateMappings(packageName, outputPath, databaseDesign, paths);
        Common.generateFileFromTemplate(Constants.ANT_BUID_FOR_HIBERNATE_MUSTACHE_FILE_PATH, outputPath + "\\" + "build.xml", templateData);
        AbstractProcessBuilder.buildProcess(new File(outputPath), "ant"); // execute ant task of creating Entity classes
    }

    public static void generateHibernateMappings(String packageName, String outputPath, JSONObject databaseDesign, JSONObject paths) throws IOException {
        Map<String, Object> templateData;
        Map<String, Object> daoTemplateData;
        List<Object> id;
        JSONArray tableList = databaseDesign.getJSONArray("tables");
        for (int i = 0; i < tableList.length(); i++) {
            templateData = new HashMap<>();
            daoTemplateData = new HashMap<>();
            JSONObject table = tableList.getJSONObject(i);
            String tableName = table.getString("table_name");
            templateData.put("defaultPackage", packageName.substring(0, packageName.lastIndexOf('.') + 1) + "persistence.entity");
            templateData.put("className", getCompliantEntityName(tableName));
            templateData.put("tableName", tableName);
            templateData.putAll(generateMappingDataForDBTable(table.getJSONArray("fileds"), getForeignKeysForADBTable(databaseDesign.getJSONArray("forign_keys"), tableName, "base_table"), getCompliantEntityName(tableName) + "Entity"));
            Common.generateFileFromTemplateWithSubTemplate(Constants.HIBERNATE_HBM_XML_MUSTACHE_FILE_PATH, outputPath + "\\hbm\\" + tableName + ".hbm.xml", templateData, Constants.HBM_TEMPLATE_DIRECTORY_PATH);

            daoTemplateData.put("package", packageName.substring(0, packageName.lastIndexOf('.') + 1));
            daoTemplateData.put("className", getCompliantEntityName(tableName));
            daoTemplateData.putAll(getPathsForADBTable(getCompliantEntityName(tableName).toLowerCase(), paths, packageName, templateData, tableList));
            Common.generateFileFromTemplate(Constants.HIBERNATE_DAO_MUSTACHE_FILE_PATH, outputPath + "\\src\\main\\java\\" + packageName.substring(0, packageName.lastIndexOf('.') + 1).replace(".", "\\") + "persistence\\dao\\" + getCompliantEntityName(tableName) + "ApiDAO.java", daoTemplateData);
            Common.generateFileFromTemplateWithSubTemplate(Constants.HIBERNATE_DAOImpl_MUSTACHE_FILE_PATH, outputPath + "\\src\\main\\java\\" + packageName.substring(0, packageName.lastIndexOf('.') + 1).replace(".", "\\") + "persistence\\daoimpl\\" + getCompliantEntityName(tableName) + "ApiDAOImpl.java", daoTemplateData, Constants.HIBERNATE_DAO_TEMPLATE_DIRECTORY_PATH);
        }
    }

    public static Map<String, Object> getHibernatePropertyMap(String dbms, String dbName) throws IOException {
        File file = new File(classLoader.getResource(Constants.HIBERNATE_JSON_FILE_PATH).getFile());
        JSONObject hibernateJson = new JSONObject(FileUtils.readFileToString(file, "UTF-8"));
        JSONObject databaseJson = hibernateJson.getJSONObject(dbms);
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("database", dbms);
        templateData.put("dialect", databaseJson.getString("dialect"));
        templateData.put("driverClass", databaseJson.getString("driverClass"));
        templateData.put("url", databaseJson.getString("defaultUrl") + dbName);
        templateData.put("username", "root");
        //templateData.put("password", "");

        return templateData;
    }

    public static Object generateListOfEntities(String packageName, JSONObject tables) {
        final List<Object> allEntities = new ArrayList<>();
        JSONArray tableList = tables.getJSONArray("tables");
        for (int i = 0; i < tableList.length(); i++) {
            JSONObject table = tableList.getJSONObject(i);
            String tableName = table.getString("table_name");
            String entityName = packageName + "." + getCompliantEntityName(tableName) + "Entity";
            allEntities.add(new Entity(entityName));
        }
        return new Object() {
            Object entities = allEntities;
        };
    }

    public static String getCompliantName(String string) {
        String compliantString = string;

        if (compliantString.endsWith("_") || compliantString.endsWith("-")) {
            compliantString = compliantString.substring(0, compliantString.length() - 1);
        }

        for (int index = compliantString.indexOf("_"); index >= 0; index = compliantString.indexOf("_", index + 1)) {
            compliantString = compliantString.substring(0, index) + compliantString.substring(index + 1, index + 2).toUpperCase().concat(compliantString.substring(index + 2));
        }

        for (int index = compliantString.indexOf("-"); index >= 0; index = compliantString.indexOf("-", index + 1)) {
            compliantString = compliantString.substring(0, index) + compliantString.substring(index + 1, index + 2).toUpperCase().concat(compliantString.substring(index + 2));
        }

        return compliantString;
    }


    public static String getCompliantEntityName(String dbString) {
        String entityString = Common.capitalizeFirstLetter(dbString);
        entityString = getCompliantName(entityString);
        return entityString;
    }


    public static String getCompliantEntityFieldName(String dbField) {
        String fieldString = getCompliantName(dbField);
        if (ReservedWords.getVariableNames().contains(fieldString.toLowerCase())) {
            fieldString += "Field";
        }
        return fieldString;
    }

    public static String getCompliantModelName(String modelName) {
        String string = getCompliantEntityName(modelName);
        if (ReservedWords.getVariableNames().contains(string.toLowerCase())) {
            string = "Model" + string;
        }
        return string;
    }

    public static String getCompliantPropertyName(String propertyName) {
        String string = getCompliantName(propertyName);
        if (ReservedWords.getVariableNames().contains(string.toLowerCase())) {
            string = "property" + string;
        }
        return string;
    }

    public static Map<String, Object> generateMappingDataForDBTable(JSONArray fieldList, JSONArray foreignKeys, final String entityName) {
        Map<String, Object> templateData = new HashMap<>();
        final List<Id> ids = new ArrayList<>();
        final List<Property> properties = new ArrayList<>();
        final List<ForeignKey> OneToOneforeignKeys = new ArrayList<>();
        final List<ForeignKey> ManyToOneforeignKeys = new ArrayList<>();

        String generator = "";
        String other;
        for (int i = 0; i < fieldList.length(); i++) {
            other = "";
            JSONObject field = fieldList.getJSONObject(i);
            JSONObject foreignKey = getForeignKeyForADBField(foreignKeys, field.getString("feild_name"));

            if (field.getBoolean("primary_key")) {
                if (field.getBoolean("auto_incriment")) {
                    generator = "<generator class=\"identity\" />";
                }
                ids.add(new Id(getCompliantEntityFieldName(field.getString("feild_name")), fromDBDataTypeToEntityDataType(field.getString("data_type")), generator, field.getString("feild_name")));
            } else if (foreignKey != null) {
                String cascade = "";
                if (foreignKey.getString("on_update").equals("CASCADE")) {
                    cascade = "save-update";
                }
                if (foreignKey.getString("on_delete").equals("CASCADE")) {
                    if (cascade.equals("")) {
                        cascade = "delete";
                    } else {
                        cascade = "save-update, delete";
                    }
                }
                if (foreignKey.getString("relation").equals("OneToOne")) {
                    OneToOneforeignKeys.add(new ForeignKey(getCompliantEntityFieldName(field.getString("feild_name")), getCompliantEntityName(foreignKey.getString("reference_table")) + "Entity", cascade, getCompliantEntityFieldName(foreignKey.getString("rt_feild_name")), null));
                } else {// ManyToMany
                    ManyToOneforeignKeys.add(new ForeignKey(getCompliantEntityFieldName(field.getString("feild_name")), getCompliantEntityName(foreignKey.getString("reference_table")) + "Entity", cascade, getCompliantEntityFieldName(foreignKey.getString("rt_feild_name")), foreignKey.getString("bt_feild_name")));
                }
            } else {
                if (field.getBoolean("not_null")) {
                    other += "not-null=\"true\" ";
                }
                if (field.getBoolean("unique")) {
                    other += "unique=\"true\" ";
                }
                properties.add(new Property(getCompliantEntityFieldName(field.getString("feild_name")), field.getString("feild_name"), fromDBDataTypeToEntityDataType(field.getString("data_type")), other));
            }
        }
        if (ids.size() > 1) {
            templateData.put("compositeId", new CompositeIdHBM("compositeId", entityName + "Id", ids));
        } else {
            templateData.put("id", ids);
        }
        templateData.put("properties", properties);
        templateData.put("OneToOnes", OneToOneforeignKeys);
        templateData.put("ManyToOnes", ManyToOneforeignKeys);

        return templateData;
    }

    public static List<Id> generatePKDataForDBTable(JSONArray fieldList) {
        final List<Id> ids = new ArrayList<>();
        String generator = "";
        for (int i = 0; i < fieldList.length(); i++) {
            JSONObject field = fieldList.getJSONObject(i);

            if (field.getBoolean("primary_key")) {
                if (field.getBoolean("auto_incriment")) {
                    generator = "<generator class=\"identity\" />";
                }
                ids.add(new Id(getCompliantEntityFieldName(field.getString("feild_name")), fromDBDataTypeToEntityDataType(field.getString("data_type")), generator, field.getString("feild_name")));
            }
        }

        return ids;
    }

    public static JSONArray getForeignKeysForADBTable(JSONArray allForeignKeys, String table, String tableType) { // tableType can be "base_table" or "reference_table"
        if (allForeignKeys == null) {
            return null;
        }
        final JSONArray foreignKeys = new JSONArray();
        for (int i = 0; i < allForeignKeys.length(); i++) {
            JSONObject foreignKey = allForeignKeys.getJSONObject(i);
            if (foreignKey.getString(tableType).equals(table)) {
                foreignKeys.put(foreignKey);
            }
        }

        return foreignKeys;
    }

    public static JSONObject getForeignKeyForADBField(JSONArray foreignKeys, String field) {
        if (foreignKeys == null) {
            return null;
        }
        JSONObject foreignKey = null;
        for (int i = 0; i < foreignKeys.length(); i++) {
            if (foreignKeys.getJSONObject(i).getString("bt_feild_name").equals(field)) {
                foreignKey = foreignKeys.getJSONObject(i);
                break;
            }
        }
        return foreignKey;
    }

    public static String fromDBDataTypeToEntityDataType(String dbDataType) {
        String entityDataType = dbDataType;
        if (dbDataType.toLowerCase().contains("int")) {
            entityDataType = "integer";
        } else if (dbDataType.toLowerCase().contains("varchar")) {
            entityDataType = "string";
        }

        return entityDataType;
    }

    public static Map<String, Object> getPathsForADBTable(String table, JSONObject paths, String packageName, final Map<String, Object> entityData, JSONArray tableList) {
        Map<String, Object> templateData = new HashMap<>();
        Set<String> imports = new HashSet<>();
        Set<String> importsForDAOImpl = new HashSet<>();
        List<Object> importsList = new ArrayList<>();
        List<Object> importsListForDAOImpl = new ArrayList<>();
        List<Object> operations = new ArrayList<>();

        List<ForeignKey> foreignKeys = new ArrayList<>();
        foreignKeys.addAll((List<ForeignKey>) entityData.get("OneToOnes"));
        foreignKeys.addAll(((List<ForeignKey>) entityData.get("ManyToOnes")));

        Iterator pathKeys = paths.keys();
        while (pathKeys.hasNext()) {
            String pathName = (String) pathKeys.next();
            if (pathName.substring(1).startsWith(table)) {
                JSONObject path = paths.getJSONObject(pathName);
                Iterator crudKeys = path.keys();
                while (crudKeys.hasNext()) {
                    String crudName = (String) crudKeys.next();
                    JSONObject crudOperation = path.getJSONObject(crudName);
                    String operationName = crudOperation.getString("operationId");
                    JSONArray parameters = crudOperation.getJSONArray("parameters");
                    String parameterType;
                    String parameterImportType;
                    String returnType;
                    String returnImportType;
                    String returnTypeForSelect = null;
                    List<Parameter> parametersForAMethod = new ArrayList<>();
                    String delimiter = ",";
                    List<Insert> insert = new ArrayList<>();
                    List<Update> update = new ArrayList<>();
                    List<Select> select = new ArrayList<>();
                    List<Select> selectUnique = new ArrayList<>();
                    List<Delete> delete = new ArrayList<>();
                    // works on method parameters and imports to the class
                    for (int i = 0; i < parameters.length(); i++) {
                        if (parameters.getJSONObject(i).has("type")) {
                            Map<String, String> importMapping = getParameterTypeAndImportMapping(parameters.getJSONObject(i).getString("type"));
                            parameterType = importMapping.get("parameterType");
                            parameterImportType = importMapping.get("parameterImportType");
                        } else {
                            String complexParameterRef = parameters.getJSONObject(i).getJSONObject("schema").getString("$ref");
                            parameterType = getCompliantModelName(complexParameterRef.substring(complexParameterRef.lastIndexOf("/") + 1));
                            parameterImportType = packageName.substring(0, packageName.lastIndexOf('.') + 1) + "model." + parameterType;
                        }

                        if (parameterImportType != null) {
                            imports.add(parameterImportType);
                        }

                        if (i == parameters.length() - 1) {
                            delimiter = "";
                        }
                        parametersForAMethod.add(new Parameter(parameterType, getCompliantPropertyName(parameters.getJSONObject(i).getString("name")), delimiter, parameters.getJSONObject(i).getBoolean("required")));
                    }
                    //works on return type of the method
                    if (crudName.equals("delete")) {
                        returnType = "String";
                    } else {
                        JSONObject responseDetails = crudOperation.getJSONObject("responses").getJSONObject("200").getJSONObject("schema");
                        String returnParameterRef;
                        if (responseDetails.has("$ref")) {
                            returnParameterRef = responseDetails.getString("$ref");
                            returnType = getCompliantModelName(returnParameterRef.substring(returnParameterRef.lastIndexOf("/") + 1));
                            returnTypeForSelect = returnType;
                            returnImportType = packageName.substring(0, packageName.lastIndexOf('.') + 1) + "model." + returnType;
                        } else {
                            String returnCollectionType = responseDetails.getString("type");
                            returnCollectionType = ImportMapping.getTypeMapping(returnCollectionType);
                            imports.add(ImportMapping.getImportMapping(returnCollectionType));
                            returnParameterRef = responseDetails.getJSONObject("items").getString("$ref");
                            returnType = returnCollectionType + "<" + getCompliantModelName(returnParameterRef.substring(returnParameterRef.lastIndexOf("/") + 1)) + ">";
                            returnTypeForSelect = getCompliantModelName(returnParameterRef.substring(returnParameterRef.lastIndexOf("/") + 1));
                            returnImportType = packageName.substring(0, packageName.lastIndexOf('.') + 1) + "model." + getCompliantModelName(returnParameterRef.substring(returnParameterRef.lastIndexOf("/") + 1));
                        }
                        imports.add(returnImportType);
                    }

                    switch (crudName) {
                        case "post":
                            insert = getInsertOperationBody(entityData, tableList, parametersForAMethod);
                            break;
                        case "put":
                            update = getUpdateOperationBody(entityData, tableList, parametersForAMethod);
                            break;
                        case "get":
                            if (returnType.equals(returnTypeForSelect)) {
                                selectUnique = getSelectOperationBody(entityData, tableList, parametersForAMethod, returnType, returnTypeForSelect);
                            } else {
                                select = getSelectOperationBody(entityData, tableList, parametersForAMethod, returnType, returnTypeForSelect);
                            }
                            break;
                        case "delete":
                            delete = getDeleteOperationBody(entityData, parametersForAMethod);
                            break;
                    }

                    operations.add(new Operation(returnType, operationName, parametersForAMethod, insert, update, select, delete, selectUnique));

                }
            }
        }
        for (String importType : imports) {
            importsList.add(new Import(importType));
        }
        for (ForeignKey fk : foreignKeys) {
            importsForDAOImpl.add(fk.getClassName());
        }
        for (String importType : importsForDAOImpl) {
            importsListForDAOImpl.add(new Import(packageName.substring(0, packageName.lastIndexOf('.') + 1) + "persistence.entity." + importType));
        }

        templateData.put("ImportsDAOImpl", importsListForDAOImpl);
        templateData.put("Imports", importsList);
        templateData.put("Operations", operations);
        return templateData;
    }

    public static Map<String, String> getParameterTypeAndImportMapping(String type) {
        Map<String, String> parameterMap = new HashMap<>();
        String parameterType = ImportMapping.getTypeMapping(type);
        String parameterImportType = ImportMapping.getImportMapping(parameterType);
        parameterMap.put("parameterType", parameterType);
        parameterMap.put("parameterImportType", parameterImportType);
        return parameterMap;
    }

    public static List<Insert> getInsertOperationBody(Map<String, Object> entityData, JSONArray tableList, List<Parameter> parametersForAMethod) {
        List<Insert> list = new ArrayList<>();
        String entity = (String) entityData.get("className") + "Entity";
        List<ForeignKeyEntity> ForeignKeyFields = new ArrayList<>();
        List<EntityField> EntityFields = new ArrayList<>();
        List<CompositeId> CompositeId = new ArrayList<>();
        List<Parameter> parametersForCompositeId = new ArrayList<>();
        String returnType = parametersForAMethod.get(0).getName();

        List<Id> id = (List<Id>) entityData.get("id");
        CompositeIdHBM compositeId = (CompositeIdHBM) entityData.get("compositeId");
        List<Property> properties = (List<Property>) entityData.get("properties");
        List<ForeignKey> foreignKeys = new ArrayList<>();
        foreignKeys.addAll((List<ForeignKey>) entityData.get("OneToOnes"));
        foreignKeys.addAll(((List<ForeignKey>) entityData.get("ManyToOnes")));

        for (ForeignKey fk : foreignKeys) {
            List<Id> foreignKeyPKData = generatePKDataForDBTable(getFieldsForAnEntity(tableList, fk.getClassName()));
            List<ForeignKeyEntityId> fKCompositeId = new ArrayList<>();
            String fKId = "";
            String fKFieldSetter = "";
            if (foreignKeyPKData.size() > 1) {
                fKId = fk.getName() + "Id";
                fKFieldSetter = getGetterOrSetterForFieldWithBracket(fKId, "set");
                //fKCompositeId.add(new ForeignKeyEntityId(fk.getClassName(),fk.getName(),));
            } else {
                fKId = parametersForAMethod.get(0).getName() + "." + getGetterOrSetterForFieldWithBracket(fk.getName(), "get");
                fKFieldSetter = getGetterOrSetterForField(fk.getName(), "set");
            }
            ForeignKeyFields.add(new ForeignKeyEntity(fk.getClassName(), fk.getName(), fKCompositeId, fKId, fKFieldSetter));
        }
        if (compositeId != null) {
            List<Id> ids = compositeId.getColumns();
            parametersForCompositeId = getParameterListForCompositeId(ids);
            CompositeId.add(new CompositeId(entity + "Id", parametersForCompositeId, "setCompositeId"));
        }
        for (Property property : properties) {
            EntityFields.add(new EntityField(getGetterOrSetterForField(property.getName(), "set"), parametersForAMethod.get(0).getName(), getGetterOrSetterForField(property.getName(), "get")));
        }

        list.add(new Insert(entity, ForeignKeyFields, EntityFields, CompositeId, returnType));
        return list;
    }

    public static List<Update> getUpdateOperationBody(Map<String, Object> entityData, JSONArray tableList, List<Parameter> parametersForAMethod) {
        List<Update> list = new ArrayList<>();
        String id = "id";
        List<Insert> insert = getInsertOperationBody(entityData, tableList, parametersForAMethod);
        Insert insertData = insert.get(0);
        if (insertData.getCompositeId().size() == 0) {
            List<Id> pkData = generatePKDataForDBTable(getFieldsForAnEntity(tableList, insertData.getEntity()));
            id = parametersForAMethod.get(0).getName() + "." + getGetterOrSetterForFieldWithBracket(pkData.get(0).getName(), "get");
        }
        list.add(new Update(insertData.getEntity(), insertData.getForeignKeyFields(), insertData.getEntityFields(), insertData.getCompositeId(), insertData.getReturnType(), id));
        return list;
    }

    public static List<Select> getSelectOperationBody(Map<String, Object> entityData, JSONArray tableList, List<Parameter> parametersForAMethod, String returnType, String returnTypeForSelect) {
        List<Select> list = new ArrayList<>();
        String entity = (String) entityData.get("className") + "Entity";
        List<Limit> limit = new ArrayList<>();
        List<Restriction> restrictions = new ArrayList<>();
        List<Alias> aliases = new ArrayList<>();
        List<Projection> projections = new ArrayList<>();
        List<String> order = new ArrayList<>();
        List<String> paging = new ArrayList<>();

        List<Id> id = (List<Id>) entityData.get("id");
        CompositeIdHBM compositeId = (CompositeIdHBM) entityData.get("compositeId");
        List<Property> properties = (List<Property>) entityData.get("properties");
        List<ForeignKey> foreignKeys = new ArrayList<>();
        foreignKeys.addAll((List<ForeignKey>) entityData.get("OneToOnes"));
        foreignKeys.addAll(((List<ForeignKey>) entityData.get("ManyToOnes")));


        for (Parameter parameter : parametersForAMethod) {
            if (parameter.getType().equals("Integer") && parameter.getName().equals("limit")) {
                limit.add(new Limit("limit"));
                continue;
            } else if (parameter.getType().equals("String") && parameter.getName().equals("sortOrder") && hasParameter(parametersForAMethod, "sortColumn", "String")) {
                order.add("");
                continue;
            } else if (parameter.getType().equals("Integer") && parameter.getName().equals("currentPage") && hasParameter(parametersForAMethod, "numberOfRows", "Integer")) {
                paging.add("");
                continue;
            }
            for (Property property : properties) {
                if (parameter.getName().equals(property.getName())) {
                    restrictions.add(new Restriction(property.getName(), parameter.getName()));
                    break;
                }
            }
            for (ForeignKey foreignKey : foreignKeys) {
                if (parameter.getName().equals(foreignKey.getName())) {
                    List<Id> foreignKeyPKData = generatePKDataForDBTable(getFieldsForAnEntity(tableList, foreignKey.getClassName()));
                    restrictions.add(new Restriction(foreignKey.getName() + "." + foreignKeyPKData.get(0).getName(), parameter.getName()));
                    break;
                }
            }

            if (returnType.equals(returnTypeForSelect)) {
                if (compositeId != null) {
                    for (Id pK : compositeId.getColumns()) {
                        if (parameter.getName().equals(pK.getName())) {
                            restrictions.add(new Restriction("compositeId." + pK.getName(), parameter.getName()));
                        }
                    }
                } else {
                    restrictions.add(new Restriction(id.get(0).getName(), parameter.getName()));
                }
            }
        }

        //generating projections
        if (compositeId != null) {
            for (Id pK : compositeId.getColumns()) {
                projections.add(new Projection("compositeId." + pK.getName(), pK.getName()));
            }
        } else {
            projections.add(new Projection(id.get(0).getName(), id.get(0).getName()));
        }

        for (ForeignKey foreignKey : foreignKeys) {
            aliases.add(new Alias(foreignKey.getName()));
            List<Id> foreignKeyPKData = generatePKDataForDBTable(getFieldsForAnEntity(tableList, foreignKey.getClassName()));
            projections.add(new Projection(foreignKey.getName() + "." + foreignKeyPKData.get(0).getName(), foreignKeyPKData.get(0).getName()));
        }

        for (Property property : properties) {
            projections.add(new Projection(property.getName(), property.getName()));
        }

        list.add(new Select(entity, returnTypeForSelect, limit, restrictions, aliases, projections, order, paging));
        return list;
    }

    public static List<Delete> getDeleteOperationBody(Map<String, Object> entityData, List<Parameter> parametersForAMethod) {
        List<Delete> list = new ArrayList<>();
        String entity = (String) entityData.get("className") + "Entity";
        List<Id> id = (List<Id>) entityData.get("id");
        CompositeIdHBM compositeId = (CompositeIdHBM) entityData.get("compositeId");
        String idData = parametersForAMethod.get(0).getName();
        List<CompositeId> compositeIdData = new ArrayList<>();
        if (compositeId != null) {
            idData = "id";
            compositeIdData.add(new CompositeId(entity + "Id", parametersForAMethod, null));
        }

        list.add(new Delete(entity, idData, compositeIdData));
        return list;
    }

    public static JSONArray getFieldsForAnEntity(JSONArray tableList, String entityName) {
        JSONArray fieldList = new JSONArray();
        for (int i = 0; i < tableList.length(); i++) {
            JSONObject table = tableList.getJSONObject(i);
            String tableName = getCompliantEntityName(table.getString("table_name")) + "Entity";
            if (entityName.equals(tableName)) {
                fieldList = table.getJSONArray("fileds");
                break;
            }
        }
        return fieldList;
    }

    public static List<Parameter> getParameterListForCompositeId(List<Id> ids) {
        List<Parameter> parameterList = new ArrayList<>();
        String delimiter = ",";
        for (Id id : ids) {
            parameterList.add(new Parameter(id.getType(), id.getName(), delimiter, true));
        }
        parameterList.get(parameterList.size() - 1).setDelimiter("");
        return parameterList;
    }

    public static String getGetterOrSetterForField(String field, String type) {
        String getterOrSetter = Common.capitalizeFirstLetter(field);
        getterOrSetter = type + getterOrSetter;
        return getterOrSetter;
    }

    public static String getGetterOrSetterForFieldWithBracket(String field, String type) {
        return getGetterOrSetterForField(field, type) + "()";
    }

    public static Boolean hasParameter(List<Parameter> parametersForAMethod, String searchTerm, String searchTermDataType) {
        Boolean result = false;
        for (Parameter parameter : parametersForAMethod) {
            if (parameter.getName().equals(searchTerm) && parameter.getType().equals(searchTermDataType)) {
                result = true;
                break;
            }
        }
        return result;
    }

}


class Entity {

    private String className;

    public Entity(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

class Property {
    private String name;
    private String column;
    private String type;
    private String other; //update="true|false" insert="true|false" lazy="true|false" unique="true|false" length="20" not-null="true|false" precision="2" scale="3"

    public Property(String name, String column, String type, String other) {
        this.name = name;
        this.column = column;
        this.type = type;
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

class Id {
    private String name;
    private String type;
    private String generator; //<generator class="identity" />
    private String column;

    public Id(String name, String type, String generator, String column) {
        this.name = name;
        this.type = type;
        this.generator = generator;
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}

class CompositeIdHBM {

    private String identifierName;
    private String className;
    private List<Id> columns;

    public CompositeIdHBM(String identifierName, String className, List<Id> columns) {
        this.identifierName = identifierName;
        this.className = className;
        this.columns = columns;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Id> getColumns() {
        return columns;
    }

    public void setColumns(List<Id> columns) {
        this.columns = columns;
    }
}

class ForeignKey {
    private String name;
    private String className;
    private String cascade;
    private String propertyRef;
    private String column;

    public ForeignKey(String name, String className, String cascade, String propertyRef, String column) {
        this.name = name;
        this.className = className;
        this.cascade = cascade;
        this.propertyRef = propertyRef;
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCascade() {
        return cascade;
    }

    public void setCascade(String cascade) {
        this.cascade = cascade;
    }

    public String getPropertyRef() {
        return propertyRef;
    }

    public void setPropertyRef(String propertyRef) {
        this.propertyRef = propertyRef;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}

class Parameter {
    private String type;
    private String name;
    private String delimiter;
    private Boolean isRequired;

    public Parameter(String type, String name, String delimiter, Boolean isRequired) {
        this.type = type;
        this.name = name;
        this.delimiter = delimiter;
        this.isRequired = isRequired;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }
}

class Operation {
    private String returnType;
    private String operationName;
    private List<Parameter> Parameters;
    private List<Insert> Insert;
    private List<Update> Update;
    private List<Select> Select;
    private List<Delete> Delete;
    private List<Select> SelectUnique;

    public Operation(String returnType, String operationName, List<Parameter> parameters, List<com.rip.api.common.Insert> insert, List<com.rip.api.common.Update> update, List<com.rip.api.common.Select> select, List<com.rip.api.common.Delete> delete, List<com.rip.api.common.Select> selectUnique) {
        this.returnType = returnType;
        this.operationName = operationName;
        Parameters = parameters;
        Insert = insert;
        Update = update;
        Select = select;
        Delete = delete;
        SelectUnique = selectUnique;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public List<Parameter> getParameters() {
        return Parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        Parameters = parameters;
    }

    public List<com.rip.api.common.Insert> getInsert() {
        return Insert;
    }

    public void setInsert(List<com.rip.api.common.Insert> insert) {
        Insert = insert;
    }

    public List<com.rip.api.common.Update> getUpdate() {
        return Update;
    }

    public void setUpdate(List<com.rip.api.common.Update> update) {
        Update = update;
    }

    public List<com.rip.api.common.Select> getSelect() {
        return Select;
    }

    public void setSelect(List<com.rip.api.common.Select> select) {
        Select = select;
    }

    public List<com.rip.api.common.Delete> getDelete() {
        return Delete;
    }

    public void setDelete(List<com.rip.api.common.Delete> delete) {
        Delete = delete;
    }

    public List<com.rip.api.common.Select> getSelectUnique() {
        return SelectUnique;
    }

    public void setSelectUnique(List<com.rip.api.common.Select> selectUnique) {
        SelectUnique = selectUnique;
    }
}

class Import {
    private String importName;

    public Import(String importName) {
        this.importName = importName;
    }

    public String getImportName() {
        return importName;
    }

    public void setImportName(String importName) {
        this.importName = importName;
    }
}

class Insert {
    private String entity;
    private List<ForeignKeyEntity> ForeignKeyFields;
    private List<EntityField> EntityFields;
    private List<CompositeId> CompositeId;
    private String returnType;

    public Insert() {
    }

    public Insert(String entity, List<ForeignKeyEntity> foreignKeyFields, List<EntityField> entityFields, List<com.rip.api.common.CompositeId> compositeId, String returnType) {
        this.entity = entity;
        ForeignKeyFields = foreignKeyFields;
        EntityFields = entityFields;
        CompositeId = compositeId;
        this.returnType = returnType;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<ForeignKeyEntity> getForeignKeyFields() {
        return ForeignKeyFields;
    }

    public void setForeignKeyFields(List<ForeignKeyEntity> foreignKeyFields) {
        ForeignKeyFields = foreignKeyFields;
    }

    public List<EntityField> getEntityFields() {
        return EntityFields;
    }

    public void setEntityFields(List<EntityField> entityFields) {
        EntityFields = entityFields;
    }

    public List<com.rip.api.common.CompositeId> getCompositeId() {
        return CompositeId;
    }

    public void setCompositeId(List<com.rip.api.common.CompositeId> compositeId) {
        CompositeId = compositeId;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}

class Update extends Insert {
    private String id;

    public Update(String entity, List<ForeignKeyEntity> foreignKeyFields, List<EntityField> entityFields, List<com.rip.api.common.CompositeId> compositeId, String returnType, String id) {
        super(entity, foreignKeyFields, entityFields, compositeId, returnType);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Select {
    private String entity;
    private String returnTypeDTO;
    private List<Limit> Limit;
    private List<Restriction> Restrictions;
    private List<Alias> Aliases;
    private List<Projection> Projections;
    private List<String> Order;
    private List<String> Paging;

    public Select(String entity, String returnTypeDTO, List<com.rip.api.common.Limit> limit, List<Restriction> restrictions, List<Alias> aliases, List<Projection> projections, List<String> order, List<String> paging) {
        this.entity = entity;
        this.returnTypeDTO = returnTypeDTO;
        Limit = limit;
        Restrictions = restrictions;
        Aliases = aliases;
        Projections = projections;
        Order = order;
        Paging = paging;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getReturnTypeDTO() {
        return returnTypeDTO;
    }

    public void setReturnTypeDTO(String returnTypeDTO) {
        this.returnTypeDTO = returnTypeDTO;
    }

    public List<com.rip.api.common.Limit> getLimit() {
        return Limit;
    }

    public void setLimit(List<com.rip.api.common.Limit> limit) {
        Limit = limit;
    }

    public List<Restriction> getRestrictions() {
        return Restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        Restrictions = restrictions;
    }

    public List<Alias> getAliases() {
        return Aliases;
    }

    public void setAliases(List<Alias> aliases) {
        Aliases = aliases;
    }

    public List<Projection> getProjections() {
        return Projections;
    }

    public void setProjections(List<Projection> projections) {
        Projections = projections;
    }

    public List<String> getOrder() {
        return Order;
    }

    public void setOrder(List<String> order) {
        Order = order;
    }

    public List<String> getPaging() {
        return Paging;
    }

    public void setPaging(List<String> paging) {
        Paging = paging;
    }
}

class Delete {
    private String entity;
    private String id;
    private List<CompositeId> CompositeId;

    public Delete(String entity, String id, List<com.rip.api.common.CompositeId> compositeId) {
        this.entity = entity;
        this.id = id;
        CompositeId = compositeId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<com.rip.api.common.CompositeId> getCompositeId() {
        return CompositeId;
    }

    public void setCompositeId(List<com.rip.api.common.CompositeId> compositeId) {
        CompositeId = compositeId;
    }
}

class ForeignKeyEntity {
    private String foreignKeyEntity;
    private String fKVariable;
    private List<ForeignKeyEntityId> FKCompositeId;
    private String fKId;
    private String fKFieldSetter;

    public ForeignKeyEntity(String foreignKeyEntity, String fKVariable, List<ForeignKeyEntityId> FKCompositeId, String fKId, String fKFieldSetter) {
        this.foreignKeyEntity = foreignKeyEntity;
        this.fKVariable = fKVariable;
        this.FKCompositeId = FKCompositeId;
        this.fKId = fKId;
        this.fKFieldSetter = fKFieldSetter;
    }

    public String getForeignKeyEntity() {
        return foreignKeyEntity;
    }

    public void setForeignKeyEntity(String foreignKeyEntity) {
        this.foreignKeyEntity = foreignKeyEntity;
    }

    public String getfKVariable() {
        return fKVariable;
    }

    public void setfKVariable(String fKVariable) {
        this.fKVariable = fKVariable;
    }

    public List<ForeignKeyEntityId> getFKCompositeId() {
        return FKCompositeId;
    }

    public void setFKCompositeId(List<ForeignKeyEntityId> FKCompositeId) {
        this.FKCompositeId = FKCompositeId;
    }

    public String getfKId() {
        return fKId;
    }

    public void setfKId(String fKId) {
        this.fKId = fKId;
    }

    public String getfKFieldSetter() {
        return fKFieldSetter;
    }

    public void setfKFieldSetter(String fKFieldSetter) {
        this.fKFieldSetter = fKFieldSetter;
    }
}

class ForeignKeyEntityId {
    private String foreignKeyEntity;
    private String fKVariable;
    private List<Parameter> Parameters;

    public ForeignKeyEntityId(String foreignKeyEntity, String fKVariable, List<Parameter> parameters) {
        this.foreignKeyEntity = foreignKeyEntity;
        this.fKVariable = fKVariable;
        Parameters = parameters;
    }

    public String getForeignKeyEntity() {
        return foreignKeyEntity;
    }

    public void setForeignKeyEntity(String foreignKeyEntity) {
        this.foreignKeyEntity = foreignKeyEntity;
    }

    public String getfKVariable() {
        return fKVariable;
    }

    public void setfKVariable(String fKVariable) {
        this.fKVariable = fKVariable;
    }

    public List<Parameter> getParameters() {
        return Parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        Parameters = parameters;
    }
}

class EntityField {
    private String entityFieldSetter;
    private String dtoName;
    private String dtoFieldGetter;

    public EntityField(String entityFieldSetter, String dtoName, String dtoFieldGetter) {
        this.entityFieldSetter = entityFieldSetter;
        this.dtoName = dtoName;
        this.dtoFieldGetter = dtoFieldGetter;
    }

    public String getEntityFieldSetter() {
        return entityFieldSetter;
    }

    public void setEntityFieldSetter(String entityFieldSetter) {
        this.entityFieldSetter = entityFieldSetter;
    }

    public String getDtoName() {
        return dtoName;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public String getDtoFieldGetter() {
        return dtoFieldGetter;
    }

    public void setDtoFieldGetter(String dtoFieldGetter) {
        this.dtoFieldGetter = dtoFieldGetter;
    }
}

class CompositeId {
    private String compositeEntityId;
    private List<Parameter> Parameters;
    private String cKFieldSetter;

    public CompositeId(String compositeEntityId, List<Parameter> parameters, String cKFieldSetter) {
        this.compositeEntityId = compositeEntityId;
        Parameters = parameters;
        this.cKFieldSetter = cKFieldSetter;
    }

    public String getCompositeEntityId() {
        return compositeEntityId;
    }

    public void setCompositeEntityId(String compositeEntityId) {
        this.compositeEntityId = compositeEntityId;
    }

    public List<Parameter> getParameters() {
        return Parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        Parameters = parameters;
    }

    public String getcKFieldSetter() {
        return cKFieldSetter;
    }

    public void setcKFieldSetter(String cKFieldSetter) {
        this.cKFieldSetter = cKFieldSetter;
    }
}

class Limit {
    private String limitName;

    public Limit(String limitName) {
        this.limitName = limitName;
    }

    public String getLimitName() {
        return limitName;
    }

    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }
}

class Restriction {
    private String entityFieldName;
    private String restrictionVariableName;

    public Restriction(String entityFieldName, String restrictionVariableName) {
        this.entityFieldName = entityFieldName;
        this.restrictionVariableName = restrictionVariableName;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public String getRestrictionVariableName() {
        return restrictionVariableName;
    }

    public void setRestrictionVariableName(String restrictionVariableName) {
        this.restrictionVariableName = restrictionVariableName;
    }
}

class Alias {
    private String entityFieldName;

    public Alias(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }
}

class Projection {
    private String entityFieldName;
    private String dtoFieldName;

    public Projection(String entityFieldName, String dtoFieldName) {
        this.entityFieldName = entityFieldName;
        this.dtoFieldName = dtoFieldName;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public String getDtoFieldName() {
        return dtoFieldName;
    }

    public void setDtoFieldName(String dtoFieldName) {
        this.dtoFieldName = dtoFieldName;
    }
}