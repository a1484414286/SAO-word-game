package com.sao.PlayerSystem;

import com.sao.BagsSystem.GeneralBag;
import com.sao.StatusSystem.BaseTemplate;

public class Player {
    private String name;
    private BaseTemplate stats;
    private GeneralBag bag;
    
    public Player(int id, String name)
    {
        this.name = name;
        this.stats = new BaseTemplate(id, name);
        this.bag = new GeneralBag();
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
