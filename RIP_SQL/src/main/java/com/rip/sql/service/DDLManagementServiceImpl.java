package com.rip.sql.service;


import com.rip.sql.ddl.TableDDL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("DDLManagementService")
@Transactional
public class DDLManagementServiceImpl implements DDLManagementService {

    @Autowired
    TableDDL tableDDL;
            
    @Override
    public String genarate(String ddlJSON) {
        String state = tableDDL.CreateDDLSQL(ddlJSON);
        return state;
    }

    

	
}
