/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.skins;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 *
 * @author Supun
 */
public class RTEdgeSkin {
    
    private Line rtEdge;
    private ContextMenu ctxMenu;
    private MenuItem ctxItem;
    
    public RTEdgeSkin(String connId){
        createRTEdge();
        setToolTip(connId);
        setEventHandlers();
        setContextMenu();
    }
    
    private void createRTEdge() {
        rtEdge = new Line();
        rtEdge.setStrokeWidth(4);
        rtEdge.setStroke(Paint.valueOf("#800000"));

    }
    
    private void setToolTip(String connId) {
        Tooltip t = new Tooltip(connId);
        Tooltip.install(rtEdge, t);
    }
    
    private void setContextMenu() {
        ctxMenu = new ContextMenu();
        ctxItem = new MenuItem("Delete");
        ctxMenu.getItems().add(ctxItem);

    }
    
    private void setEventHandlers() {
        rtEdge.setOnMouseEntered(onMouseEnteredEventHandler);
        rtEdge.setOnMouseExited(onMouseExitedEventHandler);
        rtEdge.setOnMouseClicked(onEdgeRightClickedEventHandler);
    }
    
    EventHandler<MouseEvent> onMouseEnteredEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            rtEdge.setStroke(Color.web("#66d9ff", 1.0));
        }

    };

    EventHandler<MouseEvent> onMouseExitedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            rtEdge.setStroke(Paint.valueOf("#800000"));
        }

    };

    EventHandler<MouseEvent> onEdgeRightClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {
                ctxMenu.show(rtEdge, event.getScreenX(), event.getScreenY());
            }
        }

    };

    public Line getRtEdge() {
        return rtEdge;
    }
    
    
}
