/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.validators;

import com.rip.rip_ui.model.WProjectContainer;
import com.rip.rip_ui.model.impl.WResourceNode;
import com.rip.rip_ui.model.impl.WTableField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Control;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;

/**
 *
 * Validator class for conditional statements and conditional checkings
 */
public class PredicateValidator {

    private WProjectContainer projectContainer;

    public PredicateValidator(WProjectContainer projectContainer) {
        this.projectContainer = projectContainer;
    }

    //Returns an array of tables having one or more than one field
    public ArrayList<String> getTablesHavingFields() {
        ArrayList<String> tableArray = new ArrayList();

        Map<String, HashMap<String, WTableField>> tableFieldsMap = projectContainer.getTableFieldsMap();

        for (Map.Entry<String, HashMap<String, WTableField>> entry : tableFieldsMap.entrySet()) {

            String key = entry.getKey();
            HashMap<String, WTableField> value = entry.getValue();

            if (value.size() > 0) {
                tableArray.add(key);
            }
        }

        return tableArray;
    }

    //Is project having tables
    public boolean isNoTables() {
        return projectContainer.getTableNodeMap().isEmpty();
    }

    //Checks resource exists or not
    public ValidationResult isResourceExists(Control control, String content) {
        boolean condition = false;
        
        if(projectContainer.getResourceNodeMap().get("Resource-"+content.toString())!=null)
            condition = true;

        return ValidationResult.fromMessageIf(control, "Resource exists", Severity.ERROR, condition);
    }
    
    //Checks text field empty
    public ValidationResult isTextFieldEmpty(Control control, String content){ 
        boolean condition = content.toString().isEmpty();
        return ValidationResult.fromMessageIf(control, "Empty Field", Severity.ERROR, condition);     
    }

}
