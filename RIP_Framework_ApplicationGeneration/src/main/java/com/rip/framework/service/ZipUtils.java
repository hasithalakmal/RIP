package com.rip.framework.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hasitha Lakmal
 */
@Component
public class ZipUtils {

   public static void main( String[] args )
    {
    	byte[] buffer = new byte[1024];

    	try{

    		FileOutputStream fos = new FileOutputStream("D:\\Accademic\\4thYearProject\\Dev\\FrameworkServerArea\\MyFile.zip");
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry("spy.log");
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream("C:\\spy.log");

    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}

    		in.close();
    		zos.closeEntry();

    		//remember close it
    		zos.close();

    		System.out.println("Done");

    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
    }

}
