/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.controllers;

import com.rip.rip_ui.application.wizard.diagram_tool.controllers.DiagramToolController;
import com.rip.rip_ui.application.wizard.models.ProjectHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Supun
 */
public class WizardController implements Initializable {
    
    ProjectHandler projectHandler;
    
    final int TECH_SPEC_SIZE = 17;
    final int REQ_DETAILS_SIZE = 3;
    
    private String[] reqDetails = new String[REQ_DETAILS_SIZE];
    private String[] techSpec = new String[TECH_SPEC_SIZE];
    
    @FXML
    private TextField projectNameInput;
    
    @FXML
    private TextField versionInput;
    
    @FXML
    private TextField dbNameInput;
    
    @FXML
    private ComboBox proLanguageInput;
        
    @FXML
    private ComboBox restFrameworkInput;
    
    @FXML
    private ComboBox ormFrameworkInput;
    
    @FXML
    private ComboBox dbmsInput;
    
    @FXML
    private ComboBox archiInput;
    
    @FXML
    private ComboBox authInput;
    
    @FXML
    private ComboBox htmlFrameworkInput;
    
    @FXML
    private ComboBox jsFrameworkInput;
    
    @FXML
    private ComboBox themeInput;
    
    @FXML
    private ComboBox serverInput;
    
    @FXML
    private ComboBox buildToolInput;
    
    @FXML
    private ComboBox testFrameworkInput;
    
    @FXML
    private ComboBox techDocInput;
    
    @FXML
    private ComboBox apiDocInput;
    
    @FXML
    private ComboBox testDocInput;
    
    
    @FXML
    public void initializeProjectCreation(ActionEvent event) throws IOException{
        reqDetails[0] = projectNameInput.getText();
        reqDetails[1] = versionInput.getText();
        reqDetails[2] = dbNameInput.getText();
        
        techSpec[0] = (String) proLanguageInput.getValue();
        techSpec[1] = (String) restFrameworkInput.getValue();
        techSpec[2] = (String) ormFrameworkInput.getValue();
        techSpec[3] = (String) dbmsInput.getValue();
        techSpec[4] = (String) testFrameworkInput.getValue();
        techSpec[5] = (String) htmlFrameworkInput.getValue();
        techSpec[6] = (String) jsFrameworkInput.getValue();
        techSpec[7] = (String) themeInput.getValue();
        techSpec[8] = (String) archiInput.getValue();
        techSpec[9] = (String) serverInput.getValue();
        techSpec[10] = (String) buildToolInput.getValue();
        techSpec[11] = (String) apiDocInput.getValue();
        techSpec[12] = (String) techDocInput.getValue();
        techSpec[13] = (String) testDocInput.getValue();
        techSpec[14] = (String) authInput.getValue();
                
        projectHandler = new ProjectHandler();
        projectHandler.createNewProject(reqDetails, techSpec);
        
        loadDiagramTool(event,"/fxml/DiagramTool.fxml");
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        proLanguageInput.getItems().addAll(
            "Java",
            "PHP"  
        );
        proLanguageInput.getSelectionModel().select("Java");
        
        restFrameworkInput.getItems().addAll(
            "Play",
            "Jersy"  
        );
        restFrameworkInput.getSelectionModel().select("Play");
        
        ormFrameworkInput.getItems().addAll(
            "Hibernate"
              
        );
        ormFrameworkInput.getSelectionModel().select("Hibernate");
                
        dbmsInput.getItems().addAll(
            "MySQL"
              
        );
        dbmsInput.getSelectionModel().select("MySQL");
        
        archiInput.getItems().addAll(
            "Default"
              
        );
        archiInput.getSelectionModel().select("Default");
        
        authInput.getItems().addAll(
            "Oauth2"
              
        );
        authInput.getSelectionModel().select("Oauth2");
        
        htmlFrameworkInput.getItems().addAll(
            "Bootstrap"
              
        );
        htmlFrameworkInput.getSelectionModel().select("Bootstrap");
        
        jsFrameworkInput.getItems().addAll(
            "None"
              
        );
        jsFrameworkInput.getSelectionModel().select("None");
        
        themeInput.getItems().addAll(
            "Default"
              
        );
        themeInput.getSelectionModel().select("Default");
        
        serverInput.getItems().addAll(
            "Apache"
              
        );
        serverInput.getSelectionModel().select("Apache");
        
        buildToolInput.getItems().addAll(
            "Maven"
              
        );
        buildToolInput.getSelectionModel().select("Maven");
        
        testFrameworkInput.getItems().addAll(
            "JUnit"
              
        );
        testFrameworkInput.getSelectionModel().select("JUnit");
        
        techDocInput.getItems().addAll(
            "Default"
              
        );
        techDocInput.getSelectionModel().select("Default");
        
        apiDocInput.getItems().addAll(
            "Default"
              
        );
        apiDocInput.getSelectionModel().select("Default");
        
        testDocInput.getItems().addAll(
            "Default"
              
        );
        testDocInput.getSelectionModel().select("Default");
    }    

    private void loadDiagramTool(ActionEvent event, String path) throws IOException {
        
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setScene(
                new Scene(
                (Pane) loader.load()
                )
            );
            //scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("RIP Diagram Tool");
            //stage.setScene(scene);
            
            //Send projectHandler instance to DiagramToolController
            
            DiagramToolController controller = loader.<DiagramToolController>getController();
            controller.getProjectHandler(projectHandler);
            
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
    }
    
}
