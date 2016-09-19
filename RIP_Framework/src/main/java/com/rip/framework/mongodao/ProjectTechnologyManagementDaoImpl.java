/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.mongodao;

import com.rip.framework.configuration.MongoDBConfigaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasitha Lakmal
 */
@Repository("projectTechnologyManagementDao")
public class ProjectTechnologyManagementDaoImpl implements ProjectTechnologyManagementDao {

    @Autowired
    MongoDBConfigaration mongoDBConfigaration;

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
