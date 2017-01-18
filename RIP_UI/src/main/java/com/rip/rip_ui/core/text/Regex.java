/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.core.text;

import java.util.regex.Pattern;

/**
 *
 * @author Supun
 */
public class Regex {
    
    public static final String SNAKE_CASE_REGEX = "^[a-z]+(_[a-z]+)*$";
    public static final String ONLY_NUMBERS_DOTS = "^[0-9]+(.[0-9]+)*$";
    public static final String WEBSITE_REGEX = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
    
}
