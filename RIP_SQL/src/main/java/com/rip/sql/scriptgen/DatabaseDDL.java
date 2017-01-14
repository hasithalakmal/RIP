/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.sql.scriptgen;

/**
 *
 * @author Hasitha Lakmal
 */
public interface DatabaseDDL {

    String createDatabase(String dbName);

    String dropDatabase(String dbName);

    String useDatabase(String dbName);
    
}
