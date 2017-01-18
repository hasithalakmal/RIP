/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hasitha Lakmal
 */
@Service("MavenProjectRunner")
public class MavenProjectRunner {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(WriteToJSONFile.class);
    public static String buildProcess(File dir, String argument) throws IOException {
        String script = "";
        String line;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", argument);
        Map<String, String> env = builder.environment();
        // set environment variable u
        env.put("M2_HOME", "D:\\Software Setups\\Software Engineering\\Configerations\\apache-maven-3.3.9-bin\\apache-maven-3.3.9");
        if (dir != null) {
            builder.directory(dir);
        }
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (true) {
            line = reader.readLine();
            script = script + line+"\n";
            if (line == null) {
                break;
            } else if (line.startsWith("Exception")) {
                throw new IOException(line);
            }
        }

        return script;

    }

    public String runMavenProject(String className) {
        String script = "";
        try {
            File fi = new File("D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen");
            String command2 = "mvn exec:java -Dexec.mainClass=\"com.rip.sqlscriptgen." + className + "\"";
            script = buildProcess(fi, command2);

        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return script;
    }
    
    public String buildMavenProject(String className) {
        String script = "";
        try {
            File fi = new File("D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen");
            String command1 = "mvn clean install";
            buildProcess(fi, command1);
        } catch (IOException ex) {
           LOGGER.error(ex.getMessage(), ex);
        }
        return script;
    }

}
