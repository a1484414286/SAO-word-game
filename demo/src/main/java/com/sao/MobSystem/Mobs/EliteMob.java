package com.sao.MobSystem.Mobs;

import java.util.HashMap;
import com.sao.ItemsSystem.ItemElement;
import com.sao.MobSystem.MobTemplate;
import com.sao.StatusSystem.StatsTemplate;

public class EliteMob implements MobTemplate{
    private final int id;
    private final String name;
    private StatsTemplate stats;
    private HashMap<Integer,ItemElement> dropList;
    private long respawnTime;

    public EliteMob(int id, String name, int spawnTime, HashMap<Integer, ItemElement> droppable)
    {
        this.id = id;
        this.name = name;
        this.dropList = droppable;
        this.respawnTime = spawnTime;
        this.stats = new StatsTemplate();
    }
   
    // @Override
    // public String toString() {
    //     return String.format("\n id: %d, %s %s", this.id, this.name, this.stats);
    // }

    @Override
    public void statusAdjustification() {
        // TODO Auto-generated method stub
        
    }
    
}
