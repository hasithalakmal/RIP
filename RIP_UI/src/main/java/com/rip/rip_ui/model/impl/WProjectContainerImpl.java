package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.api.impl.Converter;
import com.rip.rip_ui.core.enums.NodeType;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.model.WProjectContainer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.collections.ObservableMap;

public class WProjectContainerImpl implements WProjectContainer {

    WNode parentNode;
    private final String PARENT_NODE_ID = "_ROOT_";

    Map<String, WNode> nodeMap; // <id,cell>
    Map<String, WTableNode> tableNodeMap; //<table id, table node>
    Map<String, HashMap<String, WTableField>> tableFieldsMap; // < table id, < <field id>,<field object> > >
    Map<String, WForeignKey> foreignKeysMap; //<foreign_key_id, foreign_key_edge>

    private DTechnicalSpecification techSpec; //keeps technical specifications of project
    private DRequestDetails requestDetails; // keeps request details of project

    /*
    * keeps rest api design data and nodes of the client project
     */
    //data container of final rest api specification
    private DRestApiDesign restApiDesign;

    //<resource_node_id, resource_node>
    private Map<String, WResourceNode> resourceNodeMap;

    // < resource id, < <field id>,<field object> > >
    private Map<String, HashMap<String, WParamField>> resourceFieldsMap;

    public WProjectContainerImpl(ObservableMap<String, Object> projectDetails) {
        this.parentNode = new WNode(PARENT_NODE_ID);
        initializeDataContainers();
        setProjectDetails(projectDetails);
        initializeLists();
    }

    private void initializeDataContainers() {
        techSpec = new DTechnicalSpecification();
        requestDetails = new DRequestDetails();
        restApiDesign = new DRestApiDesign();

    }

    @Override
    public void setProjectDetails(ObservableMap<String, Object> projectDetails) {
        setTechSpec(projectDetails);
        setRequestDetails(projectDetails);
        setRestApiDetails(projectDetails);
    }

    private void setTechSpec(ObservableMap<String, Object> projectDetails) {

        techSpec.setLanguage(projectDetails.get(DataLabel.PROGRAMMING_LANGUAGE).toString());
        techSpec.setRest_framework(projectDetails.get(DataLabel.REST_FRAMEWORK).toString());
        techSpec.setDbms(projectDetails.get(DataLabel.DBMS).toString());
        techSpec.setOrm_framework(projectDetails.get(DataLabel.ORM_FRAMEWORK).toString());
        techSpec.setJs_framework(projectDetails.get(DataLabel.JS_FRAMEWORK).toString());
        techSpec.setBuild_tool(projectDetails.get(DataLabel.BUILD_TOOL).toString());
        techSpec.setTest_framework(projectDetails.get(DataLabel.TEST_FRAMEWORK).toString());
        techSpec.setServer(projectDetails.get(DataLabel.SERVER).toString());

    }

    private void setRequestDetails(ObservableMap<String, Object> projectDetails) {
        requestDetails.setProject_name(projectDetails.get(DataLabel.PROJECT_NAME).toString());
        requestDetails.setDescription(projectDetails.get(DataLabel.PROJECT_DESCRIPTION).toString());
        requestDetails.setVersion(projectDetails.get(DataLabel.VERSION).toString());
        requestDetails.setBase_path(projectDetails.get(DataLabel.BASE_PATH).toString());
        requestDetails.setHost(projectDetails.get(DataLabel.HOST).toString());
        requestDetails.setDb_name(projectDetails.get(DataLabel.DB_NAME).toString());

    }

    private void setRestApiDetails(ObservableMap<String, Object> projectDetails) {
        restApiDesign.setBasePath(projectDetails.get(DataLabel.BASE_PATH).toString());
        restApiDesign.setHost(projectDetails.get(DataLabel.HOST).toString());
        restApiDesign.getInfo().setVersion(projectDetails.get(DataLabel.VERSION).toString());
        restApiDesign.getInfo().setTitle(projectDetails.get(DataLabel.PROJECT_NAME).toString());
        restApiDesign.getInfo().setDescription(projectDetails.get(DataLabel.PROJECT_DESCRIPTION).toString());
    }

    public DTechnicalSpecification getTechSpec() {
        return techSpec;
    }

    public DRequestDetails getRequestDetails() {
        return requestDetails;
    }

    public Map<String, HashMap<String, WTableField>> getTableFieldsMap() {
        return tableFieldsMap;
    }

    private void initializeLists() {

        nodeMap = new HashMap();
        tableNodeMap = new HashMap();
        tableFieldsMap = new HashMap();
        foreignKeysMap = new HashMap();

        resourceNodeMap = new HashMap();
        resourceFieldsMap = new HashMap();
    }

