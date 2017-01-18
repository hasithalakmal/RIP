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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class WTableNodeSkin {

    private TitledPane titledPane;
    private boolean isExpanded = true;
    private StackPane skin;
    private Button minimizeButton;
    private Button deleteButton;
    private Button addButton;
    private Button editButton;
    private HBox hbox;
    private VBox vbox;

    public WTableNodeSkin(String id) {
        initializeTitledPane(id);
        setControlButtons();

        Region rg = new Region();
        this.skin = new StackPane();
        this.skin.getChildren().addAll(
                titledPane, rg, hbox, vbox
        );

    }

    private void initializeTitledPane(String id) {
        titledPane = new TitledPane();
        titledPane.setCollapsible(true);
        titledPane.setText(Title.TABLE_HEADER_PREFIX + id + Title.NODE_SKIN_TITLE_POSTFIX);
        titledPane.setExpanded(isExpanded);
        vbox = new VBox();
        titledPane.setContent(vbox);
    }

    private void setControlButtons() {
        setMinimizeButton();
        setAddButton();
        setEditButton();
        setDeleteButton();

        hbox = new HBox();
        hbox.getChildren().addAll(minimizeButton, addButton, editButton, deleteButton);
        hbox.setAlignment(Pos.TOP_RIGHT);
        //hbox.setOnMouseClicked(onMouseClickedEventHandler);

        skin.setAlignment(hbox, Pos.TOP_RIGHT);

    }

    private void setMinimizeButton() {
        minimizeButton = new Button("-");
        minimizeButton.setTooltip(new Tooltip(Tooltips.TABLE_COLLAPSE));
        minimizeButton.setOnMouseClicked(onMouseClickedEventHandler);
    }

    private void setAddButton() {
        addButton = new Button("+");
        addButton.setTooltip(new Tooltip(Tooltips.FIELD_ADD));
    }

    private void setDeleteButton() {
        deleteButton = new Button("x");
        deleteButton.setTooltip(new Tooltip(Tooltips.TABLE_DELETE));
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton() {
        editButton = new Button("e");
        editButton.setTooltip(new Tooltip(Tooltips.TABLE_EDIT));
    }

    public StackPane getSkin() {
        return this.skin;
    }

    EventHandler<MouseEvent> onMouseClickedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            if (event.getClickCount() == 1) {
                if (isExpanded) {
                    titledPane.setExpanded(!isExpanded);
                    isExpanded = !isExpanded;

                } else {
                    titledPane.setExpanded(!isExpanded);
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

    public HBox getHbox() {
        return hbox;
    }

    public VBox getVbox() {
        return vbox;
    }

}
