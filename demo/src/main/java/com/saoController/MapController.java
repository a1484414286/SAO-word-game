package com.saoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saoModel.MapSystem.Map;
import com.saoView.MapData;

@RestController
public class MapController {

    @Autowired
    private MapData mapData;

    @RequestMapping(value = "/allMaps")
    public List<Map> getMethodName() {
        return mapData.findAll();
    }

    @RequestMapping(value = "/add/map")
    public String addMap() {
        mapData.insert(new Map());
        return "added sucess";
    }

}
