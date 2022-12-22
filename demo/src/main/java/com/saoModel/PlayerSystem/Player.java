package com.saoModel.PlayerSystem;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.BagsSystem.GeneralBag;
import com.saoModel.MapSystem.Direction;
import com.saoModel.MapSystem.Map;
import com.saoModel.MapSystem.Stage;
import com.saoModel.StatusSystem.BaseTemplate;
import com.saoModel.StatusSystem.StatsTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@Document(collection = "Players")

public class Player {
    public static String SUCCESS = "SUCESS";
    public static String NO_ROAD = "No Road Ahead";
    public Map currentMap;
    @Id
    private int id;
    private String name;
    private Stage position;
    private BaseTemplate stats;
    private GeneralBag baggage;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.stats = new BaseTemplate(name);
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

    public BaseTemplate getBaseTemplate() {
        return stats;
    }

    public StatsTemplate getStats() {
        return stats.getStats();
    }

    public GeneralBag getBaggage() {
        return baggage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    // this will either return NO_ROAD indicating theres no road ahead, or
    // it will return the saved data of a player, including all the changes
    // it has been made
    public ArrayList<Object> moveToNextStage(String direction) {
        ArrayList<Object> result = new ArrayList<>();
        if (direction.equals("左") || direction.equals("←")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                result.add(NO_ROAD);
                return result;
            }
            // next stage where we're moving
            Stage currStage = this.position;
            Stage nextStage = this.position.getNeighbor(Direction.WEST);

            // remove player from current stage
            this.position.removePlayer(this);

            // set current posistion to be next stage
            this.setPosition(nextStage);
            this.position.addPlayer(this);

            // save the changes between stages into the original map
            this.currentMap.getFloor().set(currStage.getId(), currStage);
            this.currentMap.getFloor().set(nextStage.getId(), nextStage);
            result.add(this);
            return result;

        } else if (direction.equals("右") || direction.equals("→")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                result.add(NO_ROAD);
                return result;
            }
            Stage nextStage = this.position.getNeighbor(Direction.EAST);
            this.position.removePlayer(this);
            this.setPosition(nextStage);
            this.position.addPlayer(this);
            this.currentMap.getFloor().set(nextStage.getId(), nextStage);
            result.add(this);
            return result;

        } else if (direction.equals("下") || direction.equals("↓")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                result.add(NO_ROAD);
                return result;
            }
            Stage nextStage = this.position.getNeighbor(Direction.SOUTH);
            this.position.removePlayer(this);
            this.setPosition(nextStage);
            this.position.addPlayer(this);
            this.currentMap.getFloor().set(nextStage.getId(), nextStage);
            result.add(this);
            return result;

        } else if (direction.equals("上") || direction.equals("↑")) {
            if (this.position.getNeighbor(Direction.WEST) == null) {
                result.add(NO_ROAD);
                return result;
            }
            Stage nextStage = this.position.getNeighbor(Direction.NORTH);
            this.position.removePlayer(this);
            this.setPosition(nextStage);
            this.position.addPlayer(this);
            this.currentMap.getFloor().set(nextStage.getId(), nextStage);
            result.add(this);
            return result;
        }
        result.add(NO_ROAD);
        return result;
    }
}
