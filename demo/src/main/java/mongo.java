

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class mongo {
    public static void main(String[] args) {
        MongoClient client;

        client = new MongoClient("localhost", 27017);

        MongoDatabase db = client.getDatabase("MongoDB");
        MongoCollection<Document> collection = db.getCollection("test");
        
        Document document = new Document("name", "Daeshan");
        document.append("sex", "male");
        document.append("age", "21");
        document.append("race", "african american");
        
        collection.insertOne(document);
        // collection.drop();

    }
}
