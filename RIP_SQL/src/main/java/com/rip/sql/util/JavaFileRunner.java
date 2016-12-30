/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Hasitha Lakmal
 */
public class JavaFileRunner {
    
    String successResponce = "";
    String errorResponce = "";

    private static String printLines(InputStream ins) throws Exception {
        String line = null;
        String msg = "";
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
           // System.out.println(line);
           msg = msg + line+"\n";
        }
        return msg;
    }

    private static InputStream runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
//        printLines(command + " stdout:", pro.getInputStream());
//        printLines(command + " stderr:", pro.getErrorStream());
        InputStream responce = pro.getInputStream();
        InputStream responce1 = pro.getErrorStream();
        if(responce1 == null){
            responce = responce1;
        }
        pro.waitFor();

        //  System.out.println(command + " exitValue() " + pro.exitValue());
        return responce;
    }

    public static void main(String[] args) {
        try {
            runProcess("javac D:\\Accademic\\4thYearProject\\Dev\\ExternalResource\\MySQL.java");
            InputStream x = runProcess("java -cp D:\\Accademic\\4thYearProject\\Dev\\ExternalResource MySQL");
           String msg = printLines(x);
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

}
