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

    @RequestMapping(value = "/allMaps")
    public List<Map> getMethodName() {
        return mapData.findAll();
    }

    @RequestMapping(value = "/add/map")
    public String addMap() {
        int id = (int) mongoOps.getCollection("Maps").countDocuments();
        mapData.insert(new Map(id));
        return "added sucess";
    }

    @RequestMapping(value = "/add/map/{id}/stage")
    public String addStage(@PathVariable int id) {
        Map m = mapData.findById(id).get();
        // m.getFloor().add(null)
        return "stage added sucess";
    }

}
