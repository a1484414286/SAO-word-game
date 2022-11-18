package com.DB;

import com.sao.ItemsSystem.ItemTypes.Potion;

public class Mongo {
    public static void main(String[] args) {
        // MongoDatabase db = client.getDatabase("SAO_Game");
        // MongoCollection<Document> players = db.getCollection("Players");
        // MongoCollection<Document> AntiItemsBag = db.getCollection("AntiItemBag");
        // MongoCollection<Document> WeaponBag = db.getCollection("AntiItemBag");
        // MongoCollection<Document> ArmorBag = db.getCollection("ArmorBag");
        // MongoCollection<Document> CrystalBag = db.getCollection("CrystalBag");
        // MongoCollection<Document> FoodBag = db.getCollection("FoodBag");
        // MongoCollection<Document> MaterialBag = db.getCollection("MaterialBag");
        // MongoCollection<Document> PotionBag = db.getCollection("PotionBag");
        // MongoCollection<Document> items = db.getCollection("bag");
        // MongoCollection<Document> mobs = db.getCollection("Mobs");

        // AntiItemsBag.drop();
        // WeaponBag.drop();
        // ArmorBag.drop();
        // CrystalBag.drop();
        // FoodBag.drop();
        // MaterialBag.drop();
        // PotionBag.drop();
        // players.drop();
        // items.drop();
        // mobs.drop();

        // MongoUtils.init();
        MongoUtils.insertPlayer(0, "晓桐");
        MongoUtils.insertItemsToPlayerBag(1, new Potion(0, "红药", "补血", 50, 0, 0));
        MongoUtils.insertPlayer(1, "二大爷");
        // MongoUtils.insertItemsToPlayerBag(1, new Potion(0, "蓝药", "补魔", 50, 0, 0));
        // first drop to ensure the creation of database will not be affected by
        // previous state
        // Collection = table, Document = rows,cols
        // Document document =
        // new Document("name", "Daeshan");
        // document.append("sex", "male");
        // document.append("age", "21");
        // document.append("race", "african-american");
        // Document doc = new Document();
        // doc.append("name", "dory");
        // doc.append("sex", "unknown");
        // doc.append("sharpness", 5);
        // Document[] documents = new Document[2];
        // documents[0] = document;
        // documents[1] = doc;
        // for(Document i : documents)
        // {
        // collection.insertOne(i);
        // }

        // returns everything from database

        // FindIterable<Document> i = collection.find();

        // System.out.println("____________________________________________________");
        // for(Document r : i)
        // {
        // System.out.println(r);
        // }
        // client.close();

    }
}
