package com.rip.tester.controller;


import com.rip.tester.common.AbstractProcessBuilder;
import com.rip.tester.common.ConfigDetailExtractor;
import com.rip.tester.dto.ConfigDetails;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Created by Kasun Eranga on 10/3/2016.
 */
@RestController
@Component
public class Tester {

    private static final Logger LOGGER = Logger.getLogger(Tester.class);
    String msg = null;

    @RequestMapping(value = "rest-api-test", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> generateRESTAPI(@RequestBody String ripJsonString) {

        try {
            ConfigDetails configDetails = ConfigDetailExtractor.getConfigDetails(ripJsonString);
            AbstractProcessBuilder.buildProcess(new File(configDetails.getPostmanGeneratorPath()), "java -jar " + "swagger2postman.jar " + configDetails.getInputJsonPath() + " " + configDetails.getPostmanCollectionPath() +" "+configDetails.getProjectName()+ " Content-Type=application/json"); // generate Postman Test collection
            //AbstractProcessBuilder.buildSQLProcess(new File(configDetails.getDbSQLPath()),configDetails.getDbName()); // create database
            //AbstractProcessBuilder.buildProcess(new File(configDetails.getOutputPath()), "mvn clean package tomcat7:deploy"); // Deploy in tomcat
            AbstractProcessBuilder.buildProcess(new File(configDetails.getPostmanCollectionPath()), "newman run postman.json -r html --reporter-html-export results.html"); // Execute Postman Test collection
            //AbstractProcessBuilder.buildProcess(new File(configDetails.getOutputPath()), "mvn tomcat7:undeploy clean");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            msg = "Error";
            return new ResponseEntity<>(msg, HttpStatus.EXPECTATION_FAILED);
        }

        msg = "success";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
