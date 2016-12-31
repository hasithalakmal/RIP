package com.rip.api.common;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kasun Eranga on 9/30/2016.
 */
public class AbstractProcessBuilder {

    private static final Logger LOGGER = Logger.getLogger(AbstractProcessBuilder.class);

    public static void buildProcess(File dir, String argument) throws IOException {
        String line;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", argument);
        if (dir != null) {
            builder.directory(dir);
        }
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }else if(line.startsWith("Exception")){
                throw new IOException(line);
            }
            LOGGER.info(line);
        }

    }
}
