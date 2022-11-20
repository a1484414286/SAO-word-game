package com.saoView;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saoModel.ItemsSystem.ItemTypes.Potion;
import com.saoModel.PlayerSystem.Player;

public interface PlayersData extends MongoRepository<Player, Integer> {

    void insert(Potion potion);

}
