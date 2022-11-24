package com.saoModel.MapSystem;

import java.util.ArrayList;

import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.PlayerSystem.Player;

import lombok.Getter;

@Getter
public class LevelStage {
    private final int id;
    private final String name;
    private ArrayList<MobTemplate> mobs;
    // private ArrayList<Player> players;

    // public LevelStage(int id, String name, ArrayList<MobTemplate> mobs,
    // ArrayList<Player> players) {
    // this.id = id;
    // this.name = name;
    // this.mobs = mobs;
    // this.players = players;
    // }

    public LevelStage(int id, String name) {
        this.id = id;
        this.name = name;
        // this.players = new ArrayList<>();
        this.mobs = new ArrayList<>();
    }

    // public LevelStage(int id, String name, ArrayList<MobTemplate> mobs) {
    // this.id = id;
    // this.name = name;
    // this.mobs = mobs;
    // this.players = new ArrayList<>();
    // }

    public ArrayList<MobTemplate> getMobs() {
        return mobs;
    }

    public void addPlayer(Player e) {
        e.setPosition(this);
        this.players.add(e);
    }

}
