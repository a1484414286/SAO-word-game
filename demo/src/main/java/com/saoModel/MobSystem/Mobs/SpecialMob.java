package com.saoModel.MobSystem.Mobs;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.StatusSystem.StatsTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "SpecialMob")
public class SpecialMob implements MobTemplate {
    @Id
    private final int id;
    private final String name;
    private StatsTemplate stats;
    private HashMap<Integer, ItemElement> dropList;
    private long respawnTime;
    private String attackStyle;

    public SpecialMob(int id, String name, int spawnTime, HashMap<Integer, ItemElement> droppable) {
        this.id = id;
        this.name = name;
        this.dropList = droppable;
        this.respawnTime = spawnTime;
        this.stats = new StatsTemplate();
    }

    public SpecialMob(int id, String name, int spawnTime) {
        this.id = id;
        this.name = name;
        this.respawnTime = spawnTime;
        this.dropList = new HashMap<>();
        this.stats = new StatsTemplate();
    }

    @Override
    public String getAtkSyle() {
        return attackStyle;
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
