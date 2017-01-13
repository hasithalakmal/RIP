package com.rip.framework.service;

import com.rip.framework.mongodao.ProjectManagementDao;
import com.rip.framework.mongodao.UserManagementDao;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectManagementService")
@Transactional
public class ProjectManagementServiceImpl implements ProjectManagementService {

    @Autowired
    ProjectManagementDao projectManagementDao;

    @Override
    public String createProject(String project) {
        String msg = projectManagementDao.createProject(project);
        return msg;
    }

    @Override
    public String deleteProject(String projectName, String version) {
        String msg = projectManagementDao.deleteProject(projectName, version);
        return msg;
    }

    @Override
    public String selectProject(String projectName, String version) {
        String msg = projectManagementDao.selectProject(projectName, version);
        return msg;
    }

    @Override
    public ArrayList selectProjectByName(String projectName) {
        ArrayList msg = projectManagementDao.selectProjectByName(projectName);
        return msg;
    }

    @Override
    public ArrayList selectProjectByUser(String userName) {
        ArrayList msg = projectManagementDao.selectProjectByUser(userName);
        return msg;
    }

    @Override
    public ArrayList selectAllProjects() {
        ArrayList msg = projectManagementDao.selectAllProjects();
        return msg;
    }

}
