/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.skins.RTEdgeSkin;
import javafx.scene.shape.Line;

/**
 *
 * @author Supun
 */
public class WRTConnector {

    private String rtConId;
    private WTableNode referenceTable;
    private WResourceNode baseResource;
    private Line rtConnector;

    public WRTConnector(WResourceNode baseResource, WTableNode referenceTable, String rtConId) {
        this.rtConId = rtConId;
        this.baseResource = baseResource;
        this.referenceTable = referenceTable;
//        System.out.println(this.rtConId);
//        System.out.println(this.baseResource.getCellId());
//        System.out.println(this.referenceTable.getCellId());
//        
        RTEdgeSkin rtEdgeSkin = new RTEdgeSkin(rtConId);
        
        rtConnector = rtEdgeSkin.getRtEdge();

        rtConnector.startXProperty().bind( baseResource.layoutXProperty().add(baseResource.getBoundsInParent().getWidth()/2.0));
        rtConnector.startYProperty().bind( baseResource.layoutYProperty().add(4));

        rtConnector.endXProperty().bind( referenceTable.layoutXProperty().add( referenceTable.getBoundsInParent().getWidth()/2.0));
        rtConnector.endYProperty().bind( referenceTable.layoutYProperty().add(4));
    }

    public Line getRtConnector() {
        return rtConnector;
    }
    
    

}
