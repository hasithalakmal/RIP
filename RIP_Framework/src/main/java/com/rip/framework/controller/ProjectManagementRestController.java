/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.service.ProjectManagementService;
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
public class ProjectManagementRestController {

    @Autowired
    ProjectManagementService projectManagementService;

    @RequestMapping(value = "project", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addProject(@RequestBody String project) {
        String msg = projectManagementService.createProject(project);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    
    @RequestMapping(value = "project/{projectName}/{version}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deleteProject(@PathVariable String projectName,  @PathVariable String version) {
        String msg = projectManagementService.deleteProject(projectName, version);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
   
    @RequestMapping(value = "project/{projectName}/{version}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> getProject(@PathVariable String projectName,@PathVariable String version) {

        String msg = projectManagementService.selectProject(projectName, version);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
     
    @RequestMapping(value = "project/{projectName}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList> getProjectByName(@PathVariable String projectName) {

        ArrayList msg = projectManagementService.selectProjectByName(projectName);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    /*
    @RequestMapping(value = "project/{userName}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList> getProjectByUser(@PathVariable String userName) {

        ArrayList msg = projectManagementService.selectProjectByUser(userName);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "project", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList> getAllProjects() {

        ArrayList users = projectManagementService.selectAllProjects();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    */
    

}
