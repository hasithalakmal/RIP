/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.model.impl;


import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 *
 * @author Supun
 */
public class WEdge extends Group{
    protected WNode source;
    protected WNode target;

    Line line;

    public WEdge(WNode source, WNode target) {

        this.source = source;
        this.target = target;

        source.addCellChild(target);
        target.addCellParent(source);

        line = new Line();

        line.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind( target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0));

        getChildren().add( line);

    }

    public WNode getSource() {
        return source;
    }

    public WNode getTarget() {
        return target;
    }
}
