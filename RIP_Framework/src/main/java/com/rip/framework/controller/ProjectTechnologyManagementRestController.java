/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.service.ProjectTechnologyManagementService;
import com.rip.framework.service.TechnologyManagementService;
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
public class ProjectTechnologyManagementRestController {

    @Autowired
    ProjectTechnologyManagementService projectTechnologyManagementService;

    @RequestMapping(value = "project-technology", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addProjectTechnologySpeck(@RequestBody String speck) {
        String msg = projectTechnologyManagementService.addProjectTechnologySpeck(speck);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "project-technology", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateProjectTechnologySpeck(@RequestBody String speck) {
        String msg = projectTechnologyManagementService.updateProjectTechnologySpeck(speck);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "project-technology/{projectName}/{version}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deletProjectTechnologySpeck(@PathVariable String projectName, @PathVariable String version ) {
        String msg = projectTechnologyManagementService.deleteProjectTechnologySpeck(projectName, version);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "project-technology/{projectName}/{version}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> getProjectTechnologySpeck(@PathVariable String projectName, @PathVariable String version ) {
        String msg = projectTechnologyManagementService.getProjectTechnologySpeck(projectName, version);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
