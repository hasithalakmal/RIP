/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.popups;

import com.rip.rip_ui.core.text.Confirmation;
import com.rip.rip_ui.core.text.Title;
import com.rip.rip_ui.core.text.Warning;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Supun
 */
public class DeleteTableWarning {
    
    private boolean isOk = false;

    public DeleteTableWarning(String tableId) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(Title.DELETE_TABLE);
        alert.setHeaderText(Warning.TABLE_DELETE_WARNING);
        alert.setContentText(Confirmation.TABLE_DELETE_CONFIRMATION);

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

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
