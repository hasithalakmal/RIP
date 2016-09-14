package com.rip.framework.mongodao;



public interface UserManagementDao {

	String createUser(String user);
        
        String updateUser(String userName,String user);
        
        String deleteUser(String userName);
        
        String selectUser(String userName);
        
        String selectAllUsers();

}
