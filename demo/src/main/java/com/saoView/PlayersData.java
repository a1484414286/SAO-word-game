package com.saoView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saoModel.PlayerSystem.Player;

public interface PlayersData extends MongoRepository<Player, Integer> {

}
