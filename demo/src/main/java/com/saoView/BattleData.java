package com.saoView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saoModel.BattleSystem.BattleField;

public interface BattleData extends MongoRepository<BattleField, Integer> {

}
