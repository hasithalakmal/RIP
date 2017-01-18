/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.api.impl;

import com.google.common.base.CaseFormat;

/**
 *
 * Converts types and coding conventions
 */
public class Converter {
    
    public static String snakeToCamel(String key){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
    }
    
    public static String camelToSnake(String key){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
    }
    
    
}
