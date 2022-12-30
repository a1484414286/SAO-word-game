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
    public static HashMap<String, Direction> direction = new HashMap<>();
    public static HashMap<String, Direction> oppDirection = new HashMap<>();

    public static Direction NORTH = Direction.NORTH;
    public static Direction EAST = Direction.EAST;
    public static Direction WEST = Direction.WEST;
    public static Direction SOUTH = Direction.SOUTH;

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

        direction.put("NORTH", NORTH);
        direction.put("SOUTH", SOUTH);
        direction.put("EAST", EAST);
        direction.put("WEST", WEST);
        oppDirection.put("NORTH", SOUTH);
        oppDirection.put("SOUTH", NORTH);
        oppDirection.put("EAST", WEST);
        oppDirection.put("WEST", EAST);
    }

    // doubly linked structure of adding
    // 双方位的区域添加, 如草原的北是沙漠，沙漠的南是草原
    public void addNeighbors(String dir, Stage stage) {
        neighbors.put(direction.get(dir), stage);
        stage.neighbors.put(oppDirection.get(dir), this);
    }

    // 查看当前地图的情况
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

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public Stage getNeighbor(Direction dir) {
        return this.neighbors.get(dir);
    }

    public void removePlayer(Player p) {
        this.players.remove(p);
    }

    public void removeMob(MobTemplate m) {
        this.mobs.remove(m);
    }

}
