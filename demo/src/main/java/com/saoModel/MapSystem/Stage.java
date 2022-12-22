package com.saoModel.MapSystem;

import java.util.ArrayList;
import java.util.HashMap;

import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.PlayerSystem.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stage {
    public static Object NORTH = Direction.NORTH;
    public static Object EAST = Direction.EAST;
    public static Object WEST = Direction.WEST;
    public static Object SOUTH = Direction.SOUTH;

    private int id;
    private String name;
    private HashMap<Direction, Stage> neighbors;
    private HashMap<Building, Stage> buildings;
    private ArrayList<MobTemplate> mobs;
    private ArrayList<Player> players;

    public Stage() {
    }

    public Stage(int id, String name) {
        this.id = id;
        this.name = name;
        this.mobs = new ArrayList<>();
        this.players = new ArrayList<>();
        this.neighbors = new HashMap<>();
        this.buildings = new HashMap<>();
    }

    private void setNeighbors(Direction direction, Stage stage) {
        neighbors.put(direction, stage);
    }

    public String checkCurrentMap() {
        String str = "";
        for (Direction v : this.neighbors.keySet()) {
            if (v.equals(NORTH))
                str += ("↑" + " " + this.neighbors.get(v));
            if (v.equals(EAST))
                str += ("→" + " " + this.neighbors.get(v));
            if (v.equals(SOUTH))
                str += ("↓" + " " + this.neighbors.get(v));
            if (v.equals(WEST))
                str += ("←" + " " + this.neighbors.get(v));
        }
        return this.name + "\n" + str;
    }

}
