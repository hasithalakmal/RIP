package com.rip.rip_ui.api.impl;

import com.rip.rip_ui.api.ProjectHandler;
import com.rip.rip_ui.core.gui_layers.MouseGestures;
import com.rip.rip_ui.core.gui_layers.NodeLayer;
import com.rip.rip_ui.core.gui_layers.ZoomableScrollPane;
import com.rip.rip_ui.core.enums.NodeType;
import com.rip.rip_ui.core.popups.AddParametersWizard;
import com.rip.rip_ui.core.popups.DeleteResourceWarning;
import com.rip.rip_ui.core.popups.DeleteTableWarning;
import com.rip.rip_ui.core.popups.EditParamWizard;
import com.rip.rip_ui.core.popups.ForeignKeyWizard;
import com.rip.rip_ui.core.popups.NewAttributeWizard;
import com.rip.rip_ui.core.popups.NewProjectWizard;
import com.rip.rip_ui.core.popups.NewResourceWizard;
import com.rip.rip_ui.core.popups.NewTableWizard;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.validators.PredicateValidator;
import com.rip.rip_ui.model.WProjectContainer;
import com.rip.rip_ui.model.impl.DHttpMethod;
import com.rip.rip_ui.model.impl.WForeignKey;
import com.rip.rip_ui.model.impl.WNode;
import com.rip.rip_ui.model.impl.WParamField;
import com.rip.rip_ui.model.impl.WProjectContainerImpl;
import com.rip.rip_ui.model.impl.WRTConnector;
import com.rip.rip_ui.model.impl.WResourceNode;
import com.rip.rip_ui.model.impl.WTableField;
import com.rip.rip_ui.model.impl.WTableNode;
import com.rip.rip_ui.persistence.FileHandler;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class ProjectHandlerImpl implements ProjectHandler {

    private WProjectContainer wProjectContainer;

    private Group canvas;

    private NodeLayer nodeLayer;

    private MouseGestures mouseGestures;

    private ZoomableScrollPane scrollPane;

    //is the current project saved or not
    private boolean isProjectSaved = true;

    private boolean isProjectCreated = false;

    //keeps methods for conditional validations
    private PredicateValidator predicateValidator;

    @Override
    public boolean createProject() {

        if (!isProjectSaved) {
            saveProject();
        }

        try {

            NewProjectWizard newProjectWizard = new NewProjectWizard();
            ObservableMap<String, Object> projectDetails = newProjectWizard.getProjectDetails();

            if (!projectDetails.isEmpty()) {
                initializeProject(projectDetails);
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    public void editProject() {
        try {

            NewProjectWizard newProjectWizard = new NewProjectWizard(wProjectContainer.getTechSpec(), wProjectContainer.getRequestDetails());
            ObservableMap<String, Object> projectDetails = newProjectWizard.getProjectDetails();
            wProjectContainer.setProjectDetails(projectDetails);

        } catch (Exception e) {

        }
    }

    /*Initialize Project Elements*/
    private void initializeProject(ObservableMap<String, Object> projectDetails) {
        wProjectContainer = new WProjectContainerImpl(projectDetails);
        initializeGUIElements(projectDetails);
        initializeConfiguration();

    }

    //Initialize GUI elements
    private void initializeGUIElements(ObservableMap<String, Object> projectDetails) {

        canvas = new Group();
        nodeLayer = new NodeLayer();
        canvas.getChildren().add(nodeLayer);
        mouseGestures = new MouseGestures(this);

        scrollPane = new ZoomableScrollPane(canvas);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

    }

    //Initialize project configuration objects
    private void initializeConfiguration() {
        this.isProjectCreated = true;
        this.isProjectSaved = false;

        predicateValidator = new PredicateValidator(wProjectContainer);
    }

    @Override
    public void addTable() {

        try {

            NewTableWizard newTableWizard = new NewTableWizard();
            String tableName = newTableWizard.getTableName();

            if (!tableName.equals("")) {
                WNode node = wProjectContainer.addNode(tableName, NodeType.W_TABLE_NODE, null);
                updateNode(node);
                addFeaturesToTable((WTableNode) node);
            }

        } catch (Exception e) {
        }

    }

    public void removeTableNode(String nodeId, WNode node) {
        DeleteTableWarning deleteTableWarning = new DeleteTableWarning(nodeId);
        if (deleteTableWarning.getUserResponse()) {
            wProjectContainer.removeNode(nodeId, NodeType.W_TABLE_NODE);
            updateRemoval(node);
        }

    }

    @Override
    public void addTableField(String tableId) {
        NewAttributeWizard newAttributeWizard = new NewAttributeWizard();
        HashMap<String, String> fieldDetails = newAttributeWizard.getFieldDetails();
        WNode node = wProjectContainer.addNode(tableId, NodeType.W_TABLE_FIELD, fieldDetails);
    }

    @Override
    public void addForeignKey() {

        if (isProjectCreated && wProjectContainer.getTableFieldsMap().size() >= 2) {
            ForeignKeyWizard foreignKeyWizard = new ForeignKeyWizard(wProjectContainer.getTableFieldsMap());
            HashMap<String, String> fkDetails = foreignKeyWizard.getFkDetails();
            WForeignKey edge = wProjectContainer.addEdge(fkDetails);
            getNodeLayer().getChildren().add(0, edge.getLine());
        }
    }

    
    @Override
    public void loadLocalProject() {

    }

    @Override
    public double getScale() {
        return this.scrollPane.getScaleValue();
    }

    private void updateNode(WNode node) {
        getNodeLayer().getChildren().add(node);
        mouseGestures.makeDraggable(node);

    }

    private void updateRemoval(WNode node) {
        getNodeLayer().getChildren().remove(node);
    }

    private void addFeaturesToTable(WTableNode tableNode) {
        mouseGestures.makeTableClickable(tableNode);
    }

    private void addFeaturesToResource(WResourceNode resourceNode) {
        mouseGestures.makeResourceClickable(resourceNode);
    }

    private void addFeaturesToParamField(WParamField node, HashMap<String, String> paramDetails) {
        mouseGestures.makeParamFieldClickable(node, paramDetails);
    }

    public Pane getNodeLayer() {
        return this.nodeLayer;
    }

    @Override
    public WProjectContainer getProjectContainer() {
        return this.wProjectContainer;
    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    
    /*
    * function implementations related to related to rest api design
     */
    @Override
    public void addResource() {
        if (isProjectCreated && !predicateValidator.isNoTables()) {

            ArrayList<String> tableArray = predicateValidator.getTablesHavingFields();

            if (tableArray.size() > 0) {
                NewResourceWizard newResourceWizard = new NewResourceWizard(predicateValidator,tableArray);
                HashMap<String, String> resourceDetails = newResourceWizard.getResourceDetails();

                if (!resourceDetails.isEmpty()) {

                    WNode resourceNode = wProjectContainer.addNode(
                            resourceDetails.get(DataLabel.RESOURCE_NAME),
                            NodeType.W_RESOURCE_NODE, resourceDetails
                    );

                    updateNode(resourceNode);
                    addFeaturesToResource((WResourceNode) resourceNode);
                    addRTConnector(resourceDetails);
                }
            }

        }

    }

    @Override
    public void removeResourceNode(String nodeId, WResourceNode node) {
        DeleteResourceWarning deleteTableWarning = new DeleteResourceWarning(nodeId);
        if (deleteTableWarning.getUserResponse()) {
            wProjectContainer.removeNode(nodeId, NodeType.W_RESOURCE_NODE);
            getNodeLayer().getChildren().remove(node.getRtConnector().getRtConnector());
            updateRemoval(node);
        }

    }

    @Override
    public void addParameters(String nodeId) {
        WResourceNode resourceNode = wProjectContainer.getResourceNodeMap().get(nodeId);
        String resourceTable = resourceNode.getResourceTable();

        HashMap<String, WTableField> resourceTableFields = wProjectContainer.getTableFieldsMap().get(resourceTable);

        AddParametersWizard addParamWizard = new AddParametersWizard(resourceNode.getUri(), resourceTable, resourceTableFields);
        HashMap<String, String> paramDetails = addParamWizard.getParamDetails();

        WNode node = wProjectContainer.addNode(nodeId, NodeType.W_PARAM_FIELD, paramDetails);
        addFeaturesToParamField((WParamField) node, paramDetails);
    }

    @Override
    public void editGetParameters(String resourceId, String pathId) {
        WParamField paramField = wProjectContainer.getParamFieldMap(resourceId).get(pathId);
        
        DHttpMethod get = paramField.getPath().getGet();
        String paramCount = paramField.getParamCount();

        EditParamWizard editParamWizard = new EditParamWizard(get,paramCount);
        get.setModifiedDetails(editParamWizard.getModifiedDetails(),paramCount);
    }

    @Override
    public void editPostParameters(String resourceId, String pathId) {
        WParamField paramField = wProjectContainer.getParamFieldMap(resourceId).get(pathId);
        
        DHttpMethod post = paramField.getPath().getPost();

        EditParamWizard editParamWizard = new EditParamWizard(post,"n/a");
        post.setModifiedDetails(editParamWizard.getModifiedDetails());
    }

    @Override
    public void editPutParameters(String resourceId, String pathId) {
        WParamField paramField = wProjectContainer.getParamFieldMap(resourceId).get(pathId);
        
        DHttpMethod put = paramField.getPath().getPut();
 
        EditParamWizard editParamWizard = new EditParamWizard(put,"n/a");
        put.setModifiedDetails(editParamWizard.getModifiedDetails());
    }

    public void editDeleteParameters(String resourceId, String pathId) {
        WParamField paramField = wProjectContainer.getParamFieldMap(resourceId).get(pathId);
        
        DHttpMethod delete = paramField.getPath().getDelete();
        String paramCount = paramField.getParamCount();

        EditParamWizard editParamWizard = new EditParamWizard(delete,paramCount);
        delete.setModifiedDetails(editParamWizard.getModifiedDetails(),paramCount);
    }

    private void addRTConnector(HashMap<String, String> resourceDetails) {
        WRTConnector rtConnector = wProjectContainer.addRTConnector(resourceDetails);
        getNodeLayer().getChildren().add(0, rtConnector.getRtConnector());
    }
    
    /*
    * functions related to object conversions and project savings
    */
    
    @Override
    public void saveProject() {
        FileHandler fh = new FileHandler();
        fh.writeToJSON(wProjectContainer.getRestApiDesign(), "sample");
    }
    
    


}
