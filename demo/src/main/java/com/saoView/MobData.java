package com.saoView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saoModel.MobSystem.Mobs.MobTemplate;

public interface MobData extends MongoRepository<MobTemplate, Integer> {

}
