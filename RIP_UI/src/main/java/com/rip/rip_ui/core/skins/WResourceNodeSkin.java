package com.rip.rip_ui.core.skins;

import com.rip.rip_ui.core.text.Title;
import com.rip.rip_ui.core.text.Tooltips;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class WResourceNodeSkin{

    private boolean isExpanded = true;
    private Pane skin;
    private Button minimizeButton;
    private Button deleteButton;
    private Button addButton;
    private Button editButton;
    
    private VBox contentVBox;
    private VBox mainVBox;
    
    private StackPane headerBox;
    private Rectangle rectangleBox;
    private Text headerText;
    private HBox buttonHBox;
    
    private final int MIN_HEADER_WIDTH = 400;
    private final int MIN_HEADER_HEIGHT = 27;
    private final String HEADER_BOX_COLOR = "#E7E7E7";
    private final String HEADER_BOX_STROKE_COLOR = "#C2C2C2";

    public WResourceNodeSkin(String id) {
        setControlButtons();
        initializeResourceHeader(id);
        initializeContentBox();
        initializeMainBox();
        
        
        mainVBox.getChildren().addAll(headerBox, contentVBox);
        this.skin = new Pane();
        this.skin.getChildren().addAll(
                mainVBox
        );

    }
    
    private void initializeResourceHeader(String id){
        initializeHeaderText(id);
        //setControlButtons();
        initializeHeaderBox();
    
    }
            
    private void initializeHeaderBox(){
        
        rectangleBox = new Rectangle(MIN_HEADER_WIDTH,MIN_HEADER_HEIGHT);
        rectangleBox.setFill(Color.web(HEADER_BOX_COLOR, 1.0));
        rectangleBox.setStroke(Color.web(HEADER_BOX_STROKE_COLOR, 1.0));
        headerBox = new StackPane();
        headerBox.getChildren().addAll(rectangleBox, headerText, buttonHBox);
    } 
    
    private void initializeContentBox(){
        contentVBox = new VBox();
    }
    
    private void initializeMainBox(){
        mainVBox = new VBox();
    }
            
    private void initializeHeaderText(String id){
        headerText = new Text(id + Title.NODE_SKIN_TITLE_POSTFIX);
    }                

    private void setControlButtons() {
        setMinimizeButton();
        setAddButton();
        setEditButton();
        setDeleteButton();

        buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(minimizeButton, addButton, editButton, deleteButton);
        buttonHBox.setAlignment(Pos.TOP_RIGHT);

    }

    private void setMinimizeButton() {
        minimizeButton = new Button("^");
        minimizeButton.setTooltip(new Tooltip(Tooltips.RESOURCE_COLLAPSE));
        minimizeButton.setOnMouseClicked(onMouseClickedEventHandler);
    }

    private void setAddButton() {
        addButton = new Button("+");
        addButton.setTooltip(new Tooltip(Tooltips.ADD_PARAMETERS));
    }

    private void setDeleteButton() {
        deleteButton = new Button("x");
        deleteButton.setTooltip(new Tooltip(Tooltips.RESOURCE_DELETE));
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton() {
        editButton = new Button("e");
        editButton.setTooltip(new Tooltip(Tooltips.RESOURCE_EDIT));
    }

    public Pane getSkin() {
        return this.skin;
    }

    EventHandler<MouseEvent> onMouseClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            if (event.getClickCount() == 1) {
                if (isExpanded) {
                    contentVBox.setVisible(!isExpanded);
                    isExpanded = !isExpanded;

                } else {
                    contentVBox.setVisible(!isExpanded);
                    isExpanded = !isExpanded;
                }
            }
        }

    };
    

    public Button getMinimizeButton() {
        return minimizeButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public VBox getContentVBox() {
        return contentVBox;
    }


    
    

    
    


}
