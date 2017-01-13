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
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasitha Lakmal
 */
@Repository("technologyManagementDao")
public class TechnologyManagementDaoImpl implements TechnologyManagementDao {

    @Autowired
    MongoDBConfigaration mongoDBConfigaration;

    @Override
    public String addNewTechnology(String technology) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Technologies");
        /**
         * ** Insert ***
         */
        // create a document to store key and value
        DBObject document = (DBObject) JSON.parse(technology);
        WriteResult state = table.insert(document);
        return state.toString();
    }

    @Override
    public String deleteExsistingTechnology(String techname, String version) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Technologies");
        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("tech_name", techname);
        query.put("version", version);

        WriteResult state = table.remove(query);

        return state.toString();
    }

    @Override
    public ArrayList getExsistingTechnologies() {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Technologies");

        DBCursor cursor = table.find();

        ArrayList technologies = new ArrayList();

        int i = 1;
        while (cursor.hasNext()) {
            DBObject technology = cursor.next();
            technologies.add(technology);

        }

        return technologies;
    }

    @Override
    public ArrayList getExsistingTechnologies(String catogery) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Technologies");

        BasicDBObject query = new BasicDBObject();
        query.put("tech_cat", catogery);

        DBCursor cursor = table.find(query);

        ArrayList projects = new ArrayList();

        int i = 1;
        while (cursor.hasNext()) {
            DBObject project = cursor.next();
            projects.add(project);

        }

        return projects;
    }

    @Override
    public String getExsistingTechnology(String techName, String version) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Technologies");

        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("tech_name", techName);
        query.put("version", version);

        DBCursor cursor = table.find(query);

        if (cursor.hasNext()) {
            return cursor.next().toString();
        } else {
            return "{\n"
                    + "  \"tech_name\": null\n"
                    + "}";
        }
    }

}
