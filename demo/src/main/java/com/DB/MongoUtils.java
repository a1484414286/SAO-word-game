package com.DB;

import java.util.HashMap;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.sao.BagsSystem.AntiItemBag;
import com.sao.BagsSystem.CrystalBag;
import com.sao.BagsSystem.FoodBag;
import com.sao.BagsSystem.PotionBag;
import com.sao.ItemsSystem.ItemElement;
import com.sao.ItemsSystem.ItemTypes.AntiItem;
import com.sao.ItemsSystem.ItemTypes.Crystal;
import com.sao.ItemsSystem.ItemTypes.Food;
import com.sao.ItemsSystem.ItemTypes.Potion;
import com.sao.PlayerSystem.Player;

public class MongoUtils {
    public static MongoClient client = new MongoClient("localhost", 27017);
    public static MongoDatabase db = client.getDatabase("SAO_Game");
    CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            org.bson.codecs.configuration.CodecRegistries
                    .fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    public static void init() {
        MongoCollection<Document> players = db.getCollection("Players");
        // MongoCollection<Document> items = db.getCollection("Items");
        // MongoCollection<Document> mobs = db.getCollection("Mobs");
        // MongoCollection<Document> AntiItemsBag = db.getCollection("AntiItemBag");
        // MongoCollection<Document> WeaponBag = db.getCollection("AntiItemBag");
        // MongoCollection<Document> ArmorBag = db.getCollection("ArmorBag");
        // MongoCollection<Document> CrystalBag = db.getCollection("CrystalBag");
        // MongoCollection<Document> FoodBag = db.getCollection("FoodBag");
        // MongoCollection<Document> MaterialBag = db.getCollection("MaterialBag");
        // MongoCollection<Document> PotionBag = db.getCollection("PotionBag");
        players.drop();
        // AntiItemsBag.drop();
        // WeaponBag.drop();
        // ArmorBag.drop();
        // CrystalBag.drop();
        // FoodBag.drop();
        // MaterialBag.drop();
        // PotionBag.drop();
        // items.drop();
        // mobs.drop();
    }

    public static Object instanceConverter(ItemElement item) {
        Object result = null;
        if (item instanceof AntiItem) {
            result = AntiItemBag.class;
            return result;
        } else if (item instanceof Crystal) {
            result = CrystalBag.class;
            return result;
        } else if (item instanceof Food) {
            result = FoodBag.class;
            return result;
        } else if (item instanceof Potion) {
            result = PotionBag.class;
            return result;
        }
        return result;
    }

    public static void insertItemsToPlayerBag(int id, ItemElement item) {
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                org.bson.codecs.configuration.CodecRegistries
                        .fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoCollection<Document> playersDB = db.getCollection("Players").withCodecRegistry(pojoCodecRegistry);
        Document searchQuery = new Document();
        // 查找相同ID
        searchQuery.put("id", id);
        String bagStr = instanceConverter(item).toString();
        System.out.println(bagStr);
        String[] splitteStrings = bagStr.toString().split("BagsSystem.");
        String itemBag = splitteStrings[1];
        // String bag = bagStr[bagStr.length - 1];
        if (playersDB.countDocuments(searchQuery) == 1) {
            FindIterable<Document> Found = playersDB.find(searchQuery);
            Document updateDoc = Found.first();
            String json = updateDoc.toJson();

            try {
                HashMap<String, String> map = new ObjectMapper().readValue(json, HashMap.class);
                System.out.println(map.get(itemBag));
                Object i = updateDoc.get(itemBag);
                Document doc = new Document();
                doc.put(item.getName(), item);
                playersDB.findOneAndUpdate(Filters.eq("id", id), Updates.set(itemBag, doc));
            } catch (JsonProcessingException e) {
            }
        }
    }

    public static void insertPlayer(int id, String name) {
        MongoCollection<Document> playersDB = db.getCollection("Players");
        // will be data from player init
        Player p = new Player(id, name);
        Document doc = new Document();
        doc.put("id", p.getStats().getId());
        long found = playersDB.countDocuments(doc);
        if (found == 0) {
            doc.put("name", p.getName());
            doc.put("status", p.getStats().getDataFromStats());
            doc.put("bag", p.getBag().print().strip());
            playersDB.insertOne(doc);
        }
    }
}
