/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.popups;

import com.rip.rip_ui.core.data.ERData;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.text.InputLabel;
import com.rip.rip_ui.core.text.Title;
import java.util.HashMap;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Supun
 */
public class NewAttributeWizard {

    private int row = 0;
    private HashMap<String, String> newFieldDetails;

    public NewAttributeWizard() {

        Dialog<HashMap<String, String>> dialog = new Dialog();
        dialog.setTitle(Title.ADD_TABLE_FIELD);
        dialog.setHeaderText("Tips");

        ButtonType addButtonType = new ButtonType("Add");
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType,ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label(InputLabel.TABLE_FIELD_NAME), 0, row);
        TextField txAttrName = new TextField();
        grid.add(txAttrName, 1, row++);

        grid.add(new Label(InputLabel.TABLE_DATA_TYPE), 0, row);
        ChoiceBox bxDataType = new ChoiceBox();
        bxDataType.setItems(FXCollections.observableList(ERData.DATA_TYPES));
        bxDataType.getSelectionModel().selectFirst();
        grid.add(bxDataType, 1, row++);

        grid.add(new Label(InputLabel.FIELD_SIZE), 0, row);
        TextField txFieldSize = new TextField();
        grid.add(txFieldSize, 1, row++);

        CheckBox pkChBx = new CheckBox();
        CheckBox aiChBx = new CheckBox();
        CheckBox nnChBx = new CheckBox();
        CheckBox uqChBx = new CheckBox();

        HBox hboxPK = new HBox(pkChBx, new Label(InputLabel.PRIMARY_KEY));
        grid.add(hboxPK, 0, row);

        HBox hboxAI = new HBox(aiChBx, new Label(InputLabel.AUTO_INCREMENT));
        grid.add(hboxAI, 1, row++);
        
        HBox hboxNN = new HBox(nnChBx, new Label(InputLabel.NOT_NULL));
        grid.add(hboxNN, 0, row);
        
        HBox hboxUQ = new HBox(uqChBx, new Label(InputLabel.UNIQUE));
        grid.add(hboxUQ, 1, row);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                HashMap<String,String> fieldDetails = new HashMap<String,String>();
                fieldDetails.put(DataLabel.TABLE_FIELD_NAME, txAttrName.getText());
                fieldDetails.put(DataLabel.TABLE_FIELD_TYPE, bxDataType.getValue().toString());
                fieldDetails.put(DataLabel.TABLE_FIELD_PK, (pkChBx.isSelected() ? "true" : "false"));
                fieldDetails.put(DataLabel.TABLE_FIELD_AI, (aiChBx.isSelected() ? "true" : "false"));
                fieldDetails.put(DataLabel.TABLE_FIELD_NN, (nnChBx.isSelected() ? "true" : "false"));
                fieldDetails.put(DataLabel.TABLE_FIELD_UQ, (uqChBx.isSelected() ? "true" : "false"));
                return fieldDetails;
            }
            return null;
        });

        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(fieldDetails -> {
            newFieldDetails = fieldDetails;
        });
    }

    

    public HashMap<String, String> getFieldDetails() {
        return newFieldDetails;
    }

}
