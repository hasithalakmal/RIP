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
 * Class for keeping ER related data
 */
public final class ERData {

    public static final List<String> DATA_TYPES = Collections.unmodifiableList(new ArrayList<String>(Arrays.asList(
            "INT",
            "VARCHAR",
            "DECIMAL",
            "DATE",
            "DATETIME",
            "CHAR",
            "TEXT",
            "FLOAT",
            "DOUBLE"
    )));
    
    public static final List<String> RELATIONSHIPS = Collections.unmodifiableList(new ArrayList<String>(Arrays.asList(
            "One to One",
            "One to Many",
            "Many to Many"
    )));

}
