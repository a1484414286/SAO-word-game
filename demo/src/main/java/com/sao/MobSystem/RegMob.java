package com.sao.MobSystem;

import java.util.HashMap;

import com.sao.ItemsSystem.ItemElement;
import com.sao.StatusSystem.StatsTemplate;

public class RegMob extends MobTemplate{
    private int id;
    private String name;
    private long respawnTime; 
    private StatsTemplate statsTemplate;

    public RegMob(int id, String name, HashMap<Integer, ItemElement> droplist, long time) {
        super(id, name, droplist, time);
    }
    public RegMob(int id, String name, HashMap<Integer, ItemElement> droplist, StatsTemplate stats, long time) {
        super(id, name, droplist, stats, time);
    }
    public RegMob(int id, String name, long time) {
        super(id, name, time);
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
