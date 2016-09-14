package com.rip.framework.service;



public interface UserManagementService {

	String createUser(String user);
	
        String updateUser(String userName,String user);
        
        String deleteUser(String userName);
        
        String selectUser(String userName);
        
        String selectAllUsers();
}
