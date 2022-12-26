package com.saoModel.StatusSystem;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {
    private Double HP;
    private Double MP;
    private Double STR;
    private Double AGI;
    private Double VIT;
    private Double INT;
    private Double DEX;
    private Double LUK;
    private int LVL;
    private int EXP;
    private int reqEXP;

    public Stats() {
        this.HP = 10.0;
        this.MP = 10.0;
        this.EXP = 0;
        this.LVL = 1;
        this.reqEXP = 50;
        this.STR = (double) (new Random().nextInt(11) + 1);
        this.AGI = (double) (new Random().nextInt(11) + 1);
        this.VIT = (double) (new Random().nextInt(11) + 1);
        this.INT = (double) (new Random().nextInt(11) + 1);
        this.DEX = (double) (new Random().nextInt(11) + 1);
        this.LUK = (double) (new Random().nextInt(11) + 1);
    }

    @Override
    public String toString() {
        String r = String.format(
                "\n HP  : %d  MP  : %d \n STR : %d   AGI : %d \n VIT : %d   INT : %d \n DEX : %d   EXP : %d", HP, MP,
                STR, AGI, VIT, INT, DEX, EXP);
        return r;
    }

}
