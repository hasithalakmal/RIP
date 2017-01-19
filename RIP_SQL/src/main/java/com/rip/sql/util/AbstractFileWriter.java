package com.rip.sql.util;

import org.apache.log4j.Logger;

import java.io.*;
import org.springframework.stereotype.Component;

/**
 * Created by Kasun Eranga on 9/30/2016.
 */
@Component
public class AbstractFileWriter {

    private static final Logger LOGGER = Logger.getLogger(AbstractFileWriter.class);

    public static void writeToFile(String filename, String contents) throws IOException {

        LOGGER.info("writing file " + filename);
        File output = new File(filename);

        if (output.getParent() != null && !new File(output.getParent()).exists()) {
            File parent = new File(output.getParent());
            parent.mkdirs();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
        out.write(contents);
        out.close();
    }
}
