/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sqlscriptgen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Hasitha Lakmal
 */
public class JsonFileReader {

    private static final String FILENAME = "D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\pim.json";

    private String pim = "";

    //create an object of SingleObject
   private static JsonFileReader jsonFileReader = new JsonFileReader();
   
   //Get the only object available
   public static JsonFileReader getInstance(){
      return jsonFileReader;
   }

    
    public String JsonFileReader() {

        

        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                pim = pim + sCurrentLine + "/n";
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

        return pim;

    }

    public JSONObject getplatformIndependentModel() {
        this.JsonFileReader();
        JSONObject platformindependetmodel = new JSONObject(pim);
        return platformindependetmodel;
    }

   

}
