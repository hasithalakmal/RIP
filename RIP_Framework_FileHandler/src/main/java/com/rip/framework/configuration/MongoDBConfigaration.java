/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rip.framework.configuration;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Hasitha Lakmal
 */
@Configuration
@ComponentScan(basePackages = {"com.rip.*.*"})
@PropertySource("classpath:mongo.properties")
public class MongoDBConfigaration {

    @Value("${mongo.host}")
    private String mongodbHost;

    @Value("${mongo.port}")
    private int mongodbPort;

    @Value("${mongo.databse}")
    private String mongodbDB;

    private DB db;

    public void test() {
        System.out.println(mongodbHost);
        try {

            /**
             * ** Connect to MongoDB ***
             */
            // Since 2.10.0, uses MongoClient
            MongoClient mongo = new MongoClient(mongodbHost, mongodbPort);

            /**
             * ** Get database ***
             */
            // if database doesn't exists, MongoDB will create it for you
            DB db = mongo.getDB(mongodbDB);

            /**
             * ** Get collection / table from 'testdb' ***
             */
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection table = db.getCollection("user");

            /**
             * ** Insert ***
             */
            // create a document to store key and value
            BasicDBObject document = new BasicDBObject();
            document.put("name", "mkyong");
            document.put("age", 30);
            document.put("createdDate", new Date());
            table.insert(document);

            /**
             * ** Find and display ***
             */
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", "mkyong");

            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            /**
             * ** Update ***
             */
            // search document where name="mkyong" and update it with new values
            BasicDBObject query = new BasicDBObject();
            query.put("name", "mkyong");

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("name", "mkyong-updated");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            table.update(query, updateObj);

            /**
             * ** Find and display ***
             */
            BasicDBObject searchQuery2
                    = new BasicDBObject().append("name", "mkyong-updated");

            DBCursor cursor2 = table.find(searchQuery2);

            while (cursor2.hasNext()) {
                System.out.println(cursor2.next());
            }

            /**
             * ** Done ***
             */
            System.out.println("Done");

        } catch (UnknownHostException | MongoException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public DB getMongoDBConnection() {

        try {
            /**
             * ** Connect to MongoDB ***
             */
            // Since 2.10.0, uses MongoClient
            MongoClient mongo = new MongoClient(mongodbHost, mongodbPort);

            /**
             * ** Get database ***
             */
            // if database doesn't exists, MongoDB will create it for you
            db = mongo.getDB(mongodbDB);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongoDBConfigaration.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;

    }

}
