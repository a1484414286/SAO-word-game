package com.saoModel.PlayerSystem;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.BagsSystem.GeneralBag;
import com.saoModel.MapSystem.Direction;
import com.saoModel.MapSystem.Map;
import com.saoModel.MapSystem.Stage;
import com.saoModel.StatusSystem.BaseTemplate;
import com.saoModel.StatusSystem.BattleStats;
import com.saoModel.StatusSystem.Stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Players")

public class Player {
    public static String SUCCESS = "SUCCESS OPERATION";
    public static String NO_ROAD = "NO ROAD AHEAD";
    public Map currentMap;
    @Id
    private int id;
    private String name;
    private Stage position;
    private BaseTemplate base;
    private GeneralBag baggage;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.base = new BaseTemplate(name);
        this.baggage = new GeneralBag();
        this.position = null;
        this.currentMap = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return base.getStats();
    }

    public BattleStats getBattleStats() {
        return base.getBattleStats();
    }

    public GeneralBag getBaggage() {
        return baggage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    public Player savedChangePlayerData(Stage nextStage, Stage currentStage) {
        // remove player from current stage
        this.position.removePlayer(this);

        // set current position to be next stage
        this.setPosition(nextStage);
        this.position.addPlayer(this);

        // save the changes between stages into the original map
        this.currentMap.getTile().set(currentStage.getId(), currentStage);
        this.currentMap.getTile().set(nextStage.getId(), nextStage);
        return this;
    }

    // this will either return NO_ROAD indicating theres no road ahead, or
    // it will return the saved data of a player, including all the changes
    // it has been made
    public Object moveToNextStage(String direction) {
        Object result = NO_ROAD;
        if (direction.equals("左") || direction.equals("←")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                return result;
            }
            // next stage where we're moving
            Stage currentStage = this.position;
            Stage nextStage = this.position.getNeighbor(Direction.WEST);

            // remove player from current stage
            Player p = savedChangePlayerData(nextStage, currentStage);
            return p;

        } else if (direction.equals("右") || direction.equals("→")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                return result;
            }
            Stage currentStage = this.position;
            Stage nextStage = this.position.getNeighbor(Direction.EAST);
            Player p = savedChangePlayerData(nextStage, currentStage);
            return p;

        } else if (direction.equals("下") || direction.equals("↓")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                return result;
            }
            // next stage where we're moving
            Stage currentStage = this.position;
            Stage nextStage = this.position.getNeighbor(Direction.SOUTH);
            Player p = savedChangePlayerData(nextStage, currentStage);
            return p;

        } else if (direction.equals("上") || direction.equals("↑")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                return result;
            }
            // next stage where we're moving
            Stage currentStage = this.position;
            Stage nextStage = this.position.getNeighbor(Direction.NORTH);
            Player p = savedChangePlayerData(nextStage, currentStage);
            return p;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
