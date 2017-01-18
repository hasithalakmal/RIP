/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.skins.WParamFieldSkin;
import com.rip.rip_ui.core.text.DataLabel;
import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * Node to keep details about 
 * parameter path
 */
public class WParamField extends WNode{
    
    private WParamFieldSkin layout;
    private DPath path;
    private String realPath;
    private String paramCount;
    
    public WParamField(HashMap<String, String> paramDetails){
                
        super(paramDetails.get(DataLabel.URI));
        path = new DPath(paramDetails);
        realPath = paramDetails.get(DataLabel.REAL_PATH).toString();
        
        paramCount = paramDetails.get(DataLabel.PARAMETER_COUNT).toString();
        
        layout = new WParamFieldSkin(paramDetails);
        Pane skin = layout.getSkin();
        setView(skin);

        
        
    }
        
    public Button getGetButton(){
        return layout.getGetButton();
    }
    
    public Button getPostButton(){
        return layout.getPostButton();
    }
    
    public Button getPutButton(){
        return layout.getPutButton();
    }

    public DPath getPath() {
        return path;
    }
    
    
    
    public Button getDeleteButton(){
        return layout.getDeleteButton();
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getParamCount() {
        return paramCount;
    }
    
    
    
    
}
