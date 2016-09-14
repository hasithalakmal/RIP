/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.mongodb.util.JSON;
import com.rip.framework.service.UserManagementService;
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
public class UserManagementRestController {

    @Autowired
    UserManagementService userManagementService;

    @RequestMapping(value = "user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody String user) {
        String msg = userManagementService.createUser(user);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUser(@RequestBody String user) {

        JSONObject jason = new JSONObject(user);
        System.out.println(jason);
        Object userName = jason.get("user_name");

        
        String msg = userManagementService.updateUser(userName.toString(), user);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
