package com.sao.MapSystem;

import java.util.concurrent.atomic.AtomicInteger;

import com.sao.MobSystem.Mobs.MobTemplate;
import com.sao.PlayerSystem.Player;

public class LevelStage {
    public AtomicInteger increments;
    private final int id;
    private final String name;
    private MobTemplate[] mobs;
    private Player[] players;
    public LevelStage(String name, MobTemplate[] mobs, Player[] players)
    {
        this.id = increments.incrementAndGet();
        this.name = name;
        this.mobs = mobs;
        this.players = players;
    }

    public LevelStage(String name)
    {
        this.id = increments.incrementAndGet();
        this.name = name;
    }

    public LevelStage(String name, MobTemplate[] mobs)
    {
        this.id = increments.incrementAndGet();
        this.name = name;
        this.mobs = mobs;
    }

    public int getId() {
        return id;
    }
    public AtomicInteger getIncrements() {
        return increments;
    }
    public MobTemplate[] getMobs() {
        return mobs;
    }
    public String getName() {
        return name;
    }
    public Player[] getPlayers() {
        return players;
    }

}
