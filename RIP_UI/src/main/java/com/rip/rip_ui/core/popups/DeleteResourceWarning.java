/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.popups;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 *
 * Pop ups to warn when deleting a resource
 */
public class DeleteResourceWarning {
    
    private boolean isOk = false;
    
    private final String DELETE_RESOURCE = "Delete Resource";
    
    private final String DELETE_WARNING = "Deleting this Resource may loose all"
                                           + "paths \nand parameter information "
                                           + "related to it";
    
    private final String DELETE_CONFIMATION = "Are you sure you want to delete "
                                               + "\n";
    
    private final String YES_BTN = "Yes";
    
    private final String NO_BTN = "No";
    
    private final String CANCEL = "Cancel";
    

    public DeleteResourceWarning(String resource) {
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(DELETE_RESOURCE);
        alert.setHeaderText(DELETE_WARNING);
        alert.setContentText(DELETE_CONFIMATION+resource+" ?");

        ButtonType yesButton = new ButtonType(YES_BTN);
        ButtonType noButton = new ButtonType(NO_BTN);
        ButtonType cancelButton = new ButtonType(CANCEL, ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            isOk = true;
        } 
    }
    
    public boolean getUserResponse(){
        return isOk;
    }
    
}