    @Override
    public WNode addNode(String id, NodeType type, HashMap<String, String> details) {

        switch (type) {

            case W_TABLE_NODE:
                WTableNode wTableNode = new WTableNode(id);
                addTableNode(wTableNode);
                return wTableNode;

            case W_RESOURCE_NODE:
                WResourceNode wResourceNode = new WResourceNode(id, details);
                addResourceNode(wResourceNode);
                return wResourceNode;

            case W_TABLE_FIELD:
                WTableField wTableField = new WTableField(id, details);
                tableNodeMap.get(id).getBodyContent().getChildren().add(wTableField);
                addTableFieldNode(id, wTableField);
                return wTableField;

            case W_PARAM_FIELD:
                WParamField wParamField = new WParamField(details);
                resourceNodeMap.get(id).getContentBox().getChildren().add(wParamField);
                addParamFieldNode(id, wParamField);
                return wParamField;

            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    @Override
    public void removeNode(String nodeId, NodeType type) {
        switch (type) {

            case W_TABLE_NODE:
                tableNodeMap.remove(nodeId);
                break;

            case W_RESOURCE_NODE:
                removeResourceNode(nodeId);
                break;

            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    public void addTableNode(WTableNode node) {
        tableNodeMap.put(node.getCellId(), node);
        tableFieldsMap.put(node.getCellId(), new HashMap<String, WTableField>());
    }

    public void addTableFieldNode(String tableId, WTableField node) {
        HashMap<String, WTableField> fields = tableFieldsMap.get(tableId);
        fields.put(node.getCellId(), node);
    }

    public void addNode(WNode node) {
        nodeMap.put(node.getCellId(), node);

    }

    public WForeignKey addEdge(HashMap<String, String> fkDetails) {

        WTableNode baseTable = tableNodeMap.get(fkDetails.get(DataLabel.FK_BASE_TABLE));
        WTableNode referenceTable = tableNodeMap.get(fkDetails.get(DataLabel.FK_REFERENCE_TABLE));
        WForeignKey wForeignKey = new WForeignKey(baseTable, referenceTable, fkDetails);
        foreignKeysMap.put(fkDetails.get(DataLabel.FK_NAME), wForeignKey);
        return wForeignKey;

    }

    public Map<String, WTableNode> getTableNodeMap() {
        return tableNodeMap;
    }

    /*
    * function implementations related to rest api data
     */
    public void addResourceNode(WResourceNode node) {
        resourceNodeMap.put(node.getCellId(), node);
        resourceFieldsMap.put(node.getCellId(), new HashMap());
    }

    public void removeResourceNode(String nodeId) {
        resourceNodeMap.remove(nodeId);
        resourceFieldsMap.remove(nodeId);
    }

    private void addParamFieldNode(String resourceId, WParamField wParamField) {
        resourceFieldsMap.get(resourceId).put(wParamField.getCellId(), wParamField);
    }

    public WRTConnector addRTConnector(HashMap<String, String> resourceDetails) {
        String resourceName = resourceDetails.get(DataLabel.RESOURCE_NAME);
        String tableName = resourceDetails.get(DataLabel.RESOURCE_TABLE);
        String rtConName = "Connection: " + resourceName + "-" + tableName;

        WResourceNode associateResource = resourceNodeMap.get(resourceName);
        WRTConnector rtConnector = new WRTConnector(associateResource, tableNodeMap.get(tableName), rtConName);
        associateResource.setRtConnector(rtConnector);

        return rtConnector;
    }

    public Map<String, WResourceNode> getResourceNodeMap() {
        return resourceNodeMap;
    }

    public Map<String, WParamField> getParamFieldMap(String resourceId) {

        HashMap paramFieldMap = resourceFieldsMap.get(resourceId);
        return paramFieldMap;
    }

    public DRestApiDesign getRestApiDesign() {
        setTags();
        setPaths();
        setDefinitions();
        return restApiDesign;
    }

    /*
    * functions to prepare rest api design object
     */
    public void setDefinitions() {

        HashMap<String, DClass> classes = new HashMap();

        for (Map.Entry<String, HashMap<String, WTableField>> entry : tableFieldsMap.entrySet()) {
            
            String tableName = entry.getKey();
            HashMap<String, WTableField> tablefields = entry.getValue();
            
            DClass dClass = new DClass();
            
            for (WTableField tablefield : tablefields.values()) {
                String fieldName = tablefield.getdTableField().getField_name();
                fieldName = Converter.snakeToCamel(fieldName);
                
                String fieldType = tablefield.getdTableField().getData_type();
                String notNull = tablefield.getdTableField().getNot_null();
                
                if(notNull.equals("true")){
                    dClass.addRequiredAttr(fieldName);
                }
                
                dClass.addAttribute(fieldName, fieldType);
            }
            
            classes.put(tableName, dClass);
        }
        
        restApiDesign.setDefinitions(classes);

    }

    public void setTags() {
        for (String key : tableFieldsMap.keySet()) {
            DTag tag = new DTag();
            tag.setName(key);
            tag.setDescription("description not available");

            restApiDesign.getTags().add(tag);
        }
    }

    public void setPaths() {
        HashMap<String, HashMap<String, DHttpMethod>> paths = new HashMap();

        for (HashMap<String, WParamField> paramFieldMap : resourceFieldsMap.values()) {

            for (WParamField path : paramFieldMap.values()) {

                DPath pathObj = path.getPath();

                HashMap<String, DHttpMethod> methods = pathObj.getMethods();

                try {
                    for (Map.Entry<String, DHttpMethod> entry : methods.entrySet()) {
                        String key = entry.getKey();
                        DHttpMethod value = entry.getValue();

                        paths.get(path.getRealPath()).put(key, value);
                    }

                } catch (Exception e) {
                    paths.put(path.getRealPath(), methods);
                }
            }
        }

        restApiDesign.setPaths(paths);
    }

}
