package com.saoModel.MapSystem;

import java.util.ArrayList;

import com.google.gwt.dev.util.collect.HashMap;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.PlayerSystem.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stage {
    private final int id;
    private final String name;
    private HashMap<> neighbors;
    private ArrayList<MobTemplate> mobs;
    private ArrayList<Player> players;

    public Stage(int id, String name) {
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
        this.mobs = new ArrayList<>();
    }
}
