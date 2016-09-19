/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.service.ProjectManagementService;
import com.rip.framework.service.TechnologyManagementService;
import com.rip.framework.service.UserManagementService;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
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
public class TechnologyManagementRestController {

    @Autowired
    TechnologyManagementService technologyManagementService;

  /*  @RequestMapping(value = "technology/project", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addProjectTechnologySpeck(@RequestBody String project) {
        String msg = technologyManagementService.addProjectTechnologySpeck(project);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }*/

  
}
