/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Hasitha Lakmal
 */
public class ZipDirectory {

    public static void main(String[] args) throws IOException {
        File directoryToZip = new File("D:\\test");

        List<File> fileList = new ArrayList<>();
        System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
        getAllFiles(directoryToZip, fileList);
        System.out.println("---Creating zip file");
        writeZipFile(directoryToZip, fileList);
        System.out.println("---Done");
    }

    public static void getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    getAllFiles(file, fileList);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
        }
    }

    public static void writeZipFile(File directoryToZip, List<File> fileList) {

        try {
            try (FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip"); ZipOutputStream zos = new ZipOutputStream(fos)) {

                for (File file : fileList) {
                    if (!file.isDirectory()) { // we only zip files, not directories
                        addToZip(directoryToZip, file, zos);
                    }
                }

            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
            IOException {

        // we want the zipEntry's path to be a relative path that is relative
        // to the directory being zipped, so chop off the rest of the path
        try (FileInputStream fis = new FileInputStream(file)) {
            // we want the zipEntry's path to be a relative path that is relative
            // to the directory being zipped, so chop off the rest of the path
           // String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1, file.getCanonicalPath().length());
            String zipFilePath = "D:\\";
            System.out.println("Writing '" + zipFilePath + "' to zip file");
            ZipEntry zipEntry = new ZipEntry(zipFilePath);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }

            zos.closeEntry();
        }
    }
}
