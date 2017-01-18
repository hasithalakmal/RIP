package com.rip.rip_ui.core.popups;

import com.rip.rip_ui.core.text.DataLabel;
import com.rip.rip_ui.core.text.Info;
import com.rip.rip_ui.core.text.InputLabel;
import com.rip.rip_ui.core.text.Regex;
import com.rip.rip_ui.core.text.Title;
import com.rip.rip_ui.core.text.Warning;
import com.rip.rip_ui.model.impl.DRequestDetails;
import com.rip.rip_ui.model.impl.DTechnicalSpecification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.Wizard.LinearFlow;
import org.controlsfx.dialog.WizardPane;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * Template for New Project Wizard
 *
 */
public class NewProjectWizard {

    private final int GRID_V_GAP = 10;
    private final int GRID_H_GAP = 10;
    
    private ValidationSupport vsProjectName, vsVersion, vsDBName, vsHost;
    private TextField txprojectName, txVersion, txDatabase, txHost, txBasePath;
    private ChoiceBox bxProLang, bxRestFramework, bxDBMS, bxORMFramework, bxJSFramework, bxBuildTool, bxtestFramework, bxServer;
    private TextArea txDescription;
    private Wizard wizard;
    private final int PAGE_WIDTH = 500;
    private final int PAGE_HEIGHT = 300;

    int row = 0;

    ObservableMap<String, Object> projectDetails;

