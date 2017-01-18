/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.persistence;

import com.google.gson.Gson;
import com.rip.rip_ui.model.impl.DPath;
import com.rip.rip_ui.model.impl.DRestApiDesign;
import com.rip.rip_ui.model.impl.WParamField;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Supun
 */
public class FileHandler {
    
    DRestApiDesign restApiDesign;
    
    public void writeToJSON(DRestApiDesign restApiDesign, String id) {
        
        this.restApiDesign = restApiDesign;

//        for (Map.Entry<String, WParamField> entry : project.entrySet()) {
//            String key = entry.getKey();
//            WParamField value = entry.getValue();
//            
//            projectD.put(key, value.getPath());
//        }

        Gson gson = new Gson();
        //String json = gson.toJson(projectD);
        //System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter(id + ".json")) {

            gson.toJson(this.restApiDesign, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
