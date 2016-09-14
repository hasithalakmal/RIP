/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.mongodao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.rip.framework.configuration.MongoDBConfigaration;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasitha Lakmal
 */
@Repository("projectManagementDao")
public class ProjectManagementDaoImpl implements ProjectManagementDao {

    @Autowired
    MongoDBConfigaration mongoDBConfigaration;

    @Override
    public String createProject(String project) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Projects");
        /**
         * ** Insert ***
         */
        // create a document to store key and value
        DBObject document = (DBObject) JSON.parse(project);
        WriteResult state = table.insert(document);
        return state.toString();
    }

    @Override
    public String deleteProject(String projectName, String version) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Projects");

        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("project_name", projectName);
        query.put("version", version);
        
        System.out.println("???? "+projectName+ version);

        WriteResult state = table.remove(query);

        return state.toString();
    }

    @Override
    public String selectProject(String projectName, String version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String selectProjectByName(String projectName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList selectProjectByUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList selectAllProjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
