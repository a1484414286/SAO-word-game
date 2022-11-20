package com.saoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClients;
import com.saoModel.ItemsSystem.ItemTypes.Food;
import com.saoModel.PlayerSystem.Player;
import com.saoView.PlayersData;

@RestController
public class PlayerController {
    // AnnotationConfigApplicationContext ctx = new
    // AnnotationConfigApplicationContext(SpringMongoConfig.class);
    // MongoOperations mongoOperation = (MongoOperations) ctx.getBean("SAO_Game");
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");

    @Autowired
    private PlayersData repo;

    @GetMapping("/addPlayer/{id}/{name}")
    public String savePlayer(@PathVariable int id, String name) {
        // for (String i : mongoOperation.getCollectionNames()) {
        // System.out.println(i);
        // }
        repo.insert(new Player(id, name));
        return id + " " + " has been added to the database";
    }

    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    public String addItem() {
        Player p = repo.findById(20).get();
        p.getBag().addChild((new Food(0, "面包", "吃的", 0, 0, 0)));
        repo.save(p);
        return "success";
    }

    @GetMapping("/All")
    public List<Player> retrivePlayers() {

        return repo.findAll();
    }
}
