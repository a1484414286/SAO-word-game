package com.sao.MobSystem;

import java.util.HashMap;
import java.util.Random;

import com.sao.ItemsSystem.ItemElement;
import com.sao.StatusSystem.StatsTemplate;

public class MobTemplate {
    private final int id;
    private final String name;
    private StatsTemplate stats;
    private HashMap<Integer,ItemElement> dropList;
    private long respawnTime;
    private int quality;

    public MobTemplate(int id, String name, HashMap<Integer, ItemElement> droplist, StatsTemplate stats, long time)
    {
        this.id = id;
        this.name = name;
        this.dropList = droplist;
        this.respawnTime = time;
        this.stats = stats;
        this.quality = new Random().nextInt(4);
    }

    public MobTemplate(int id, String name, HashMap<Integer, ItemElement> droplist, long time)
    {
        this.id = id;
        this.name = name;
        this.respawnTime = time;
        this.dropList = droplist;
        this.stats = new StatsTemplate();
        this.quality = new Random().nextInt(4);
    }

  
    public MobTemplate(int id2, String name2, long time) {
        this.id = id2;
        this.name = name2;
        this.respawnTime = time;
        this.dropList = new HashMap<>();
        this.quality = new Random().nextInt(4);
    }

    public StatsTemplate getStats() {
        return stats;
    }
    
    @Override
    public String toString() {
        return String.format("\n id: %d, %s %s", this.id, this.name, this.stats);
    }

}
