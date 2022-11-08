package com.sao.BattleSystem;

import com.sao.MapSystem.LevelStage;
import com.sao.StatusSystem.StatsTemplate;

public class BattleField {
    public LevelStage stage;
    public StatsTemplate p1;
    public StatsTemplate p2;

    public BattleField(StatsTemplate p1, StatsTemplate p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setP1(StatsTemplate p1) {
        this.p1 = p1;
    }

    public void setP2(StatsTemplate p2) {
        this.p2 = p2;
    }

    public void battle()
    {  
        System.out.println(p2.getSTR() - p1.getHP());
    }
}
