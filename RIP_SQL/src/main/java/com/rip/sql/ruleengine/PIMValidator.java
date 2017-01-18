/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.ruleengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sasanka
 */
@Component
public class PIMValidator {
    @Autowired
    DBRuleValidator DBRuleValidator;
    
    @Autowired
    AutoIncrementCheck AutoIncrementCheck;
    
    @Autowired
    FKeyCheck FKeyCheck;
    
    @Autowired
    FieldNameCheck FieldNameCheck;
    
    @Autowired
    KeyWordCheck KeyWordCheck;
    
    @Autowired
    TableNameCheck TableNameCheck;
    
    public String validatJSON(String pimJson){
        String msg = "success";
        String responce ="";
        
        responce = DBRuleValidator.controler(pimJson);
        if(!responce.equals("success")){
            return responce;
        }
        
        responce = AutoIncrementCheck.controler(pimJson);
        if(!responce.equals("success")){
            return responce;
        }
        
        responce = FKeyCheck.controler(pimJson);
        if(!responce.equals("success")){
            return responce;
        }
        
        responce = FieldNameCheck.controler(pimJson);
        if(!responce.equals("success")){
            return responce;
        }
        
        responce = KeyWordCheck.controler(pimJson);
        if(!responce.equals("success")){
            return responce;
        }
        
        responce = TableNameCheck.controler(pimJson);
        if(!responce.equals("success")){
            return responce;
        }
        
        
        return msg;
    }

   
}
