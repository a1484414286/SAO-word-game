package com.saoModel.BattleSystem;

import com.saoModel.MapSystem.LevelStage;
import com.saoModel.StatusSystem.StatsTemplate;

public class BattleField {
    public LevelStage stage;
    public StatsTemplate p1;
    public StatsTemplate p2;

    public BattleField(LevelStage stage, StatsTemplate p1, StatsTemplate p2) {
        this.stage = stage;
        this.p1 = p1;
        this.p2 = p2;
    }

    public BattleField(StatsTemplate p1, StatsTemplate p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setStage(LevelStage stage) {
        this.stage = stage;
    }

    public void setP1(StatsTemplate p1) {
        this.p1 = p1;
    }

    public void setP2(StatsTemplate p2) {
        this.p2 = p2;
    }

    public StatsTemplate getP1() {
        return p1;
    }

    public StatsTemplate getP2() {
        return p2;
    }

    public void battle() {
        int sum = 0;
        p1.getHP();
        p2.getHP();
        sum = p1.getHP() - p2.getSTR() / 4;
        p1.setHP(sum);
    }

    public static void main(String[] args) {

        int str = 8;
        int HP = 10;
        System.out.println(HP - str / 4);
    }
}
