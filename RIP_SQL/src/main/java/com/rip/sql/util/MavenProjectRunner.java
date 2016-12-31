/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
public class MavenProjectRunner {

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
        //  builder.command("D:\\Software Setups\\Software Engineering\\Configerations\\apache-maven-3.3.9-bin\\apache-maven-3.3.9\\bin");
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
           // String command1 = "mvn clean install";
            String command2 = "mvn exec:java -Dexec.mainClass=\"com.rip.sqlscriptgen." + className + "\"";
           // buildProcess(fi, command1);
            script = buildProcess(fi, command2);

        } catch (IOException ex) {
            Logger.getLogger(JavaMainFileRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return script;
    }
    
    public String buildMavenProject(String className) {
        String script = "";
        try {
            File fi = new File("D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen");
            String command1 = "mvn clean install";
           // String command2 = "mvn exec:java -Dexec.mainClass=\"com.rip.sqlscriptgen." + className + "\"";
            buildProcess(fi, command1);
            //script = buildProcess(fi, command2);

        } catch (IOException ex) {
            Logger.getLogger(JavaMainFileRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return script;
    }

}
