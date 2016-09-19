/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.wizard.models;

import com.google.gson.Gson;
import com.rip.rip_ui.application.wizard.templates.ProjectTemplate;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Supun
 */
public class FileHandler {
    
    public void writeToJSON(ProjectTemplate project, String id){
        
        
        Gson gson = new Gson();
        String json = gson.toJson(project);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("D:\\"+id+".json")) {

            gson.toJson(project, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
