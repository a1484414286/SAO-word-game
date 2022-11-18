package com.DB;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    static CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            org.bson.codecs.configuration.CodecRegistries
                    .fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    public static void init() {
        // MongoCollection<Document> players = db.getCollection("Players");
        // MongoCollection<Document> items = db.getCollection("Items");
        // MongoCollection<Document> mobs = db.getCollection("Mobs");
        // MongoCollection<Document> AntiItemsBag = db.getCollection("AntiItemBag");
        // MongoCollection<Document> WeaponBag = db.getCollection("AntiItemBag");
        // MongoCollection<Document> ArmorBag = db.getCollection("ArmorBag");
        // MongoCollection<Document> CrystalBag = db.getCollection("CrystalBag");
        // MongoCollection<Document> FoodBag = db.getCollection("FoodBag");
        // MongoCollection<Document> MaterialBag = db.getCollection("MaterialBag");
        // MongoCollection<Document> PotionBag = db.getCollection("PotionBag");
        // players.drop();
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
        Document doc = new Document();
        HashMap<Object, Object> bag;

        MongoCollection<Document> playersDB = db.getCollection("Players").withCodecRegistry(pojoCodecRegistry);
        Document searchQuery = new Document();
        // 查找相同ID
        searchQuery.put("id", id);

        // 查找物品归属的helper method
        String[] bagStr = instanceConverter(item).toString().split("BagsSystem.");
        String itemBag = bagStr[1];
        //

        if (playersDB.countDocuments(searchQuery) == 1) {
            FindIterable<Document> Found = playersDB.find(searchQuery);
            Document personBagge = Found.first();
            try {
                if (personBagge.get(itemBag) != null) {// 如果找到背包,提取数据,添加数据
                    Document str = (Document) personBagge.get(itemBag);
                    bag = new ObjectMapper().readValue(str.toJson(), HashMap.class);
                } else {// 如果找不到背包, 创建背包
                    bag = new HashMap<>();
                }
                bag.put(item.getName(), item);
                doc.put(itemBag, bag);
                playersDB.findOneAndUpdate(Filters.eq("id", id), Updates.set(itemBag,
                        doc.get(itemBag)));

            } catch (JsonProcessingException e) {
            }
        }
    }

    public static void CreatePlayer(int id, String name) {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        // 查询db,看有没有一样的id，如果没有,添加玩家
        MongoCollection<Document> playersDB = db.getCollection("Players");
        Player p = new Player(id, name);
        Document doc = new Document();
        doc.put("id", p.getStats().getId());
        long found = playersDB.countDocuments(doc);
        if (found == 0) {
            doc.put("name", p.getName());
            doc.put("status", p.getStats().getDataFromStats());
            playersDB.insertOne(doc);
        }
    }
}
