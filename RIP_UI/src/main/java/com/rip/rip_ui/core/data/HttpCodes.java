/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * Class for keeping http code data
 */
public class HttpCodes {
    
    
    public static final List<String> HTTP_CODES = Collections.unmodifiableList(new ArrayList<String>(Arrays.asList(
            "200",
            "201",
            "202",
            "203",
            "204",
            "205",
            "206",
            "207",
            "208",
            "226"

    )));
    
}
