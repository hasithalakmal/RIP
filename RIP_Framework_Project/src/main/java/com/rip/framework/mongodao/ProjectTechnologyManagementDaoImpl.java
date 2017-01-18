/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.mongodao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.rip.framework.configuration.MongoDBConfigaration;
import org.json.JSONObject;
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
         DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("ProjectTechnologies");
        /**
         * ** Insert ***
         */
        // create a document to store key and value
        DBObject document = (DBObject) JSON.parse(proTechnologySpeck);
        WriteResult state = table.insert(document);
        return state.toString();
    }

    @Override
    public String updateProjectTechnologySpeck(String proTechnologySpeck) {
         DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("ProjectTechnologies");

        JSONObject jason = new JSONObject(proTechnologySpeck);
        String app_name = jason.get("project_name").toString();
        String version = jason.get("version").toString();
        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("project_name", app_name);
        query.put("version", version);

        BasicDBObject updateObj = (BasicDBObject) JSON.parse(proTechnologySpeck);

        WriteResult state = table.update(query, updateObj);

        return state.toString();
    }

    @Override
    public String deleteProjectTechnologySpeck(String appName, String version) {
         DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("ProjectTechnologies");
        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("project_name", appName);
        query.put("version", version);

        WriteResult state = table.remove(query);

        return state.toString();
    }

    @Override
    public String getProjectTechnologySpeck(String appName, String version) {
          DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("ProjectTechnologies");

        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("project_name", appName);
        query.put("version", version);

        DBCursor cursor = table.find(query);

        if (cursor.hasNext()) {
            return cursor.next().toString();
        } else {
            return "{\n"
                    + "  \"user_name\": null\n"
                    + "}";
        }
    }

    

   

}
