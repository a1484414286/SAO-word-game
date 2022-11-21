package com.saoController;

import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.saoModel.MobSystem.Factories.AbstractFactory;
import com.saoModel.MobSystem.Factories.FactoryProducer;
import com.saoModel.MobSystem.Mobs.DungeonMob;
import com.saoModel.MobSystem.Mobs.EliteMob;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.PeacefulMob;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoModel.MobSystem.Mobs.SpecialMob;
import com.saoView.MobData;

@RestController
public class MobController {

    // http://localhost:8080/addmob/reg?name=野猪&hp=20&mp=10&respawnTime=2
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");
    // if on produces enhanced mobs, if off reg mobs
    AbstractFactory mobFactory = FactoryProducer.getFactory(false);

    @Autowired
    private MobData MobRepo;

    @GetMapping("/AllMobs")
    public List<MobTemplate> retriveMobs() {
        return MobRepo.findAll();
    }

    @GetMapping("/addmob/reg")
    public String addRegMob(@RequestParam(required = true) String name, int hp, int mp,
            int respawnTime) {
        MongoCollection<Document> mobData = mongoOps.getCollection("Mobs");
        int id = (int) mobData.countDocuments();
        MobTemplate mob = mobFactory.getMob(RegMob.class, id, name, hp, mp, respawnTime, new HashMap<>());
        MobRepo.insert(mob);
        return "success";
    }

    @GetMapping("/addmob/dun")
    public String addDunMob(@RequestParam(required = true) String name, int hp, int mp,
            int respawnTime) {
        MongoCollection<Document> mobData = mongoOps.getCollection("Mobs");
        int id = (int) mobData.countDocuments();
        MobTemplate mob = mobFactory.getMob(DungeonMob.class, id, name, hp, mp, respawnTime, new HashMap<>());
        MobRepo.insert(mob);
        return "success";
    }

    @GetMapping("/addmob/elite")
    public String addEliteMob(@RequestParam(required = true) String name, int hp, int mp,
            int respawnTime) {
        MongoCollection<Document> mobData = mongoOps.getCollection("Mobs");
        int id = (int) mobData.countDocuments();
        MobTemplate mob = mobFactory.getMob(EliteMob.class, id, name, hp, mp, respawnTime, new HashMap<>());
        MobRepo.insert(mob);
        return "success";
    }

    @GetMapping("/addmob/peace")
    public String addPeaceMob(@RequestParam(required = true) String name, int hp, int mp,
            int respawnTime) {
        MongoCollection<Document> mobData = mongoOps.getCollection("Mobs");
        int id = (int) mobData.countDocuments();
        MobTemplate mob = mobFactory.getMob(PeacefulMob.class, id, name, hp, mp, respawnTime, new HashMap<>());
        MobRepo.insert(mob);
        return "success";
    }

    @GetMapping("/addmob/spec")
    public String addSpecialMob(@RequestParam(required = true) String name, int hp, int mp,
            int respawnTime) {
        MongoCollection<Document> mobData = mongoOps.getCollection("Mobs");
        int id = (int) mobData.countDocuments();
        MobTemplate mob = mobFactory.getMob(SpecialMob.class, id, name, hp, mp, respawnTime, new HashMap<>());
        MobRepo.insert(mob);
        return "success";
    }

}
