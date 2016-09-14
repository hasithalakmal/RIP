package com.rip.framework.service;

import com.rip.framework.mongodao.UserManagementDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userManagementService")
@Transactional
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserManagementDao userManagementDao;
            
    @Override
    public String createUser(String user) {
        String state = userManagementDao.createUser(user);
        return state;
    }

    @Override
    public String updateUser(String userName, String user) {
        String state = userManagementDao.updateUser(userName,user);
        return state;
    }

    @Override
    public String deleteUser(String userName) {
        String state = userManagementDao.deleteUser(userName);
        return state;
    }

    @Override
    public String selectUser(String userName) {
        String state = userManagementDao.selectUser(userName);
        return state;
    }

    @Override
    public String selectAllUsers() {
        String state = userManagementDao.selectAllUsers();
        return state;
    }

	
}
