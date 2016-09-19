/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.rip_ui.application.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Supun
 */
public class User {
    
    private ArrayList<ArrayList<String>> userInfo;
    
    //authenticate the user
    public boolean authenticateUser(String username, String password){
        if(username.equals("user") && password.equals("123")){
            //passing json obj to setInfo method : TO BE IMPLEMENTED 
            return true;
        }
        
        return false;
    }
    
    public void setInfo(){
        userInfo = new ArrayList<ArrayList<String>>();
        
        ArrayList<String> userProfile = new ArrayList<String>();
        userProfile.add("Custom User");
        userProfile.add("frequent user");
        
        ArrayList<String> userProjects = new ArrayList<String>();
        userProjects.add("Project 01");
        userProjects.add("Project 02");
        userProjects.add("Project 03");
        userProjects.add("Project 04");
        userProjects.add("Project 05");
        userProjects.add("Project 06");
        
        userInfo.add(userProfile);
        userInfo.add(userProjects);
    }

    //send user and project info to UserController
    public void getInfo() {
        
    }
    
}
