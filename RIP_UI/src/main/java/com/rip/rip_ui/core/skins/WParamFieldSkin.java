/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.skins;

import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.text.Title;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Supun
 */
public class WParamFieldSkin {
    
    private Pane skin;
    
    private final int MIN_FIELD_WIDTH = 400;
    private final int MIN_FIELD_HEIGHT = 27;
    private final String FIELD_BOX_COLOR = "#BDCFBD";
    private final String FIELD_BOX_STROKE_COLOR = "#A5BF78";
    
    private StackPane fieldBox;
    
    private Rectangle rectangleBox;
    private Text fieldText;
    
    private Button getButton;
    private Button postButton;
    private Button putButton;
    private Button deleteButton;
    
    private final String getBtnText = "GET";
    private final String postBtnText = "POST";
    private final String putBtnText = "PUT";
    private final String deleteBtnText = "DELETE";
    
    private ContextMenu ctxMenu;
    private MenuItem ctxItem;
    
    private HBox buttonHBox;
    
    public WParamFieldSkin(HashMap<String, String> paramDetails){
        
        setControlButtons(paramDetails.get(DataLabel.GET),
                        paramDetails.get(DataLabel.POST),
                        paramDetails.get(DataLabel.PUT),
                        paramDetails.get(DataLabel.DELETE));
        
        initializeFieldText(paramDetails.get(DataLabel.URI));
        initializeFieldBox();
        
        this.skin = new Pane();
        this.skin.getChildren().addAll(
                fieldBox
        );
        
        setContextMenu();
        setEventHandlers();
  
    }
    
    private void setContextMenu() {
        ctxMenu = new ContextMenu();
        ctxItem = new MenuItem("Delete");
        ctxMenu.getItems().add(ctxItem);

    }
    
    private void setEventHandlers(){
    
        skin.setOnMouseClicked(onPathRightClickedEventHandler);
    }

    
    private void initializeFieldText(String id){
        fieldText = new Text(id);
        
    }
    
    private void initializeFieldBox(){
        rectangleBox = new Rectangle(MIN_FIELD_WIDTH,MIN_FIELD_HEIGHT);
        rectangleBox.setFill(Color.web(FIELD_BOX_COLOR, 1.0));
        rectangleBox.setStroke(Color.web(FIELD_BOX_STROKE_COLOR, 1.0));
        fieldBox = new StackPane();
        fieldBox.getChildren().addAll(rectangleBox, fieldText, buttonHBox);
    }
    
    private void setControlButtons(String get, String post, String put, String delete){
        
        buttonHBox = new HBox();
        
        if(get.equals("true")){
            setGetButton();
            buttonHBox.getChildren().add(getButton);
        }
        
        if(post.equals("true")){
            setPostButton();
            buttonHBox.getChildren().add(postButton);
        }
        
        if(put.equals("true")){
            setPutButton();
            buttonHBox.getChildren().add(putButton);
        }
        
        if(delete.equals("true")){
            setDeleteButton();
            buttonHBox.getChildren().add(deleteButton);
        }
        
        buttonHBox.setAlignment(Pos.CENTER_RIGHT);
        
    
    }
    
    private void setGetButton(){
        getButton = new Button(getBtnText);
    }
    
    private void setPostButton(){
        postButton = new Button(postBtnText);
    }
    
    private void setPutButton(){
        putButton = new Button(putBtnText);
    }
    
    private void setDeleteButton(){
        deleteButton = new Button(deleteBtnText);
    }
    
    
    public Pane getSkin(){
        return skin;
    }

    public Button getGetButton() {
        return getButton;
    }

    public Button getPostButton() {
        return postButton;
    }

    public Button getPutButton() {
        return putButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
    
    EventHandler<MouseEvent> onPathRightClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {
                ctxMenu.show(skin, event.getScreenX(), event.getScreenY());
            }
        }

    };
    
    
}
