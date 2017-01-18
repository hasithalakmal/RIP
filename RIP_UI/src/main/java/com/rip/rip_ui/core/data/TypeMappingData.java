/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Keeps hash map for MySQL to Java data type conversion 
 */
public class TypeMappingData {
    
    public static final List<String> JSON_DATA_TYPE = Collections.unmodifiableList(new ArrayList<String>(Arrays.asList(
            "n/a",
            "array",
            "object"
    )));
}
