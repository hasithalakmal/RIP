/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.popups;

import com.rip.rip_ui.core.data.HttpCodes;
import com.rip.rip_ui.core.data.ReservedWords;
import com.rip.rip_ui.core.data.TypeMappingData;
import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.model.impl.DHttpMethod;
import com.rip.rip_ui.model.impl.DParameter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;

/**
 *
 * @author Supun
 */
public class EditParamWizard {

    private final int GRID_V_GAP = 10;
    private final int GRID_H_GAP = 10;

    private Wizard editParamWizard;

    private final String SUMMARY = "Summary";
    private final String DESCRIPTION = "Description";
    private final String OPERATION_ID = "Operation Id";

    private final String IN_LBL = "In";
    private final String DESCRIPTION_LBL = "Description";
    private final String REQUIRED_LBL = "Required";
    private final String TYPE_LBL = "Type";

    private final String RESPONSE_LBL = "Response";
    private final String SCHEMA_TYPE = "Response Type";

    private ArrayList<WizardPane> wizardPagesList;
    private ArrayList<DParameter> parameters;
    private ObservableMap<String, Object> modifiedDetails;
    
    private DHttpMethod method;

    private String paramCount;

    public EditParamWizard(DHttpMethod method, String paramCount) {
        
        this.method = method;
        parameters = method.getParameters();

        this.paramCount = paramCount;

        editParamWizard = new Wizard();
        wizardPagesList = new ArrayList<>();

        WizardPane frontPage = initializeFrontPage();
        wizardPagesList.add(frontPage);

        initializeWizardPages(this.parameters.size());

        WizardPane responsePage = initializeResponsePage();
        wizardPagesList.add(responsePage);

        WizardPane finalPage = new WizardPane();
        wizardPagesList.add(finalPage);

        editParamWizard.setFlow(new Wizard.LinearFlow(wizardPagesList));

        editParamWizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                modifiedDetails = editParamWizard.getSettings();
            }
        });

    }

    private void initializeWizardPages(int pagesCount) {

        for (int i = 0; i < pagesCount; i++) {
            wizardPagesList.add(createPage(i));
        }

    }

    private WizardPane initializeFrontPage() {
        return new WizardPane() {
            {
                GridPane frontPageGrid = createGridPane();
                int row = 0;

                frontPageGrid.add(new Label(OPERATION_ID), 0, row);
                TextField txOperationId = createTextField(DataLabel.EDIT_PARAM_OPT_ID);
                txOperationId.setText(method.getOperationId());
                frontPageGrid.add(txOperationId, 1, row++);

                frontPageGrid.add(new Label(DESCRIPTION), 0, row);
                TextArea txDescription = createTextArea(DataLabel.EDIT_PARAM_DESCRIPTION);
                frontPageGrid.setRowSpan(txDescription, 1);
                txDescription.setText(method.getDescription());
                frontPageGrid.add(txDescription, 1, row++);

                frontPageGrid.add(new Label(SUMMARY), 0, row);
                TextArea txSummary = createTextArea(DataLabel.EDIT_PARAM_SUMMARY);
                frontPageGrid.setRowSpan(txSummary, 2);
                txSummary.setText(method.getSummary());
                frontPageGrid.add(txSummary, 1, row++);

                setContent(frontPageGrid);
            }
        };

    }

    private WizardPane initializeResponsePage() {

        return new WizardPane() {
            {
                GridPane responsePageGrid = createGridPane();
                int row = 0;

                responsePageGrid.add(new Label(RESPONSE_LBL), 0, row);
                ChoiceBox bxResponse = new ChoiceBox();
                bxResponse.setId(DataLabel.HTTP_CODE);
                bxResponse.setItems(FXCollections.observableArrayList(HttpCodes.HTTP_CODES));
                bxResponse.getSelectionModel().selectFirst();
                responsePageGrid.add(bxResponse, 1, row++);

                responsePageGrid.add(new Label(DESCRIPTION), 0, row);
                TextArea txCodeDescription = createTextArea(DataLabel.CODE_DESCRIPTION);
                responsePageGrid.add(txCodeDescription, 1, row++);

                responsePageGrid.add(new Label(SCHEMA_TYPE), 0, row);
                ChoiceBox bxSchemaType = new ChoiceBox();
                bxSchemaType.setId(DataLabel.RESPONSE_DATA_TYPE);
                bxSchemaType.setItems(FXCollections.observableArrayList(TypeMappingData.JSON_DATA_TYPE));
                bxSchemaType.getSelectionModel().selectFirst();
                responsePageGrid.add(bxSchemaType, 1, row++);

                setContent(responsePageGrid);

            }

        };

    }

    private WizardPane createPage(int i) {
        return new WizardPane() {
            {
                GridPane pageGrid = createGridPane();
                int row = 0;

                pageGrid.add(new Label("Parameter" + i), 0, row);

                if (paramCount.equals("0")) {
                    ChoiceBox chBox = new ChoiceBox();
                    chBox.setId(DataLabel.PARAM_PREFIX + i);
                    chBox.setItems(FXCollections.observableArrayList(ReservedWords.KEYS));
                    chBox.getSelectionModel().selectFirst();
                    pageGrid.add(chBox, 1, row++);

                } else {
                    TextField txParam = createTextField(DataLabel.PARAM_PREFIX + i);
                    txParam.setText(parameters.get(i).getName());
                    txParam.setEditable(false);
                    pageGrid.add(txParam, 1, row++);
                }

                pageGrid.add(new Label(IN_LBL), 0, row);
                TextField txIn = createTextField(DataLabel.PARAMETER_TYPE + i);
                txIn.setText(parameters.get(i).getIn());
                txIn.setEditable(false);
                pageGrid.add(txIn, 1, row++);

                pageGrid.add(new Label(DESCRIPTION_LBL), 0, row);
                TextArea txParamDescription = createTextArea(DataLabel.PARAM_DESCRIPTION + i);
                pageGrid.add(txParamDescription, 1, row++);

                pageGrid.add(new Label(REQUIRED_LBL), 0, row);
                ChoiceBox chBxReq = new ChoiceBox();
                chBxReq.setItems(FXCollections.observableArrayList("true", "false"));
                chBxReq.getSelectionModel().selectFirst();
                chBxReq.setId(DataLabel.PARAM_REQUIRED + i);
                pageGrid.add(chBxReq, 1, row++);

                pageGrid.add(new Label(TYPE_LBL), 0, row);
                TextField txParamType = createTextField(DataLabel.DATA_TYPE_PREFIX + i);
                
                if(paramCount.equals("0")){
                    txParamType.setText("integer");
                }else{
                    txParamType.setText(parameters.get(i).getType());
                }
                
                txParamType.setEditable(false);
                pageGrid.add(txParamType, 1, row++);

                setContent(pageGrid);
            }
        };
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(GRID_V_GAP);
        gridPane.setHgap(GRID_H_GAP);
        return gridPane;
    }

    private TextArea createTextArea(String id) {
        TextArea textArea = new TextArea();
        textArea.setId(id);
        //GridPane.setHgrow(textArea, Priority.ALWAYS);
        return textArea;
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setId(id);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        return textField;
    }

    public ObservableMap<String, Object> getModifiedDetails() {
        return modifiedDetails;
    }

}
