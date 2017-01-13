/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.controller;

import com.rip.sql.service.DDLManagementService;
import com.rip.sql.service.DatabaseDocumentationService;
import com.rip.sql.service.DatabaseIntroduceManagementService;
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
public class DDLRestController {

    @Autowired
    DDLManagementService DDLManagementService;
    
    @Autowired
    DatabaseIntroduceManagementService DatabaseIntroduceManagementService;
    
    @Autowired
    DatabaseDocumentationService databaseDocumentationService;

    @RequestMapping(value = "sql-file", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> genarate(@RequestBody String RIP_JSON) {
        String msg = DDLManagementService.genarate(RIP_JSON);
        databaseDocumentationService.createDatabaseDoc(RIP_JSON);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "introduce-database", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> databaseIntroducer(@RequestBody String newdatabase) {
        String msg = DatabaseIntroduceManagementService.introduceDatabse(newdatabase);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }

}
