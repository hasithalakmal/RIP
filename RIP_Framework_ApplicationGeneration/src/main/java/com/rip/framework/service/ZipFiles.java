/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.service;

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
public class ZipFiles {

    List<String> filesListInDir = new ArrayList<String>();

    public String genarateZipFile(String sourceLocation, String outputLocation) {
        String msg = "success";
        File dir = new File(sourceLocation);
        ZipFiles zipFiles = new ZipFiles();
        zipFiles.zipDirectory(dir, outputLocation);
        return msg;
    }

//    public static void main(String[] args) {
////        File file = new File("/Users/pankaj/sitemap.xml");
////        String zipFileName = "/Users/pankaj/sitemap.zip";
//
//        File dir = new File("D:\\Accademic\\4thYearProject\\Dev\\FrameworkServerArea\\125\\1.0.0");
//        String zipDirName = "D:\\Accademic\\4thYearProject\\Dev\\FrameworkServerArea\\Output.zip";
//
//        //  zipSingleFile(file, zipFileName);
//        ZipFiles zipFiles = new ZipFiles();
//        zipFiles.zipDirectory(dir, zipDirName);
//    }

    /**
     * This method zips the directory
     *
     * @param dir
     * @param zipDirName
     */
    private void zipDirectory(File dir, String zipDirName) {
        try {
            populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String filePath : filesListInDir) {
                System.out.println("Zipping " + filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method populates all the files in a directory to a List
     *
     * @param dir
     * @throws IOException
     */
    private void populateFilesList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                filesListInDir.add(file.getAbsolutePath());
            } else {
                populateFilesList(file);
            }
        }
    }

    /**
     * This method compresses the single file to zip format
     *
     * @param file
     * @param zipFileName
     */
    private static void zipSingleFile(File file, String zipFileName) {
        try {
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            //add a new Zip Entry to the ZipOutputStream
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            //read the file and write to ZipOutputStream
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            //Close the zip entry to write to zip file
            zos.closeEntry();
            //Close resources
            zos.close();
            fis.close();
            fos.close();
            System.out.println(file.getCanonicalPath() + " is zipped to " + zipFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
