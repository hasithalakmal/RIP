/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.popups;

import com.rip.rip_ui.core.text.Info;
import com.rip.rip_ui.core.text.InputLabel;
import com.rip.rip_ui.core.text.Title;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;


/**
 * Template for New Table Wizard
 *
 */
public class NewTableWizard {

    private String tableName;

    public NewTableWizard() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(Title.CREATE_NEW_TABLE);
        dialog.setHeaderText(Info.NEW_TABLE_CREATION_INFO);
        dialog.setContentText(InputLabel.TABLE_NAME);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> tableName = name);
    }

    public String getTableName() {
        return tableName;
    }

}
