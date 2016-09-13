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
import java.util.Date;
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
         * ** Get collection / table from 'testdb' ***
         */
        // if collection doesn't exists, MongoDB will create it for you
        DBCollection table = db.getCollection("Users");

        /**
         * ** Insert ***
         */
        // create a document to store key and value
        DBObject  document = (DBObject) JSON.parse(user);
        WriteResult state = table.insert(document);
        return state.toString(); 
    }

}
