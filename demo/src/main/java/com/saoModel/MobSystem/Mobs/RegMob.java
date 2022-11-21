package com.saoModel.MobSystem.Mobs;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.StatusSystem.StatsTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "RegMob")
public class RegMob implements MobTemplate {
    private @Id int id;
    private final String name;
    private final int respawnTime;
    private StatsTemplate stats;
    private HashMap<Integer, ItemElement> droppable;

    public RegMob(int id, String name, int respawnTime, HashMap<Integer, ItemElement> droppable) {
        this.id = id;
        this.name = name;
        this.droppable = droppable;
        this.respawnTime = respawnTime;
        this.stats = new StatsTemplate();
    }

    public HashMap<Integer, ItemElement> getDropList() {
        return droppable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRespawnTime() {
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
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return String.format("\n名字: %s %s", this.name, this.stats);
    }
}
