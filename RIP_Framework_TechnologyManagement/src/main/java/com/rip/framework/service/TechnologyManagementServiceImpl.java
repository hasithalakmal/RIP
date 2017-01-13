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
    public String addNewTechnology(String technology) {
        String msg = technologyManagementDao.addNewTechnology(technology);
        return msg;
    }

    @Override
    public String deleteExsistingTechnology(String techName, String version) {
        String msg = technologyManagementDao.deleteExsistingTechnology(techName, version);
        return msg;
    }

    @Override
    public ArrayList getExsistingTechnologies() {
        ArrayList msg = technologyManagementDao.getExsistingTechnologies();
        return msg;
    }

    @Override
    public ArrayList getExsistingTechnologies(String catogery) {
         ArrayList msg = technologyManagementDao.getExsistingTechnologies(catogery);
        return msg;
    }

    @Override
    public String getExsistingTechnology(String techName, String version) {
         String msg = technologyManagementDao.getExsistingTechnology(techName, version);
        return msg;
    }

      
   
	
}
