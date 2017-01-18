/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.skins;

import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.text.InputLabel;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 *
 * Edge skin for Foreign Key
 */
public class FKEdgeSkin {

    private Line fkEdge;
    private ContextMenu ctxMenu;
    private MenuItem ctxItem;

    public FKEdgeSkin(HashMap<String, String> fkDetails) {

        createFKEdge();
        setToolTip(fkDetails);
        setEventHandlers();
        setContextMenu();
    }

    private void createFKEdge() {
        fkEdge = new Line();
        fkEdge.setStrokeWidth(4);
        fkEdge.setStroke(Paint.valueOf("#000000"));

    }

    private void setToolTip(HashMap<String, String> fkDetails) {

        StringBuilder tip = new StringBuilder();

        tip.append(InputLabel.FOREIGN_KEY_NAME);
        tip.append(fkDetails.get(DataLabel.FK_NAME));
        tip.append("\n");

        tip.append(InputLabel.BASE_TABLE);
        tip.append(fkDetails.get(DataLabel.FK_BASE_TABLE));
        tip.append("\n");

        tip.append(InputLabel.BASE_FIELD);
        tip.append(fkDetails.get(DataLabel.FK_BASE_TABLE_FIELD));
        tip.append("\n");

        tip.append(InputLabel.REFERENCE_TABLE);
        tip.append(fkDetails.get(DataLabel.FK_REFERENCE_TABLE));
        tip.append("\n");

        tip.append(InputLabel.REFERENCE_FIELD);
        tip.append(fkDetails.get(DataLabel.FK_REFERENCE_TABLE_FIELD));
        tip.append("\n");

        tip.append(InputLabel.FK_RELATIONSHIP);
        tip.append(fkDetails.get(DataLabel.FK_RELATION));
        tip.append("\n");

        Tooltip t = new Tooltip(tip.toString());
        Tooltip.install(fkEdge, t);
    }

    private void setEventHandlers() {
        fkEdge.setOnMouseEntered(onMouseEnteredEventHandler);
        fkEdge.setOnMouseExited(onMouseExitedEventHandler);
        fkEdge.setOnMouseClicked(onEdgeRightClickedEventHandler);
    }

    private void setContextMenu() {
        ctxMenu = new ContextMenu();
        ctxItem = new MenuItem("Delete");
        ctxMenu.getItems().add(ctxItem);

    }

    public Line getFkEdge() {
        return fkEdge;
    }

    EventHandler<MouseEvent> onMouseEnteredEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            fkEdge.setStroke(Color.web("#66d9ff", 1.0));
        }

    };

    EventHandler<MouseEvent> onMouseExitedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            fkEdge.setStroke(Paint.valueOf("#000000"));
        }

    };

    EventHandler<MouseEvent> onEdgeRightClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {
                ctxMenu.show(fkEdge, event.getScreenX(), event.getScreenY());
            }
        }

    };

}