    public NewProjectWizard() {
        
        initializeWizard();
        wizard.setTitle(Title.CREATE_NEW_PROJECT);

        // show wizard and wait for response
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                projectDetails = wizard.getSettings();
            }
        });

    }
    
    public NewProjectWizard(DTechnicalSpecification techSpec, DRequestDetails requestDetails){
        initializeWizard();
        wizard.setTitle(Title.EDIT_PROJECT_DETAILS);
        setFieldValues(techSpec,requestDetails);
        // show wizard and wait for response
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                this.projectDetails = wizard.getSettings();
            }
        });
    }
    
    private void setFieldValues(DTechnicalSpecification techSpec, DRequestDetails requestDetails){
        txprojectName.setText(requestDetails.getProject_name());
        txVersion.setText(requestDetails.getVersion());
        txDatabase.setText(requestDetails.getDb_name());
        txHost.setText(requestDetails.getHost());
        txBasePath.setText(requestDetails.getBase_path());
        txDescription.setText(requestDetails.getDescription());
        
        bxProLang.getSelectionModel().select(techSpec.getLanguage());
        bxRestFramework.getSelectionModel().select(techSpec.getRest_framework());
        bxDBMS.getSelectionModel().select(techSpec.getDbms());
        bxORMFramework.getSelectionModel().select(techSpec.getOrm_framework());
        bxJSFramework.getSelectionModel().select(techSpec.getJs_framework());
        bxBuildTool.getSelectionModel().select(techSpec.getBuild_tool());
        bxtestFramework.getSelectionModel().select(techSpec.getTest_framework());
        bxServer.getSelectionModel().select(techSpec.getServer());
        
        
    }
    
    private void initializeWizard(){
        wizard = new Wizard();

        initializeValidationSupports();

        //Page 01 - Basic Project Details
        WizardPane page1 = initializePageOne();
        
        row = 0;

        //Page 02 - Technical Details
        WizardPane page2 = initializePageTwo();
        row = 0;

        //Page 03 - Testing and Config details
        WizardPane page3 = initializePageThree();

        //Final Page
        WizardPane page4 = initializeFinalPage();

        page1.setMinSize(PAGE_WIDTH, PAGE_HEIGHT);
        page2.setMinSize(PAGE_WIDTH, PAGE_HEIGHT);
        page3.setMinSize(PAGE_WIDTH, PAGE_HEIGHT);
 
        page1.setHeaderText(Title.PAGE_1_HEADER);
        page2.setHeaderText(Title.PAGE_2_HEADER);
        page3.setHeaderText(Title.PAGE_3_HEADER);
        
        // create and assign the flow
        wizard.setFlow(new LinearFlow(page1, page2, page3, page4));
    }

    private void initializeValidationSupports() {
        vsProjectName = new ValidationSupport();
        vsVersion = new ValidationSupport();
        vsDBName = new ValidationSupport();
        vsHost = new ValidationSupport();

        vsProjectName.initInitialDecoration();
        vsVersion.initInitialDecoration();
        vsDBName.initInitialDecoration();
        vsHost.initInitialDecoration();
    }

    private WizardPane initializePageOne() {
        return new WizardPane() {
            {
                GridPane page1Grid = createGridPane();

                page1Grid.add(new Label(InputLabel.PROJECT_NAME_LBL), 0, row);
                txprojectName = createTextField(DataLabel.PROJECT_NAME);
                vsProjectName.registerValidator(
                        txprojectName,
                        Validator.createRegexValidator(
                                Warning.SNAKE_CASE_WARNING,
                                Regex.SNAKE_CASE_REGEX,
                                Severity.WARNING
                        )
                );

                page1Grid.add(txprojectName, 1, row++);

                page1Grid.add(new Label(InputLabel.PROJECT_DESCRIPTION), 0, row);
                txDescription = createTextArea(DataLabel.PROJECT_DESCRIPTION);
                txDescription.setMaxHeight(75);
                txDescription.setMaxWidth(400);
                page1Grid.add(txDescription, 1, row++);

                page1Grid.add(new Label(InputLabel.VERSION_LBL), 0, row);
                txVersion = createTextField(DataLabel.VERSION);
                txVersion.setText("1.0.0");
                vsVersion.registerValidator(
                        txVersion,
                        Validator.createRegexValidator(
                                Warning.NUMBERS_DOTS_WARNING,
                                Regex.ONLY_NUMBERS_DOTS,
                                Severity.WARNING
                        )
                );
                page1Grid.add(txVersion, 1, row++);

                page1Grid.add(new Label(InputLabel.DB_NAME_LBL), 0, row);
                txDatabase = createTextField(DataLabel.DB_NAME);
                vsDBName.registerValidator(
                        txDatabase,
                        Validator.createRegexValidator(
                                Warning.SNAKE_CASE_WARNING,
                                Regex.SNAKE_CASE_REGEX,
                                Severity.WARNING
                        )
                );
                page1Grid.add(txDatabase, 1, row++);

                setContent(page1Grid);
            }

            @Override
            public void onEnteringPage(Wizard wizard) {
                wizard.invalidProperty().unbind();
                
            }

        };

    }
    
    private WizardPane initializePageTwo(){
        return new WizardPane() {
            {
                GridPane page2Grid = createGridPane();

                page2Grid.add(new Label(InputLabel.PRO_LANG_LBL), 0, row);
                bxProLang = createChoiceBox(DataLabel.PROGRAMMING_LANGUAGE);
                page2Grid.add(bxProLang, 1, row++);

                page2Grid.add(new Label(InputLabel.REST_FRAMEWORK_LBL), 0, row);
                bxRestFramework = createChoiceBox(DataLabel.REST_FRAMEWORK);
                page2Grid.add(bxRestFramework, 1, row++);

                row = createLineSeperator(page2Grid, row);

                page2Grid.add(new Label(InputLabel.DBMS_LBL), 0, row);
                bxDBMS = createChoiceBox(DataLabel.DBMS);
                page2Grid.add(bxDBMS, 1, row++);

                page2Grid.add(new Label(InputLabel.ORM_FRAMEWORK_LBL), 0, row);
                bxORMFramework = createChoiceBox(DataLabel.ORM_FRAMEWORK);
                page2Grid.add(bxORMFramework, 1, row++);

                row = createLineSeperator(page2Grid, row);

                page2Grid.add(new Label(InputLabel.JS_FRAMEWORK_LBL), 0, row);
                bxJSFramework = createChoiceBox(DataLabel.JS_FRAMEWORK);
                page2Grid.add(bxJSFramework, 1, row++);

                setContent(page2Grid);

            }

            @Override
            public void onEnteringPage(Wizard wizard) {
                wizard.invalidProperty().unbind();
                
            }
        };
    }
    
    private WizardPane initializePageThree(){
        return new WizardPane() {
            {
                GridPane page3Grid = createGridPane();

                page3Grid.add(new Label(InputLabel.BUILD_TOOL), 0, row);
                bxBuildTool = createChoiceBox(DataLabel.BUILD_TOOL);
                page3Grid.add(bxBuildTool, 1, row++);

                page3Grid.add(new Label(InputLabel.TEST_FRAMEWORK), 0, row);
                bxtestFramework = createChoiceBox(DataLabel.TEST_FRAMEWORK);
                page3Grid.add(bxtestFramework, 1, row++);

                row = createLineSeperator(page3Grid, row);

                page3Grid.add(new Label(InputLabel.SERVER), 0, row);
                bxServer = createChoiceBox(DataLabel.SERVER);
                page3Grid.add(bxServer, 1, row++);

                page3Grid.add(new Label(InputLabel.HOST), 0, row);
                txHost = createTextField(DataLabel.HOST);
                vsHost.registerValidator(
                        txHost,
                        Validator.createRegexValidator(
                                Warning.WEBSITE,
                                Regex.WEBSITE_REGEX,
                                Severity.WARNING
                        )
                );
                page3Grid.add(txHost, 1, row++);

                page3Grid.add(new Label(InputLabel.BASE_PATH), 0, row);
                txBasePath = createTextField(DataLabel.BASE_PATH);
                txBasePath.setText("/api");
                txBasePath.setEditable(false);
                page3Grid.add(txBasePath, 1, row++);

                setContent(page3Grid);

            }

            @Override
            public void onEnteringPage(Wizard wizard) {
                wizard.invalidProperty().unbind();
                wizard.invalidProperty().bind(vsHost.invalidProperty());
            }
        };
    }
    
    private WizardPane initializeFinalPage(){
        return new WizardPane() {
            {
                GridPane page4Grid = createGridPane();

                page4Grid.add(new Label(Info.NEW_PROJECT_CREATION_INFO), 0, 0);

                setContent(page4Grid);
            }
        };
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setId(id);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        return textField;
    }

    private TextArea createTextArea(String id) {
        TextArea textArea = new TextArea();
        textArea.setId(id);
        //GridPane.setHgrow(textArea, Priority.ALWAYS);
        return textArea;
    }

    private int createLineSeperator(GridPane grid, int rowNo) {
        final Separator sepHor = new Separator();
        grid.setConstraints(sepHor, 0, rowNo++);
        grid.setColumnSpan(sepHor, 2);
        grid.getChildren().add(sepHor);
        return rowNo;
    }

    private ChoiceBox createChoiceBox(String id) {

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setId(id);
        choiceBox.setMinWidth(200);

        switch (id) {
            case DataLabel.PROGRAMMING_LANGUAGE:
                choiceBox.setItems(FXCollections.observableArrayList("Java"));
                break;

            case DataLabel.REST_FRAMEWORK:
                choiceBox.setItems(FXCollections.observableArrayList("Jersy", "RESTEasy"));
                break;

            case DataLabel.ORM_FRAMEWORK:
                choiceBox.setItems(FXCollections.observableArrayList("Hibernate"));
                break;

            case DataLabel.DBMS:
                choiceBox.setItems(FXCollections.observableArrayList("MySQL"));
                break;

            case DataLabel.JS_FRAMEWORK:
                choiceBox.setItems(FXCollections.observableArrayList("Pure JavaScript"));
                break;

            case DataLabel.BUILD_TOOL:
                choiceBox.setItems(FXCollections.observableArrayList("Maven, Gradle"));
                break;

            case DataLabel.TEST_FRAMEWORK:
                choiceBox.setItems(FXCollections.observableArrayList("JUnit"));
                break;

            case DataLabel.SERVER:
                choiceBox.setItems(FXCollections.observableArrayList("Apache Tomcat"));
                break;

        }

        choiceBox.getSelectionModel().selectFirst();
        GridPane.setHgrow(choiceBox, Priority.ALWAYS);
        return choiceBox;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(GRID_V_GAP);
        gridPane.setHgap(GRID_H_GAP);
        return gridPane;
    }

    private ComboBox<String> createComboBox(String id) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setId(id);
        GridPane.setHgrow(comboBox, Priority.ALWAYS);
        return comboBox;
    }

    public ObservableMap<String, Object> getProjectDetails() throws NullPointerException {
        return projectDetails;
    }

}
