package com.geektcp.alpha.db.arango;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/4/15.
 */
@Slf4j
public class DriverImportTest {

    private static ArangoDB arangoDB;

    private String dbName = "test";
    private String collectionName = "firstCollection";

//    private String host = "alpha-server";
    private String host = "192.168.1.176";

    @Before
    public void buildClient(){
        arangoDB = new ArangoDB.Builder()
                .host(host,8529)
                .user("root")
                .password("123456")
                .build();
    }


    @Test
    public void createDatabase() {
        try {
            arangoDB.createDatabase(dbName);
            System.out.println("Database created: " + dbName);
        } catch (ArangoDBException e) {
            System.out.println("create error: " + e.getErrorMessage());
        }

    }

    @Test
    public void createCollection() {
        try {
            CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
            System.out.println("Collection created: " + myArangoCollection.getName());
        } catch (ArangoDBException e) {
            System.err.println("Failed to create collection: " + collectionName + "; " + e.getMessage());
        }
    }

    public void createDocument(){
        BaseDocument myObject = new BaseDocument();
        myObject.setKey("myKey");
        myObject.addAttribute("a", "Foo");
        myObject.addAttribute("b", 42);
        try {
            arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);
            System.out.println("Document created");
        } catch (ArangoDBException e) {
            System.err.println("Failed to create document. " + e.getMessage());
        }
    }

    public VPackSlice readVeloyPack(){
        VPackSlice myDocument = null;
        try {
            myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("myKey",
                    VPackSlice.class);
            System.out.println("Key: " + myDocument.get("_key").getAsString());
            System.out.println("Attribute a: " + myDocument.get("a").getAsString());
            System.out.println("Attribute b: " + myDocument.get("b").getAsInt());
            //System.out.println("Attribute c: " + myDocument.get("c").getAsInt());
        } catch (ArangoDBException | VPackException e) {
            System.err.println("Failed to get document: myKey; " + e.getMessage());
        }
        return myDocument;
    }



    public void updateDocument(){
        //get the document
        BaseDocument myObject = readDocument();
        //update the document
        try {
            myObject.addAttribute("c",11);
            arangoDB.db(dbName).collection(collectionName).updateDocument("myKey", myObject);
        } catch (ArangoDBException e) {
            System.err.println("Failed to update document. " + e.getMessage());
        }
        //read the document again
        readDocument();
    }

    private BaseDocument readDocument(){
        BaseDocument myObject = null;
        try {
            myObject = arangoDB.db(dbName).collection(collectionName).getDocument("myKey",
                    BaseDocument.class);
            System.out.println("Key: " + myObject.getKey());
            System.out.println("Attribute a: " + myObject.getAttribute("a"));
            System.out.println("Attribute b: " + myObject.getAttribute("b"));
            System.out.println("Attribute c: " + myObject.getAttribute("c"));
        } catch (ArangoDBException e) {
            System.err.println("Failed to get document: myKey; " + e.getMessage());
        }
        return myObject;
    }

    public void deleteDocument(){
        try {
            arangoDB.db(dbName).collection(collectionName).deleteDocument("myKey");
        } catch (ArangoDBException e) {
            System.err.println("Failed to delete document. " + e.getMessage());
        }
    }



}
