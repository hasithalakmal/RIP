/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.diagram_tool.controllers;

import com.rip.rip_ui.application.wizard.models.ProjectHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Supun
 */
public class DiagramToolController implements Initializable {

    private ProjectHandler projectHandler;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //receive projectHandler instance from previous scene
    public void getProjectHandler(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }
    
}
