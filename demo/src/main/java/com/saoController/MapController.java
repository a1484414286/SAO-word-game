package com.saoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gwt.dev.util.collect.HashMap;
import com.mongodb.client.MongoClients;
import com.saoModel.MapSystem.Map;
import com.saoModel.MapSystem.Stage;
import com.saoView.MapData;

@RestController
public class MapController {
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");
    public static String SUCCESS = "SUCESS OPERATION";
    @Autowired
    private MapData mapData;

    @GetMapping(value = "/allMaps")
    public List<Map> getMethodName() {
        return mapData.findAll();
    }

    @GetMapping(value = "/getStage/{id}")
    public Stage getStage(@PathVariable int id) {
        return new Stage(0, null);
    }

    @PostMapping(value = "/initMap")
    public String initMap(@RequestBody String jsonStr) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = mapper.readValue(jsonStr, HashMap.class);
        int id = (int) mapData.count();
        mapData.insert(new Map(id, Integer.parseInt(map.get("size"))));
        return SUCCESS;
    }

    @GetMapping(value = "/addStage")
    public String addStage(@RequestBody String str) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = mapper.readValue(str, HashMap.class);
        String mapID = map.get("mapID");
        String name = map.get("name");
        Map currentMap = mapData.findById(Integer.parseInt(mapID)).get();
        int stageID = currentMap.getFloor().size();
        currentMap.getFloor().add(new Stage(stageID, name));
        return SUCCESS;
    }

    @PostMapping(value = "/add/map/{size}")
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
