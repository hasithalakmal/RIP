/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.diagram_tool.controllers;


import com.rip.rip_ui.application.wizard.models.ProjectHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Supun
 */
public class DiagramToolController implements Initializable {

    private ProjectHandler projectHandler;
    private String commandLine;
    private String responseString;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField commandInput;
    
    @FXML
    private Label responseLabel;
    
    @FXML
    private Label warningLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    //receive projectHandler instance from previous scene
    public void getProjectHandler(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }
    
    @FXML
    public void executeQuery(ActionEvent event){
        
        warningLabel.setText("");
        responseLabel.setText("");
        
        commandLine = commandInput.getText();
        commandLine.toLowerCase();
        String[] splitArray = commandLine.split("\\s+");
        
        responseString = projectHandler.analyzeCommand(splitArray);
        
        if(!responseString.equals("")){
            responseLabel.setText(responseString);
        }
        else{
            warningLabel.setText("Invalid Input");
        }
        
        commandInput.clear();
    }
    
    @FXML
    public void createProject(ActionEvent event){
        
        projectHandler.writeToProject();
        ((Node)event.getSource()).getScene().getWindow().hide();
        
    }

}
