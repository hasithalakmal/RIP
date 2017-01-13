package com.rip.framework.mongodao;

import java.util.ArrayList;



public interface UserManagementDao {

	String createUser(String user);
        
        String updateUser(String userName,String user);
        
        String deleteUser(String userName);
        
        String selectUser(String userName);
        
        ArrayList selectAllUsers();

}
