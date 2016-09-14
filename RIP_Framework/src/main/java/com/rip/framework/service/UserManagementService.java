package com.rip.framework.service;

import java.util.ArrayList;



public interface UserManagementService {

	String createUser(String user);
	
        String updateUser(String userName,String user);
        
        String deleteUser(String userName);
        
        String selectUser(String userName);
        
        ArrayList selectAllUsers();
}
