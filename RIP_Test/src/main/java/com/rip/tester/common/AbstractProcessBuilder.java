package com.rip.tester.common;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by Kasun Eranga on 9/30/2016.
 */
public class AbstractProcessBuilder {

    private static final Logger LOGGER = Logger.getLogger(AbstractProcessBuilder.class);

    public static void buildProcess(File dir,String argument) throws IOException{
        String line;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", argument);
        if(dir != null) {
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

    public static void buildSQLProcess(File dir,String dbName) throws IOException{
        String argument = "mysql --host=localhost --user=root --verbose";
        String line;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c", argument);
        builder.directory(dir);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(process.getOutputStream())), true);
        writer.println("DROP DATABASE IF EXISTS "+dbName.toLowerCase()+"; create database "+dbName.toLowerCase()+"; source "+dbName+".sql;");
        writer.flush();
        writer.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (true) {
            line = reader.readLine();
            if (line == null) { break; }
            LOGGER.info(line);
        }

    }
}
