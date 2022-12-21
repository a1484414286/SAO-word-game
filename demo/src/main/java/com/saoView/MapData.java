package com.saoView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saoModel.MapSystem.Map;
import com.saoModel.MapSystem.Stage;

public interface MapData extends MongoRepository<Map, Integer> {
    void insert(Stage stage);
}
