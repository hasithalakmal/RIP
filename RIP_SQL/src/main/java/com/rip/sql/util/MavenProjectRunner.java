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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
public class JavaMainFileRunner {

    private String printLines(InputStream ins) throws Exception {
        String line = null;
        String msg = "";
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            msg = msg + line + "\n";
        }
        return msg;
    }

    private InputStream runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        InputStream responce = pro.getInputStream();
        pro.waitFor();
        return responce;
    }

    public static void buildProcess(File dir, String argument) throws IOException {
        System.out.println("");
        String line;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", argument);
//        Process process = new ProcessBuilder().command("D:\\Software Setups\\Software Engineering\\Configerations\\apache-maven-3.3.9-bin\\apache-maven-3.3.9\\bin","/c", "clean")
//                .directory(dir).redirectErrorStream(true).start();
        if (dir != null) {
            builder.directory(dir);
            System.out.println(dir);
        }
        builder.redirectErrorStream(true);
      //  builder.command("D:\\Software Setups\\Software Engineering\\Configerations\\apache-maven-3.3.9-bin\\apache-maven-3.3.9\\bin");
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (true) {
            line = reader.readLine();
            System.out.println(line);
            if (line == null) {
                break;
            } else if (line.startsWith("Exception")) {
                throw new IOException(line);
            }
        }

    }
    
    public void TestCode(){
        try {
            File fi = new File("D:\\Accademic\\4thYearProject\\Dev\\ExternalResource");
            String command2 = "mvn exec:java -Dexec.mainClass=\"com.rip.sqlscriptgen.MySQLScriptGen\"";
            String command3 = "mvn clean build";
            buildProcess(fi, command3);

        } catch (IOException ex) {
            Logger.getLogger(JavaMainFileRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String runClass(String className) {
        String successResponce = "";
        //String command0 = "javac D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen\\src\\main\\java\\com\\rip\\sqlscriptgen\\JsonFileReader.java";
        //  String command1 = "javac D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen\\src\\main\\java\\com\\rip\\sqlscriptgen\\" + className.trim() + ".java";
        //  String command2 = "java -cp D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\SQLScriptGen\\target\\classes\\com\\rip\\sqlscriptgen " + className;
        String command2 = "mvn -cp D:\\\\Accademic\\\\4thYearProject\\\\Dev\\\\ExternalResource\\\\SQLScriptGen exec:java -Dexec.mainClass=\"com.rip.sqlscriptgen.MySQLScriptGen\" ";
       // String command2 = "mvn install";
        try {
            // this.runProcess(command0);
            // this.runProcess(command1);

            InputStream responceOfClass = this.runProcess(command2);
            successResponce = this.printLines(responceOfClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return successResponce;
    }

    public static void main(String[] args) {
//        try {
//            File fi = new File("D:\\Accademic\\4thYearProject\\Dev\\ExternalResource");
//            String command2 = "mvn exec:java -Dexec.mainClass=\"com.rip.sqlscriptgen.MySQLScriptGen\"";
//            String command3 = "mvn install";
//            buildProcess(fi, command3);
//
//        } catch (IOException ex) {
//            Logger.getLogger(JavaMainFileRunner.class.getName()).log(Level.SEVERE, null, ex);
//        }

        JavaMainFileRunner jfr = new JavaMainFileRunner();
        String script = jfr.runClass("MySQLScriptGen");
        System.out.println(script);


//        JavaMainFileRunner obj = new JavaMainFileRunner();
//
//        String domainName = "google.com";
//
//        //in mac oxs
//        String command = "ping -c 3 " + domainName;
//
//        //in windows
//        //String command = "ping -n 3 " + domainName;
//        String output = obj.executeCommand(command);
//
//        System.out.println(output);

    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}
