// package com.DB;

// import java.util.HashMap;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// import org.bson.Document;
// import org.bson.codecs.configuration.CodecRegistry;
// import org.bson.codecs.pojo.PojoCodecProvider;
// import org.springframework.web.bind.annotation.RestController;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.mongodb.MongoClient;
// import com.mongodb.MongoClientSettings;
// import com.mongodb.client.FindIterable;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import com.mongodb.client.model.Filters;
// import com.mongodb.client.model.Updates;
// import com.saoModel.BagsSystem.AntiItemBag;
// import com.saoModel.BagsSystem.CrystalBag;
// import com.saoModel.BagsSystem.FoodBag;
// import com.saoModel.BagsSystem.PotionBag;
// import com.saoModel.ItemsSystem.ItemElement;
// import com.saoModel.ItemsSystem.ItemTypes.AntiItem;
// import com.saoModel.ItemsSystem.ItemTypes.Crystal;
// import com.saoModel.ItemsSystem.ItemTypes.Food;
// import com.saoModel.ItemsSystem.ItemTypes.Potion;
// import com.saoModel.PlayerSystem.Player;

// @RestController
// public class MongoUtils {
// public static MongoClient client = new MongoClient("localhost", 27017);
// public static MongoDatabase db = client.getDatabase("SAO_Game");
// static CodecRegistry pojoCodecRegistry =
// org.bson.codecs.configuration.CodecRegistries.fromRegistries(
// MongoClientSettings.getDefaultCodecRegistry(),
// org.bson.codecs.configuration.CodecRegistries
// .fromProviders(PojoCodecProvider.builder().automatic(true).build()));

// // public static void init() {
// // MongoCollection<Document> players = db.getCollection("Players");
// // MongoCollection<Document> items = db.getCollection("Items");
// // MongoCollection<Document> mobs = db.getCollection("Mobs");
// // MongoCollection<Document> AntiItemsBag = db.getCollection("AntiItemBag");
// // MongoCollection<Document> WeaponBag = db.getCollection("AntiItemBag");
// // MongoCollection<Document> ArmorBag = db.getCollection("ArmorBag");
// // MongoCollection<Document> CrystalBag = db.getCollection("CrystalBag");
// // MongoCollection<Document> FoodBag = db.getCollection("FoodBag");
// // MongoCollection<Document> MaterialBag = db.getCollection("MaterialBag");
// // MongoCollection<Document> PotionBag = db.getCollection("PotionBag");
// // players.drop();
// // AntiItemsBag.drop();
// // WeaponBag.drop();
// // ArmorBag.drop();
// // CrystalBag.drop();
// // FoodBag.drop();
// // MaterialBag.drop();
// // PotionBag.drop();
// // items.drop();
// // mobs.drop();
// // }

// public static Object instanceConverter(ItemElement item) {
// Object result = null;
// if (item instanceof AntiItem) {
// result = AntiItemBag.class;
// return result;
// } else if (item instanceof Crystal) {
// result = CrystalBag.class;
// return result;
// } else if (item instanceof Food) {
// result = FoodBag.class;
// return result;
// } else if (item instanceof Potion) {
// result = PotionBag.class;
// return result;
// }
// return result;
// }

// public static void insertItemsToPlayerBag(int id, ItemElement item) {
// Document doc = new Document();
// HashMap<Object, Object> bag;

// MongoCollection<Document> playersDB =
// db.getCollection("Players").withCodecRegistry(pojoCodecRegistry);
// Document searchQuery = new Document();
// // ????????????ID
// searchQuery.put("id", id);

// // ?????????????????????helper method
// String[] bagStr = instanceConverter(item).toString().split("BagsSystem.");
// String itemBag = bagStr[1];
// //

// if (playersDB.countDocuments(searchQuery) == 1) {
// FindIterable<Document> Found = playersDB.find(searchQuery);
// Document personBagge = Found.first();
// try {
// if (personBagge.get(itemBag) != null) {// ??????????????????,????????????,????????????
// Document str = (Document) personBagge.get(itemBag);
// bag = new ObjectMapper().readValue(str.toJson(), HashMap.class);
// } else {// ?????????????????????, ????????????
// bag = new HashMap<>();
// }
// bag.put(item.getName(), item);
// doc.put(itemBag, bag);
// playersDB.findOneAndUpdate(Filters.eq("id", id), Updates.set(itemBag,
// doc.get(itemBag)));

// } catch (JsonProcessingException e) {
// }
// }
// }

// public static void CreatePlayer(int id, String name) {
// Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

// // ??????db,?????????????????????id???????????????,????????????
// MongoCollection<Document> playersDB = db.getCollection("Players");
// Player p = new Player(id, name);
// Document doc = new Document();
// doc.put("id", p.getStats().getId());
// long found = playersDB.countDocuments(doc);
// if (found == 0) {
// doc.put("name", p.getName());
// doc.put("status", p.getStats().getDataFromStats());
// playersDB.insertOne(doc);
// }
// }

// }
