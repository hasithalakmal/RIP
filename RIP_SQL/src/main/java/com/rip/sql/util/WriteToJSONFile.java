/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

/**
 *
 * @author Hasitha Lakmal
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class WriteToJSONFile {
    private static final Logger LOGGER = Logger.getLogger(WriteToJSONFile.class);

	public int createJsonFile(String fileName, String jsonObject) {
             int state = 0;
             final String FILENAME = "D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\"+fileName+".json";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = jsonObject;

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
                        state = 1;


		} catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
                            LOGGER.error(ex.getMessage(), ex);
			}

		}
                
               return state;
                       

	}

}