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
@Document(collection = "EliteMob")
public class EliteMob implements MobTemplate {
    @Id
    private final int id;
    private final String name;
    private BaseTemplate base;
    private HashMap<Integer, ItemElement> dropList;
    private long respawnTime;
    private String attackStyle;
    private String hidAttackStyle;
    private Stage stage;

    public EliteMob(int id, String name, int spawnTime, HashMap<Integer, ItemElement> droppable) {
        this.id = id;
        this.name = name;
        this.dropList = droppable;
        this.respawnTime = spawnTime;
        this.base = new BaseTemplate(name);
    }

    public EliteMob(int id, String name, int spawnTime, String atkStyle) {
        this.id = id;
        this.name = name;
        this.respawnTime = spawnTime;
        this.attackStyle = atkStyle;
        this.dropList = new HashMap<>();
        this.base = new BaseTemplate(name);

    }

    @Override
    public String getAtkStyle() {
        return attackStyle;
    }

    @Override
    public String toString() {
        return String.format("\n id: %d,  %s %s", this.id, this.name, this.base);
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
