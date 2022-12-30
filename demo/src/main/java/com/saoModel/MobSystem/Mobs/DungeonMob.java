package com.saoModel.MobSystem.Mobs;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.MapSystem.Stage;
import com.saoModel.StatusSystem.BaseTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "DungenMob")
public class DungeonMob implements MobTemplate {
    @Id
    private final int id;
    private final String name;
    private BaseTemplate base;
    private HashMap<Integer, ItemElement> dropList;
    private int respawnTime;
    private String attackStyle;
    private String hidAttackStyle;
    private Stage stage;

    public DungeonMob(int id, String name, int spawnTime,
            HashMap<Integer, ItemElement> droppable) {
        this.id = id;
        this.name = name;
        // this.attackStyle = atkStlye;
        // this.hidAttackStyle = hidAtkStyle;
        this.dropList = droppable;
        this.respawnTime = spawnTime;
        this.base = new BaseTemplate(name);
    }

    public DungeonMob(int id, String name, int spawnTime, String attackStyle, String hidAtkStlye) {
        this.id = id;
        this.name = name;
        this.respawnTime = spawnTime;
        this.attackStyle = attackStyle;
        this.hidAttackStyle = hidAtkStlye;
        this.dropList = new HashMap<>();
        this.base = new BaseTemplate(name);
    }

    @Override
    public String getAtkStyle() {
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

    @Override
    public String toString() {
        return String.format("\n id: %d, %s %s", this.id, this.name, this.base);
    }

    @Override
    public void statusJustification() {
        // TODO Auto-generated method stub

    }

    @Override
    public String saveAfterBattle(Double HP) {
        this.base.getBattleStats().setHP(HP);
        return "SUCCESS OPERATION";
    }

    @Override
    public String removeMob() {
        if (stage.getMobs().contains(this)) {
            stage.removeMob(this);
            return "SUCCESS OPERATION";
        }
        return "CANNOT BE FOUND";
    }

}
