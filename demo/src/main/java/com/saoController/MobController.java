package com.saoController;

import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.saoModel.MobSystem.Factories.AbstractFactory;
import com.saoModel.MobSystem.Factories.FactoryProducer;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoView.MobData;

@RestController
public class MobController {
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");
    // if on produces enhanced mobs, if off reg mobs
    AbstractFactory mobFactory = FactoryProducer.getFactory(false);

    @Autowired
    private MobData MobRepo;

    @GetMapping("/AllMobs")
    public List<MobTemplate> retriveMobs() {
        return MobRepo.findAll();
    }

    @GetMapping("/addMob/{id}")
    public String addRegMob(@PathVariable int id, @RequestParam(required = true) String name, int hp, int mp,
            int respawnTime) {
        // mongoOps.insert(mob, "Mobs");
        // Query query = new Query();
        // query.addCriteria(Criteria.where())
        // mongoOps.count(, "Mobs");

        MongoCollection<Document> mobData = mongoOps.getCollection("Mobs");
        mobData.find().first();

        MobTemplate mob = mobFactory.getMob(RegMob.class, id, name, hp, mp, respawnTime, new HashMap<>());
        MobRepo.insert(mob);
        return "success";
    }
}
