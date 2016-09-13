/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.configuration.MongoDBConfigaration;
import com.rip.framework.model.Employee;
import com.rip.framework.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

    @Autowired
    MongoDBConfigaration mongoDBConfigaration;
    
    @RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Employee>> test() {
        List<Employee> x = employeeService.findAllEmployees();
        return new ResponseEntity<>(x, HttpStatus.OK);
    }
    
    @RequestMapping(value = "testmongo", method = RequestMethod.GET, produces = "application/json")
    public void test_mongo() {
        System.out.println("working");
        mongoDBConfigaration.test();
         System.out.println("@@@@@@@@@@@@");
    }


   

}
