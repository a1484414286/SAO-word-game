package com.saoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClients;
import com.saoModel.MapSystem.Map;
import com.saoView.MapData;

@RestController
public class MapController {
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");

    @Autowired
    private MapData mapData;
    // private PlayersData playersData;

    @RequestMapping(value = "/allMaps")
    public List<Map> getMethodName() {
        return mapData.findAll();
    }

    @RequestMapping(value = "/add/map/{size}")
    public String addMap(@PathVariable int size) {
        int id = (int) mongoOps.getCollection("Maps").countDocuments();
        mapData.insert(new Map(id, size));
        return "added sucess";
    }

    // @RequestMapping(value = "/add/map/stage/player/{userID}")
    // public String enterStage(@PathVariable int mapId, int stageNum, int userID) {
    // Map m = mapData.findById(mapId).get();
    // Player p = playersData.findById(userID).get();
    // LevelStage stage = m.getFloor().get(mapId).get(stageNum);
    // // p.setPosition(stage);
    // mapData.save(m);
    // return "stage added sucess";
    // }

}
