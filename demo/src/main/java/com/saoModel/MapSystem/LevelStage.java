package com.saoModel.MapSystem;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.PlayerSystem.Player;

public class LevelStage {
    public AtomicInteger increments;
    private final int id;
    private final String name;
    private ArrayList<MobTemplate> mobs;
    private ArrayList<Player> players;

    public LevelStage(String name, ArrayList<MobTemplate> mobs, ArrayList<Player> players) {
        this.increments = new AtomicInteger();
        this.id = increments.incrementAndGet();
        this.name = name;
        this.mobs = mobs;
        this.players = players;
    }

    public LevelStage(String name) {
        this.increments = new AtomicInteger();
        this.id = increments.incrementAndGet();
        this.name = name;
        this.players = new ArrayList<>();
    }

    public LevelStage(String name, ArrayList<MobTemplate> mobs) {
        this.increments = new AtomicInteger();
        this.id = increments.incrementAndGet();
        this.name = name;
        this.mobs = mobs;
        this.players = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public AtomicInteger getIncrements() {
        return increments;
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
