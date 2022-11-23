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
@Document(collection = "DungenMob")
public class DungeonMob implements MobTemplate {
    @Id
    private final int id;
    private final String name;
    private StatsTemplate stats;
    private HashMap<Integer, ItemElement> dropList;
    private int respawnTime;
    private String attackStyle;
    private String hidAttackStyle;

    public DungeonMob(int id, String name, int spawnTime,
            HashMap<Integer, ItemElement> droppable) {
        this.id = id;
        this.name = name;
        // this.attackStyle = atkStlye;
        // this.hidAttackStyle = hidAtkStyle;
        this.dropList = droppable;
        this.respawnTime = spawnTime;
        this.stats = new StatsTemplate();
    }

    public DungeonMob(int id, String name, int spawnTime, String attackStyle, String hidAtkStlye) {
        this.id = id;
        this.name = name;
        this.respawnTime = spawnTime;
        this.attackStyle = attackStyle;
        this.hidAttackStyle = hidAtkStlye;
        this.dropList = new HashMap<>();
        this.stats = new StatsTemplate();
    }

    @Override
    public String getAtkSyle() {
        // TODO Auto-generated method stub
        return null;
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
    public String toString() {
        return String.format("\n id: %d, %s %s", this.id, this.name, this.stats);
    }

    @Override
    public void statusAdjustification() {
        // TODO Auto-generated method stub

    }

}
