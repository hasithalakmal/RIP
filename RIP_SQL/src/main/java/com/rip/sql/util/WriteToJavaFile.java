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
import org.springframework.stereotype.Component;

@Component
public class WriteToJavaFile {

	

	public int createJavaFile(String fileName, String javaCode) {
             int state = 0;
             final String FILENAME = "D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen\\src\\main\\java\\com\\rip\\sqlscriptgen\\"+fileName+".java";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = javaCode;

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
                        state = 1;
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
                
               return state;
                       

	}

}