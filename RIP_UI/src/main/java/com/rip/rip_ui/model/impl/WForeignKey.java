/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;

import com.rip.rip_ui.core.skins.FKEdgeSkin;
import com.rip.rip_ui.core.text.DataLabel;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 *
 * @author Supun
 */
public class WForeignKey{
    
    private String fkId;
    private WTableNode base;
    private WTableNode reference;

    private Line line;

    public WForeignKey(WTableNode base, WTableNode reference, HashMap<String, String> fkDetails) {

        this.fkId = fkDetails.get(DataLabel.FK_NAME);
        this.base = base;
        this.reference = reference;
        
        FKEdgeSkin fkSkin = new FKEdgeSkin(fkDetails);

        line = fkSkin.getFkEdge();

        line.startXProperty().bind( base.layoutXProperty().add(base.getBoundsInParent().getWidth()/2.0));
        line.startYProperty().bind( base.layoutYProperty().add(4));

        line.endXProperty().bind( reference.layoutXProperty().add( reference.getBoundsInParent().getWidth()/2.0));
        line.endYProperty().bind( reference.layoutYProperty().add(4));

        //getChildren().add(line);

    }

    public Line getLine() {
        return line;
    }
    
    

    public String getFkId() {
        return fkId;
    }

    public WNode getSource() {
        return base;
    }

    public WNode getTarget() {
        return reference;
    }
    
}
