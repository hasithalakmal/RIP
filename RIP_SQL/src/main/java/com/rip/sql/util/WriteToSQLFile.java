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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("WriteToSQLFile")
public class WriteToSQLFile {
    private static final Logger LOGGER = Logger.getLogger(WriteToSQLFile.class);

	public int createSQLFile(String fileName, String sql_code, String pimJSON) {
             ConfigDetails configdetails = ConfigDetailExtractor.getConfigDetails(pimJSON);
            String sqlpath = configdetails.getDbSQLPath();

            if (sqlpath != null) {
                File parent = new File(sqlpath);
                parent.mkdirs();
            }
            
             int state = 0;
             final String FILENAME = sqlpath+"\\"+fileName+".sql";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String content = sql_code;
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