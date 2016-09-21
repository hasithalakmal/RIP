/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.controller;

import com.rip.sql.model.Employee;
import com.rip.sql.service.EmployeeService;
import java.util.List;
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

    @RequestMapping(value = "rest-client-app", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> genarate(@RequestBody String RIP_JSON) {
        String msg = "RIP UI_Gen is Currently Not Implemented.\n You will have it as soon as possible.\n";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }
   

}
