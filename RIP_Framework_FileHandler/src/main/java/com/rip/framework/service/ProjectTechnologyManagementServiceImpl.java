package com.rip.framework.service;

import com.rip.framework.mongodao.ProjectManagementDao;
import com.rip.framework.mongodao.ProjectTechnologyManagementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("projectTechnologyManagementService")
@Transactional
public class ProjectTechnologyManagementServiceImpl implements ProjectTechnologyManagementService {

    @Autowired
    ProjectTechnologyManagementDao projectTechnologyManagementDao;
    
    @Override
    public String addProjectTechnologySpeck(String proTechnologySpeck) {
        String msg = projectTechnologyManagementDao.addProjectTechnologySpeck(proTechnologySpeck);
        return msg;
    }

    @Override
    public String updateProjectTechnologySpeck(String proTechnologySpeck) {
        String msg = projectTechnologyManagementDao.updateProjectTechnologySpeck(proTechnologySpeck);
        return msg;
    }

    @Override
    public String deleteProjectTechnologySpeck(String appName, String version) {
        String msg = projectTechnologyManagementDao.deleteProjectTechnologySpeck(appName, version);
        return msg;
    }

    @Override
    public String getProjectTechnologySpeck(String appName, String version) {
        String msg = projectTechnologyManagementDao.getProjectTechnologySpeck(appName, version);
        return msg;
    }

   
}
