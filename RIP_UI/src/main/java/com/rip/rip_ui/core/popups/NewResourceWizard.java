/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.popups;

import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.validators.PredicateValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author Supun
 */
public class NewResourceWizard {

    private final String CREATE_NEW_RESOURCE = "Create New Resource";

    private final String INFO = "Info: Resource can be attached on only with tables having"
            + "\none or more fields.\n\n"
            + "After creation of a resource you cannot modify "
            + "\nthe table data\n\n"
            + "Use UpperCamelNotation as Resource Name for "
            + "\ndiagram clarity";

    private final String CREATE_BTN = "Create";

    private final String RESOURCE_NAME = "Resource Name";

    private final String RESOURCE_TABLE = "Resource Table";

    private final String URI = "URI";

    private ObservableList<String> tables;

    private int row = 0;

    private HashMap<String, String> resourceDetails;

    private TextField txURI;

    private ValidationSupport validationSupport;

    private Node createButton;
    
    private PredicateValidator predicateValidator;

    public NewResourceWizard(PredicateValidator predicateValidator, ArrayList<String> tables) {
        this.predicateValidator = predicateValidator;
        resourceDetails = new HashMap<>();
        initializeValidationSupport();
        readAllTables(tables);
        setUIElements();

    }

    private void initializeValidationSupport() {
        validationSupport = new ValidationSupport();
        validationSupport.initInitialDecoration();
    }

    private void setUIElements() {
        Dialog<HashMap<String, String>> dialog = new Dialog();
        dialog.setTitle(CREATE_NEW_RESOURCE);
        dialog.setHeaderText(INFO);

        ButtonType createButtonType = new ButtonType(CREATE_BTN);

        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label(RESOURCE_NAME), 0, row);
        TextField resourceName = new TextField();
        validationSupport.registerValidator(
                resourceName,
                //Validator.createEqualsValidator("Color should be WHITE or BLACK", Arrays.asList("a", "b")));
                Validator.combine(predicateValidator::isTextFieldEmpty,predicateValidator::isResourceExists));
        grid.add(resourceName, 1, row++);

        grid.add(new Label(RESOURCE_TABLE), 0, row);
        ChoiceBox bxResourceTable = new ChoiceBox();
        bxResourceTable.valueProperty().addListener(onChangeTableListner);
        bxResourceTable.setItems(tables);
        grid.add(bxResourceTable, 1, row++);

        grid.add(new Label(URI), 0, row);
        txURI = new TextField();
        txURI.setEditable(false);
        grid.add(txURI, 1, row++);

        bxResourceTable.getSelectionModel().selectFirst();

        createButton = dialog.getDialogPane().lookupButton(createButtonType);
        createButton.setDisable(true);
        validationSupport.invalidProperty().addListener(listener);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                HashMap<String, String> resourceDetails = new HashMap<String, String>();
                resourceDetails.put(DataLabel.RESOURCE_NAME, DataLabel.RESOURCE_ID_PREFIX + resourceName.getText());
                resourceDetails.put(DataLabel.RESOURCE_TABLE, bxResourceTable.getValue().toString());
                resourceDetails.put(DataLabel.URI, txURI.getText());
                return resourceDetails;
            }
            return null;
        });

        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(resourceDetails -> {
            this.resourceDetails = resourceDetails;
        });
    }

    ChangeListener<String> onChangeTableListner = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            txURI.setText("/" + newValue);
        }

    };

    ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            createButton.setDisable(newValue);
        }

    };

    private void readAllTables(ArrayList<String> tableArray) {

        tables = FXCollections.observableArrayList();

        for (String table : tableArray) {
            tables.add(table);
        }
    }

    public HashMap<String, String> getResourceDetails() {
        return resourceDetails;
    }


}
