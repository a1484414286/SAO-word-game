package com.saoModel.MobSystem.Mobs;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.StatusSystem.StatsTemplate;

public class PeacefulMob implements MobTemplate {
    public AtomicInteger incrementID = new AtomicInteger();
    private int id;
    private final String name;
    private StatsTemplate stats;
    private HashMap<Integer, ItemElement> dropList;
    private long respawnTime;

    public PeacefulMob(String name, int spawnTime, HashMap<Integer, ItemElement> droppable) {
        this.id = incrementID.incrementAndGet();
        this.name = name;
        this.dropList = droppable;
        this.respawnTime = spawnTime;
        this.stats = new StatsTemplate();
    }

    public HashMap<Integer, ItemElement> getDropList() {
        return dropList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getRespawnTime() {
        return respawnTime;
    }

    public StatsTemplate getStats() {
        return stats;
    }

    @Override
    public void statusAdjustification() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        return String.format("\n id: %d, %s %s", this.id, this.name, this.stats);
    }

}
