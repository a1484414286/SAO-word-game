package com.saoView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saoModel.MapSystem.Map;

public interface MapData extends MongoRepository<Map, Integer> {

}
