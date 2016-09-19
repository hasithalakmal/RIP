package com.rip.framework.service;

import com.rip.framework.mongodao.TechnologyManagementDao;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("technologyManagementService")
@Transactional
public class ProjectTechnologyManagementServiceImpl implements ProjectTechnologyManagementService {

    @Override
    public String addProjectTechnologySpeck(String proTechnologySpeck) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateProjectTechnologySpeck(String proTechnologySpeck) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleetProjectTechnologySpeck(String appName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProjectTechnologySpeck(String appName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
