package com.sao;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class mongo {
    public static void main(String[] args) {
        MongoClient client;
        
        client = new MongoClient("localhost", 27017);
        
        MongoDatabase db = client.getDatabase("MongoDB");
        MongoCollection<Document> collection = db.getCollection("test");
        collection.drop();
        //first drop to ensure the creation of database will not be affected by previous state
        //Collection = table, Document = rows,cols
        Document document =
        new Document("name", "Daeshan");
        document.append("sex", "male");
        document.append("age", "21");
        document.append("race", "african-american");
        Document doc = new Document();
        doc.append("name", "dory");
        doc.append("sex", "unknown");
        doc.append("sharpness", 5);
        Document[] documents = new Document[2];
        documents[0] = document;
        documents[1] = doc;
        for(Document i : documents)
        {
            collection.insertOne(i);
        }

        //returns everything from database

        FindIterable<Document> i = collection.find();

        System.out.println("____________________________________________________");
        for(Document r : i)
        {
            System.out.println(r);
        }
        client.close();

    }
}
