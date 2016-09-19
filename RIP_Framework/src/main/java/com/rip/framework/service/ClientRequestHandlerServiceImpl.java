package com.rip.framework.service;

import com.rip.framework.mongodao.ProjectManagementDao;
import com.rip.framework.mongodao.UserManagementDao;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientRequestHandlerService")
@Transactional
public class ClientRequestHandlerServiceImpl implements ClientRequestHandlerService {

    @Autowired
    ProjectManagementDao projectManagementDao;

    @Override
    public String createClientRequest(String design) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteClientRequest(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateClientRequest(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String selectClientRequest(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String genarateClientResponse(String RIP_JSON) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
