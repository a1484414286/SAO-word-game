package com.saoController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

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
    public static String SUCCESS = "SUCESS OPERATION";
    public static String FAILED = "FAILED OPERATION";

    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "SAO_Game");
    @Autowired
    private MapData mapData;

    @GetMapping(value = "/allMaps")
    public List<Map> getMethodName() {
        return mapData.findAll();
    }

    @GetMapping(value = "/getStage/{id}")
    public Map getMap(@PathVariable int id) {
        return mapData.findById(id).get();
    }

    // this will be a "setted" amount of space, where the user can edit based on
    // given stage/tile
    @PostMapping(value = "/initSizedMap")
    public String initSizedMap(@RequestBody String jsonStr) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = mapper.readValue(jsonStr, HashMap.class);
        int id = (int) mapData.count();
        mapData.insert(new Map(id, Integer.parseInt(map.get("size"))));
        return SUCCESS;
    }

    // this will automatically increment based on the count of "Map"(世界)
    @PostMapping(value = "/initMap")
    public String initMap() throws JsonMappingException, JsonProcessingException {
        int id = (int) mapData.count();
        mapData.insert(new Map(id));
        return SUCCESS;
    }

    // adding stage to each associated map
    // 添加区域到地图
    @PostMapping(value = "/addStage")
    public String addStage(@RequestBody String str) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = mapper.readValue(str, HashMap.class);

        String mapID = map.get("mapID");
        Map currentMap = mapData.findById(Integer.parseInt(mapID)).get();
        String name = map.get("name");
        int range = currentMap.getFloor().size();
        // finding if the name exists in current map
        // 寻找所添加的区块有没有被记录
        for (int i = 0; i < range; i++) {
            if (!currentMap.getFloor().get(i).getName().equals(name)) {
                currentMap.getFloor().add(new Stage(range, name));
                return SUCCESS;
            }
        }
        return FAILED;
    }

    protected <T> Object readWithMsgConverters(NativeWebRequest webRequest, MethodParameter param) {
        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest(HttpServletRequest.class);

        return null;
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
