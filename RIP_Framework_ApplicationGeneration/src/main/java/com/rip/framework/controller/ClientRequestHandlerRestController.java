/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.service.ClientRequestHandlerService;
import com.rip.framework.service.ConfigDetailExtractor;
import com.rip.framework.service.ConfigDetails;
import com.rip.framework.service.ZipFiles;
import java.io.File;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hasitha Lakmal
 */
@RestController
@Component
public class ClientRequestHandlerRestController {

    @Autowired
    ClientRequestHandlerService clientRequestHandlerService;

    @Autowired
    ZipFiles ZipFiles;

    @RequestMapping(value = "rest-in-peace", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> generateProject(@RequestBody String RIP_JSON) {
        String msg = clientRequestHandlerService.genarateClientResponse(RIP_JSON);
        ConfigDetails configdetails = ConfigDetailExtractor.getConfigDetails(RIP_JSON);
        String folderRoot = configdetails.getOutputPath();
        System.out.println(folderRoot);
        //String folderRoot = "D:\\Accademic\\4thYearProject\\Dev\\FrameworkServerArea\\125";
        String ZipFile = "C:\\xampp\\htdocs"+"\\"+configdetails.getAppId()+"-"+configdetails.getProjectName()+"-"+configdetails.getVersion()+".zip";
        String downloadURL = "http://localhost/"+configdetails.getAppId()+"-"+configdetails.getProjectName()+"-"+configdetails.getVersion()+".zip";
        System.out.println(ZipFile);
        //String ZipFile = "D:\\Accademic\\4thYearProject\\Dev\\FrameworkServerArea\\aaaa.zip";
        if (folderRoot != null) {
            File parent = new File(folderRoot);
            parent.mkdirs();
        }
//        ZipUtils.setOUTPUT_ZIP_FILE(ZipFile);
//        ZipUtils.setSOURCE_FOLDER(folderRoot);
        ZipFiles.genarateZipFile(folderRoot, ZipFile);
        JSONObject outputJson = new JSONObject(msg);
        outputJson.accumulate("DownloadURL", downloadURL);
        return new ResponseEntity<>(outputJson.toString(), HttpStatus.OK);
    }

}
