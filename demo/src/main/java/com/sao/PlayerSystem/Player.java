package com.sao.PlayerSystem;

import com.sao.BagsSystem.GeneralBag;
import com.sao.StatusSystem.BaseTemplate;

public class Player {
    private String name;
    private BaseTemplate stats;
    private GeneralBag bag;
    private int id;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.stats = new BaseTemplate(id, name);
        this.bag = new GeneralBag();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BaseTemplate getStats() {
        return stats;
    }

    public GeneralBag getBag() {
        return bag;
    }

}
