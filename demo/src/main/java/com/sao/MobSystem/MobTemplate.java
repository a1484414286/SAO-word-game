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

    public MobTemplate(int id, String name, HashMap<Integer, ItemElement> droplist, long time)
    {
        this.id = id;
        this.name = name;
        this.dropList = droplist;
        this.respawnTime = time;
        this.stats = new StatsTemplate();
        this.quality = new Random().nextInt(4);
    }

}
