/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.ddl;

import org.json.JSONObject;

/**
 *
 * @author Hasitha Lakmal
 */
public interface DDL_Genarator {

    String createDDLScript(JSONObject platformIndependentModel, JSONObject domainSpecificModel);

    String createTable(JSONObject platformIndependentModel, JSONObject domainSpecificModel);
    
    String addForignKeys(JSONObject platformIndependentModel, JSONObject domainSpecificModel);
}
