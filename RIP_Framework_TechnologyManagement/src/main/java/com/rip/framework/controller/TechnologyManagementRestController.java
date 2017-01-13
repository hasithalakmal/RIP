/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.controller;

import com.rip.framework.service.TechnologyManagementService;
import java.util.ArrayList;
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

    @RequestMapping(value = "technology", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addNewTechnology(@RequestBody String technology) {
        String msg = technologyManagementService.addNewTechnology(technology);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "technology/{techName}/{version}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deleteExsistingTechnology(@PathVariable String techName,@PathVariable String version) {
        String msg = technologyManagementService.deleteExsistingTechnology(techName, version);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "technology", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList> getExsistingTechnologies() {
        ArrayList msg = technologyManagementService.getExsistingTechnologies();
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "technology-by-category/{catogery}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArrayList> getExsistingTechnologies(@PathVariable String catogery) {
        ArrayList msg = technologyManagementService.getExsistingTechnologies(catogery);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "technology/{techName}/{version}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> getExsistingTechnologies(@PathVariable String techName, @PathVariable String version) {
        String msg = technologyManagementService.getExsistingTechnology(techName, version);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

  
}
