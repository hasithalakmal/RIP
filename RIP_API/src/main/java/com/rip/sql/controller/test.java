/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.controller;

import com.rip.sql.model.Employee;
import com.rip.sql.service.EmployeeService;
import java.util.List;
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

    @RequestMapping(value = "swager", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUser(@RequestBody String ripJSON) {

        System.out.println(ripJSON);
        /* JSONObject jason = new JSONObject(ripJSON);
        Object userName = jason.get("user_name");*/

        String msg = "{\n"
                + "    \"response\": \"Success\""
                + "  }";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
