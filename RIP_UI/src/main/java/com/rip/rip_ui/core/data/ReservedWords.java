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
import java.util.Map;


/**
 *
 * Class for storing reserved key words
 */
public class ReservedWords {

    public static final List<String> KEYS = Collections.unmodifiableList(new ArrayList<String>(Arrays.asList(
            "currentPage",
            "numberOfRows",
            "sortOrder",
            "sortColumn",
            "limit"
    )));
    
    public static final Map<String, String> KEYS_WITH_VALUES;
    static
    {
        KEYS_WITH_VALUES = new HashMap<String, String>();
        KEYS_WITH_VALUES.put("currentPage", "integer");
        KEYS_WITH_VALUES.put("numberOfRows", "integer");
        KEYS_WITH_VALUES.put("sortOrder", "string");
        KEYS_WITH_VALUES.put("sortColumn", "string");
        KEYS_WITH_VALUES.put("limit", "integer");
    }

}
