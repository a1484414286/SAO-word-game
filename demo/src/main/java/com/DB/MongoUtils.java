package com.DB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sao.ItemsSystem.ItemElement;
import com.sao.PlayerSystem.Player;

public class MongoUtils {

    public static void init() {
        MongoClient client;
        client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("SAO_Game");
        MongoCollection<Document> players = db.getCollection("Players");
        MongoCollection<Document> items = db.getCollection("Items");
        MongoCollection<Document> mobs = db.getCollection("Mobs");
        players.drop();
        items.drop();
        mobs.drop();

        // todo implement from execel data sheet
        client.close();
    }

    public static void insertItems(int id, ItemElement item) {
        MongoClient client;
        client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("SAO_Game");
        MongoCollection<Document> playersDB = db.getCollection("Players");
        Document searchQuery = new Document();
        searchQuery.put("id", id);
        if (playersDB.countDocuments(searchQuery) == 1) {
            FindIterable<Document> Found = playersDB.find(searchQuery);
            for (Document i : Found) {
                i.append("bag", item.toString());
                playersDB.updateOne(Filters.eq("id", "1"), i);
            }
        }
        client.close();

    }

    public static void insertPlayer(String name) {
        MongoClient client;
        client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("SAO_Game");
        MongoCollection<Document> playersDB = db.getCollection("Players");
        // will be data from player init
        long maxCount = playersDB.countDocuments();
        Player p = new Player((int) maxCount, name);
        Document doc = new Document();
        doc.put("id", p.getStats().getId());
        long found = playersDB.countDocuments(doc);

        if (found == 0) {
            doc.put("name", p.getName());
            doc.put("status", p.getStats().getDataFromStats());
            doc.put("bag", p.getBag().print().strip());
            doc.put("bag info", p.getBag().calculate());
            playersDB.insertOne(doc);
            client.close();
        }

    }
}
