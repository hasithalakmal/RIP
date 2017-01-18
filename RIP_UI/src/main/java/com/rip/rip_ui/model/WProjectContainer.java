
package com.rip.rip_ui.model;

import com.rip.rip_ui.core.enums.NodeType;
import com.rip.rip_ui.model.impl.DRequestDetails;
import com.rip.rip_ui.model.impl.DRestApiDesign;
import com.rip.rip_ui.model.impl.DTechnicalSpecification;
import com.rip.rip_ui.model.impl.WEdge;
import com.rip.rip_ui.model.impl.WForeignKey;
import com.rip.rip_ui.model.impl.WNode;
import com.rip.rip_ui.model.impl.WParamField;
import com.rip.rip_ui.model.impl.WRTConnector;
import com.rip.rip_ui.model.impl.WResourceNode;
import com.rip.rip_ui.model.impl.WTableField;
import com.rip.rip_ui.model.impl.WTableNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableMap;


/**
 * Keeps all the project related details 
 * and visual elements and handle all
 * interior functions
 * 
 */
public interface WProjectContainer {
    
    
    /*Setting project details in project details object*/
    void setProjectDetails(ObservableMap<String, Object> projectDetails);
    
    /*return Technical Project details*/
    DTechnicalSpecification getTechSpec();
    
    /*return Request Project details*/
    DRequestDetails getRequestDetails();
    
   
    
    WNode addNode(String id, NodeType type, HashMap<String,String> details);
    
    void removeNode(String nodeId, NodeType type);
    
    WForeignKey addEdge(HashMap<String, String> fkDetails);
    
    Map<String, HashMap<String, WTableField>> getTableFieldsMap();
    
    Map<String, WTableNode> getTableNodeMap();

    
    /*
    * function declerations related to rest api data
    */
    
    //returns full rest api data object
    DRestApiDesign getRestApiDesign();
       
    //returns the resource node map
    Map<String, WResourceNode> getResourceNodeMap();
    
    //returns the parameter fields node map
    Map<String, WParamField> getParamFieldMap(String resourceId);
    
    //returns a resource table connector
    WRTConnector addRTConnector(HashMap<String, String> resourceDetails); 

     
}
