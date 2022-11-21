package com.saoModel.MapSystem;

import java.util.ArrayList;

import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.PlayerSystem.Player;

public class LevelStage {
    private final int id;
    private final String name;
    private ArrayList<MobTemplate> mobs;
    private ArrayList<Player> players;

    public LevelStage(int id, String name, ArrayList<MobTemplate> mobs, ArrayList<Player> players) {
        this.id = id;
        this.name = name;
        this.mobs = mobs;
        this.players = players;
    }

    public LevelStage(int id, String name) {
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
    }

    public LevelStage(int id, String name, ArrayList<MobTemplate> mobs) {
        this.id = id;
        this.name = name;
        this.mobs = mobs;
        this.players = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<MobTemplate> getMobs() {
        return mobs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player e) {
        this.players.add(e);
    }

    @Override
    public String toString() {
        String result = "";
        result += getName() + "\n 玩家列表: ";
        for (Player p : players) {
            result += p.getName();
        }
        return result;
    }
}
