/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.skins.WDefaultFieldSkin;
import com.rip.rip_ui.core.skins.WFieldSkin;
import com.rip.rip_ui.core.skins.WPKFieldSkin;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.text.InputLabel;
import java.util.HashMap;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Supun
 */
public class WTableField extends WNode {

    private WFieldSkin layout;
    private String parentTable;
    private DTableField dTableField;

    public WTableField(String tableId,HashMap<String, String> details) {
        
        super(details.get(DataLabel.TABLE_FIELD_NAME));
        setParentTable(tableId);
        createTableFieldNode(details);
        createTableFieldData(details);

        
    }
    
    private void createTableFieldNode(HashMap<String, String> details){
        if (details.get(DataLabel.TABLE_FIELD_PK)=="true") {
            layout = new WPKFieldSkin(
                    details.get(DataLabel.TABLE_FIELD_NAME), 
                    details.get(DataLabel.TABLE_FIELD_TYPE)
            );
                        
        } 
        else {
            layout = new WDefaultFieldSkin(
                    details.get(DataLabel.TABLE_FIELD_NAME), 
                    details.get(DataLabel.TABLE_FIELD_TYPE)
            );
        }
        
        StackPane skin = layout.getSkin();
        setView(skin);
    }
    
    private void createTableFieldData(HashMap<String, String> details){
        dTableField = new DTableField(details);
    }

    public String getParentTable() {
        return parentTable;
    }

    public void setParentTable(String parentTable) {
        this.parentTable = parentTable;
    }

    public DTableField getdTableField() {
        return dTableField;
    }
    
    
    
    
}
