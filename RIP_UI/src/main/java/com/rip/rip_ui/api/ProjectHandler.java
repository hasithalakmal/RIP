
package com.rip.rip_ui.api;

import com.rip.rip_ui.model.WProjectContainer;
import com.rip.rip_ui.model.impl.WNode;
import com.rip.rip_ui.model.impl.WResourceNode;
import java.util.HashMap;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/*
 * Handles all the activities related to 
 * a single client project
 */
public interface ProjectHandler {
    
    /*input the basic requirements of 
     *the project and creates project
     */
    boolean createProject();
    
    /*edit project*/
    void editProject();
    
    //saves project in local disk
    void saveProject();
    
    //load project from local disk
    void loadLocalProject();
    
    //returns the scroll pane zoom value
    double getScale();
    
    //adds a table node to the model
    void addTable();
    
    //removes table node from model
    void removeTableNode(String nodeId, WNode node);
    
    //adds a field to a table
    void addTableField(String tableId);   
    
    WProjectContainer getProjectContainer();
    
    ScrollPane getScrollPane();
    
    Pane getNodeLayer();
    
    //adds a foreign key
    void addForeignKey();
    
    
    
    /*
    * function declerations 
    * related to rest api design
    */
    
    //adds a main resource
    void addResource();
    
    //removes a resource node
    void removeResourceNode(String nodeId, WResourceNode node);
    
    //adds parameters and paths to a resource
    void addParameters(String nodeId);
    
    //edits added parameters related to get method
    void editGetParameters(String resourceId, String pathId);
    
    //edits added parameters related to post method
    void editPostParameters(String resourceId, String pathId);
    
    //edits added parameters related to put method
    void editPutParameters(String resourceId, String pathId);
    
    //edits added parameters related to delete method
    void editDeleteParameters(String resourceId, String pathId);


    
    
    
}
