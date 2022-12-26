package com.saoModel.MobSystem.Mobs;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.StatusSystem.BaseTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "RegMob")
public class RegMob implements MobTemplate {
    private @Id int id;
    private final String name;
    private final int respawnTime;
    private BaseTemplate base;
    private HashMap<Integer, ItemElement> droppable;
    private String attackStyle;

    public RegMob(int id, String name, int respawnTime, HashMap<Integer, ItemElement> droppable) {
        this.id = id;
        this.name = name;
        this.attackStyle = "布";
        this.droppable = droppable;
        this.respawnTime = respawnTime;
        this.base = new BaseTemplate(name);
    }

    public RegMob(int id, String name, int spawnTime) {
        this.id = id;
        this.name = name;
        this.respawnTime = spawnTime;
        this.droppable = new HashMap<>();
        this.base = new BaseTemplate(name);
    }

    @Override
    public void statusJustification() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getAtkStyle() {
        return attackStyle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return String.format("\n名字: %s %s", this.name, this.base);
    }

    @Override
    public String saveAfterBattle(Double HP) {
        this.base.getBattleStats().setHP(HP);
        return "SUCCESS OPERATION";
    }
}
