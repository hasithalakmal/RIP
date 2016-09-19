/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Supun
 */
public class ApplicationController implements Initializable {

    
    public void initializeWizard(ActionEvent event) throws IOException{
            
            Stage stage = new Stage();
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Wizard.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("RIP Wizard");
            stage.setScene(scene);
            stage.show();
            
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
