/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.skins;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author Supun
 */
public class WPKFieldSkin implements WFieldSkin{
    
    private StackPane skin;
    private Text fieldName;
    private Rectangle rec;
    private HBox hbox;

    public WPKFieldSkin(String id, String dataType) {

        fieldName = new Text("      "+dataType+" "+id);
        
        hbox = new HBox();
        
        hbox.getChildren().add(fieldName);
        
        hbox.setAlignment(Pos.CENTER_LEFT);
        
        rec = new Rectangle(200, 22);
        rec.setFill(Color.web("#DF9496", 1.0));
        
        
        skin = new StackPane();

        skin.getChildren()
                .addAll(
                        rec,
                        hbox
                );
        

    }
    

        
    public StackPane getSkin(){
        return skin;
    }


}
