package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.skins.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class WTableNode extends WNode {
    
    private WTableNodeSkin layout;
    
    public WTableNode(String id) {
        super(id);

        layout = new WTableNodeSkin(id);
        StackPane skin = layout.getSkin();
        setView(skin);

    }
    
    public Button getTableSkinAddButton(){
        return layout.getAddButton();
    }
    
    public Button getTableSkinDeleteButton(){
        return layout.getDeleteButton();
    }
    
    public VBox getBodyContent(){
        return layout.getVbox();
    }


}
