package com.saoModel.StatusSystem;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsTemplate {
    private String name;
    private int HP;
    private int MP;
    private int STR;
    private int AGI;
    private int VIT;
    private int INT;
    private int DEX;
    private int LUK;
    private int LVL;
    private long EXP;
    private long reqEXP;

    public StatsTemplate(String name) {
        this.name = name;
        this.HP = 10;
        this.MP = 10;
        this.EXP = 0;
        this.LVL = 1;
        this.reqEXP = 50;
        this.STR = new Random().nextInt(11);
        this.AGI = new Random().nextInt(11);
        this.VIT = new Random().nextInt(11);
        this.INT = new Random().nextInt(11);
        this.DEX = new Random().nextInt(11);
        this.LUK = new Random().nextInt(11);
    }

    @Override
    public String toString() {
        String r = String.format(
                "\n HP  : %d  MP  : %d \n STR : %d   AGI : %d \n VIT : %d   INT : %d \n DEX : %d   EXP : %d", HP, MP,
                STR, AGI, VIT, INT, DEX, EXP);
        return r;
    }

}
