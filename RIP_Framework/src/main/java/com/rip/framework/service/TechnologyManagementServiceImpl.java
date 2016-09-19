package com.rip.framework.service;

import com.rip.framework.mongodao.TechnologyManagementDao;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("technologyManagementService")
@Transactional
public class TechnologyManagementServiceImpl implements TechnologyManagementService {

    @Autowired
    TechnologyManagementDao technologyManagementDao;

    @Override
    public String addProjectTechnologySpeck(String proTechnologySpeck, String appName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateProjectTechnologySpeck(String proTechnologySpeck, String appName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleetProjectTechnologySpeck(String proTechnologySpeck, String appName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProjectTechnologySpeck(String proTechnologySpeck, String appName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String introduceNewTechnology(String technology) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String removeExsistingTechnology(String userName, String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getExsistingTechnologies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getExsistingTechnologies(String catogery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getExsistingTechnology(String technologyName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
   
	
}
