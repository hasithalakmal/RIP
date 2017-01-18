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
import com.rip.rip_ui.model.impl.WTableField;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ForeignKeyWizard {

    private int row = 0;
    private HashMap<String, String> fkDetails;
    private ObservableList<String> tables;
    private ObservableList<String> fieldsOfSelectedBaseTable;
    private ObservableList<String> fieldsOfSelectedReferenceTable;
    private ObservableList<String> referenceTables;
    private ComboBox bxTableOne;
    private ComboBox bxTableTwo;
    private ComboBox bxTableFieldOne;
    private ComboBox bxTableFieldTwo;
    private Map<String, HashMap<String, WTableField>> tableWithFields;

    public ForeignKeyWizard(Map<String, HashMap<String, WTableField>> tableWithFields) {
        this.tableWithFields = tableWithFields;
        readAllTables(tableWithFields);
        setUIElements();

    }

    private void setUIElements() {
        Dialog<HashMap<String, String>> dialog = new Dialog();
        dialog.setTitle(Title.ADD_FOREIGN_KEY);
        dialog.setHeaderText("Tips");

        ButtonType addButtonType = new ButtonType("Add");
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label(InputLabel.FOREIGN_KEY_NAME), 0, row);
        TextField txFkName = new TextField();
        grid.add(txFkName, 1, row++);

        grid.add(new Label(InputLabel.BASE_TABLE), 0, row);
        bxTableOne = new ComboBox();
        bxTableOne.setItems(tables);
        bxTableOne.valueProperty().addListener(onChangeBaseTableListner);
        grid.add(bxTableOne, 1, row++);

        grid.add(new Label(InputLabel.BASE_FIELD), 0, row);
        bxTableFieldOne = new ComboBox();
        bxTableFieldOne.valueProperty().addListener(onChangeBaseFieldListner);
        grid.add(bxTableFieldOne, 1, row++);

        grid.add(new Label(InputLabel.REFERENCE_TABLE), 0, row);
        bxTableTwo = new ComboBox();
        bxTableTwo.valueProperty().addListener(onChangeReferenceTableListner);
        grid.add(bxTableTwo, 1, row++);

        grid.add(new Label(InputLabel.REFERENCE_FIELD), 0, row);
        bxTableFieldTwo = new ComboBox();
        grid.add(bxTableFieldTwo, 1, row++);
        
        grid.add(new Label(InputLabel.FK_RELATIONSHIP), 0, row);
        ChoiceBox bxRelation = new ChoiceBox();
        bxRelation.setItems(FXCollections.observableArrayList(ERData.RELATIONSHIPS));
        grid.add(bxRelation, 1, row++);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                HashMap<String, String> fkDetails = new HashMap<String, String>();
                fkDetails.put(DataLabel.FK_NAME, txFkName.getText());
                fkDetails.put(DataLabel.FK_BASE_TABLE, bxTableOne.getValue().toString());
                fkDetails.put(DataLabel.FK_BASE_TABLE_FIELD, bxTableFieldOne.getValue().toString());
                fkDetails.put(DataLabel.FK_REFERENCE_TABLE, bxTableTwo.getValue().toString());
                fkDetails.put(DataLabel.FK_REFERENCE_TABLE_FIELD, bxTableFieldTwo.getValue().toString());
                fkDetails.put(DataLabel.FK_RELATION, bxRelation.getValue().toString());
                return fkDetails;
            }
            return null;
        });

        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(fkDetails -> {
            this.fkDetails = fkDetails;
        });
    }

    ChangeListener<String> onChangeBaseTableListner = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            fetchFieldsInBaseTable(newValue);
        }

    };

    ChangeListener<String> onChangeBaseFieldListner = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            selectMatchingReferenceTables(newValue);
        }

    };

    ChangeListener<String> onChangeReferenceTableListner = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            fetchFieldsInReferenceTable(newValue);
        }

    };

    private void readAllTables(Map<String, HashMap<String, WTableField>> tableWithFields) {

        tables = FXCollections.observableArrayList();

        for (String key : tableWithFields.keySet()) {
            tables.add(key);
        }
    }

    private void fetchFieldsInBaseTable(String selectedTable) {
        fieldsOfSelectedBaseTable = FXCollections.observableArrayList();

        for (String key : tableWithFields.get(selectedTable).keySet()) {
            fieldsOfSelectedBaseTable.add(key);
        }

        bxTableFieldOne.setItems(fieldsOfSelectedBaseTable);
        bxTableFieldOne.getSelectionModel().selectFirst();

    }

    private void fetchFieldsInReferenceTable(String selectedRefTable) {
        fieldsOfSelectedReferenceTable = FXCollections.observableArrayList();
        String selectedBaseTable = bxTableOne.getValue().toString();
        String selectedBaseField = bxTableFieldOne.getValue().toString();

        String baseDataType = tableWithFields.get(selectedBaseTable).get(selectedBaseField).getdTableField().getData_type();

        for (String fieldKey : tableWithFields.get(selectedRefTable).keySet()) {

            WTableField tempField = tableWithFields.get(selectedRefTable).get(fieldKey);

            if (tempField.getdTableField().getData_type().equals(baseDataType)) {
                fieldsOfSelectedReferenceTable.add(fieldKey);
            }

        }

        bxTableFieldTwo.setItems(fieldsOfSelectedReferenceTable);
        bxTableFieldTwo.getSelectionModel().selectFirst();

    }

    private void selectMatchingReferenceTables(String selectedBaseField) {
        String selectedTable = bxTableOne.getValue().toString();
        String baseDataType = tableWithFields.get(selectedTable).get(selectedBaseField).getdTableField().getData_type();
        referenceTables = FXCollections.observableArrayList();

        for (String tableKey : tableWithFields.keySet()) {
            boolean isMatch = false;
            
            if (!selectedTable.equals(tableKey)) {
                for (String fieldKey : tableWithFields.get(tableKey).keySet()) {

                    WTableField tempField = tableWithFields.get(tableKey).get(fieldKey);

                    if (tempField.getdTableField().getData_type().equals(baseDataType)) {
                        isMatch = true;
                        break;
                    }
                }
                
                if(isMatch)
                    referenceTables.add(tableKey);
            }

        }

        bxTableTwo.setItems(referenceTables);
        bxTableTwo.getSelectionModel().selectFirst();
    }

    public HashMap<String, String> getFkDetails() {
        return fkDetails;
    }
    
    



}
