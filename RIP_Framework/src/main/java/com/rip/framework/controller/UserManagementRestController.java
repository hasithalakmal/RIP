/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.configuration.MongoDBConfigaration;
import com.rip.framework.model.Employee;
import com.rip.framework.service.EmployeeService;
import com.rip.framework.service.UserManagementService;
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
public class UserManagementRestController {

  
    @Autowired
    UserManagementService userManagementService;
    
    @RequestMapping(value = "addUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody String a) {
        System.out.println(a);
        String msg = userManagementService.createUser(a);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
   


   

}
