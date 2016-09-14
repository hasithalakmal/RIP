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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasitha Lakmal
 */
@Repository("userManagementDao")
public class UserManagementDaoImpl implements UserManagementDao {

    @Autowired
    MongoDBConfigaration mongoDBConfigaration;

    @Override
    public String createUser(String user) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Users");
        /**
         * ** Insert ***
         */
        // create a document to store key and value
        DBObject document = (DBObject) JSON.parse(user);
        WriteResult state = table.insert(document);
        return state.toString();
    }

    @Override
    public String updateUser(String userName, String updatedUser) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Users");

        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("user_name", userName);

        BasicDBObject updateObj = (BasicDBObject) JSON.parse(updatedUser);

        WriteResult state = table.update(query, updateObj);

        return state.toString();
    }

    @Override
    public String deleteUser(String userName) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Users");

        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("user_name", userName);

        WriteResult state = table.remove(query);

        return state.toString();
    }

    @Override
    public String selectUser(String userName) {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Users");

        /**
         * ** Update ***
         */
        // search document where name="mkyong" and update it with new values
        BasicDBObject query = new BasicDBObject();
        query.put("user_name", userName);

        DBCursor cursor = table.find(query);

        if (cursor.hasNext()) {
            return cursor.next().toString();
        } else {
            return "{\n"
                    + "  \"user_name\": null\n"
                    + "}";
        }

    }

    @Override
    public String selectAllUsers() {
        DB db = mongoDBConfigaration.getMongoDBConnection();
        /**
         * ** Get collection / table from 'Users' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Users");

        DBCursor cursor = table.find();

        BasicDBObject obj = new BasicDBObject();

        int i = 1;
        while (cursor.hasNext()) {
            DBObject user = cursor.next();
            obj.append(user.get("user_name").toString(), user);

        }

        return obj.toString();
    }

}
