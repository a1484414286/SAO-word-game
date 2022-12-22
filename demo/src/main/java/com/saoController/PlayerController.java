package com.saoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClients;
import com.saoModel.ItemsSystem.ItemTypes.AntiItem;
import com.saoModel.ItemsSystem.ItemTypes.Crystal;
import com.saoModel.ItemsSystem.ItemTypes.Food;
import com.saoModel.ItemsSystem.ItemTypes.Material;
import com.saoModel.ItemsSystem.ItemTypes.Potion;
import com.saoModel.MapSystem.Direction;
import com.saoModel.MapSystem.Stage;
import com.saoModel.PlayerSystem.Player;
import com.saoView.MapData;
import com.saoView.PlayersData;

@RestController
public class PlayerController {
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");
    public static String SUCCESS = "SUCESS OPERATION";
    public static String FAILED = "FAILED OPERATION";

    @Autowired
    private PlayersData repo;
    private MapData mapData;

    @GetMapping("/addPlayer")
    public String savePlayer(@PathVariable int id, @RequestParam(required = false) String name) {
        repo.insert(new Player(id, name));
        return id + " " + " has been added to the database";
    }

    @RequestMapping(value = "/inventory/item/food/{id}", method = RequestMethod.PUT)
    public String addFood(@PathVariable int id, @RequestParam(required = true) String name, String desc, int val,
            int weight, int durability) {
        Player p = repo.findById(id).get();
        p.getBaggage().addChild(new Food(name, desc, val, weight, durability));
        repo.save(p);
        return "success";
    }

    @RequestMapping(value = "/inventory/item/potion/{id}", method = RequestMethod.PUT)
    public String addPotion(@PathVariable int id, @RequestParam(required = true) String name, String desc, int val,
            int weight, int durability) {
        Player p = repo.findById(id).get();
        p.getBaggage().addChild(new Potion(name, desc, val, weight, durability));
        repo.save(p);
        return "success";
    }

    @RequestMapping(value = "/inventory/item/anti/{id}", method = RequestMethod.PUT)
    public String addAntiItem(@PathVariable int id, @RequestParam(required = true) String name, String desc, int val,
            int weight, int durability) {
        Player p = repo.findById(id).get();
        p.getBaggage().addChild(new AntiItem(name, desc, val, weight, durability));
        repo.save(p);
        return "success";
    }

    @RequestMapping(value = "/inventory/item/material/{id}", method = RequestMethod.PUT)
    public String addMaterial(@PathVariable int id, @RequestParam(required = true) String name, String desc, int val,
            int weight, int durability) {
        Player p = repo.findById(id).get();
        p.getBaggage().addChild(new Material(name, desc, val, weight, durability));
        repo.save(p);
        return "success";
    }

    @RequestMapping(value = "/inventory/item/crystal/{id}", method = RequestMethod.PUT)
    public String addCrystal(@PathVariable int id, @RequestParam(required = true) String name, String desc, int val,
            int weight, int durability) {
        Player p = repo.findById(id).get();
        p.getBaggage().addChild(new Crystal(name, desc, val, weight, durability));
        repo.save(p);
        return "success";
    }

    // 待优化
    @RequestMapping(value = "/stage", method = RequestMethod.PUT)
    public String moveTo(@PathVariable int id, @RequestParam(required = true) String direction) {
        Stage nextMove;
        Stage currentMove;

        // 上下左右 -> direction
        // id == QQ id
        Player p = repo.findById(id).get();
        currentMove = p.getPosition();
        if (direction == "左" || direction == "←") {
            // 消除玩家在当前的纪录
            // remove player from "current" stage
            currentMove.getPlayers().remove(p);
            // 将玩家添加到下一个地区
            // add the player to next stage
            nextMove = p.getPosition().getNeighbors().get(Direction.WEST);
            p.setPosition(nextMove);
            repo.save(p);
            return SUCCESS;

        } else if (direction == "右" || direction == "→") {
            currentMove.getPlayers().remove(p);
            nextMove = p.getPosition().getNeighbors().get(Direction.EAST);
            p.setPosition(nextMove);
            repo.save(p);
            return SUCCESS;

        } else if (direction == "下" || direction == "↓") {
            currentMove.getPlayers().remove(p);
            nextMove = p.getPosition().getNeighbors().get(Direction.SOUTH);
            p.setPosition(nextMove);
            repo.save(p);
            return SUCCESS;

        } else if (direction == "上" || direction == "↑") {
            currentMove.getPlayers().remove(p);
            nextMove = p.getPosition().getNeighbors().get(Direction.NORTH);
            p.setPosition(nextMove);
            repo.save(p);
            return SUCCESS;

        }
        return FAILED;
    }

    // todo
    // weapon and armor will need to be implemented
    //
    @GetMapping("/All")
    public List<Player> retrivePlayers() {
        return repo.findAll();
    }
}
