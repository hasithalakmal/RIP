/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.api.controller;

import com.rip.api.common.HibernateGenerator;
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

    @RequestMapping(value = "test", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> generateRESTAPI(@RequestBody String string) {

        String msg = "{" + "    \"response\": \""+ HibernateGenerator.getCompliantEntityName(string)+"\"" + "  }";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
