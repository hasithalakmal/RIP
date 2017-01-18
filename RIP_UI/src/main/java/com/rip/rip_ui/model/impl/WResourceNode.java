package com.rip.rip_ui.model.impl;


import com.rip.rip_ui.core.skins.*;
import com.rip.rip_ui.core.text.DataLabel;
import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class WResourceNode extends WNode {
    
    private WResourceNodeSkin layout;
    private WRTConnector rtConnector;
    private String resourceTable;
    private String uri;

    public WResourceNode(String id, HashMap<String, String> details) {
        super(id);
        
        resourceTable = details.get(DataLabel.RESOURCE_TABLE);
        uri = details.get(DataLabel.URI);
        
        layout = new WResourceNodeSkin(id);
        Pane skin = layout.getSkin();
        setView(skin);
    }
    
    public Button getResourceSkinAddButton(){
        return layout.getAddButton();
    }
    
    public Button getResourceSkinEditButton(){
        return layout.getEditButton();
    }
    
    public Button getResourceSkinDeleteButton(){
        return layout.getDeleteButton();
    }

    public String getResourceTable() {
        return resourceTable;
    }

    public String getUri() {
        return uri;
    }
    
    public VBox getContentBox(){
        return layout.getContentVBox();
    }

    public WRTConnector getRtConnector() {
        return rtConnector;
    }

    public void setRtConnector(WRTConnector rtConnector) {
        this.rtConnector = rtConnector;
    }
    
    
    

    

}