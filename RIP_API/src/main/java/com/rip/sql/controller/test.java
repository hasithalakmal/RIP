/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.controller;

import com.rip.sql.model.Employee;
import com.rip.sql.service.EmployeeService;

import java.io.*;
import java.util.List;

import org.hibernate.annotations.SourceType;
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
public class test {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Employee>> test() {
        List<Employee> x = employeeService.findAllEmployees();
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    @RequestMapping(value = "rest-api", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> generateRESTAPI(@RequestBody String ripJSON) {

        JSONObject json = new JSONObject(ripJSON);
        JSONObject appDetails = json.getJSONObject("Request_details");
        JSONObject technicalSpecification = json.getJSONObject("Technical_Spec");
        JSONObject restAPISpecification = json.getJSONObject("RestAPI_Design");
        System.out.println(restAPISpecification);
        String basePath = "";
        try {
/*            File theDir = new File("E:\\UCSC_Work\\Testing\\"+appDetails.getInt("app_id"));
            if (!theDir.exists()) {
                theDir.mkdir();
            }
            FileWriter filewriter=new FileWriter("E:\\UCSC_Work\\Testing\\"+appDetails.getInt("app_id")+"\\"+appDetails.getInt("app_id")+".json");
            BufferedWriter bufferwriter= new BufferedWriter(filewriter);
            PrintWriter out = new PrintWriter(bufferwriter);
            out.print(restAPISpecification);
            out.close();

            System.out.println("Starting to create REST API");
            Runtime.getRuntime().exec("java -jar E:\\UCSC_Work\\Git\\RIP_API\\Swagger_REST_API\\modules\\swagger-codegen-cli\\target\\swagger-codegen-cli.jar generate -i E:\\UCSC_Work\\Testing\\example.json -l jaxrs-resteasy -o E:\\UCSC_Work\\Testing\\Java");
            System.out.println("Finished creating REST API");*/

            System.out.println("Starting to create REST API");
            Runtime.getRuntime().exec("java -jar E:\\UCSC_Work\\Git\\RIP_API\\Swagger_REST_API\\modules\\swagger-codegen-cli\\target\\swagger-codegen-cli.jar generate -i E:\\UCSC_Work\\Testing\\example.json -l jaxrs-resteasy -o E:\\UCSC_Work\\Testing\\Java");
            System.out.println("Finished creating REST API");
        }catch (Exception e) {
            e.printStackTrace();
        }

        String msg = "{" + "    \"response\": \"Success\"" + "  }";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
