package com.rip.framework.service;

import com.rip.framework.dao.EmployeeDao;
import com.rip.framework.model.Employee;
import com.rip.framework.mongodao.UserManagementDao;
import java.util.List;

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

	
}
