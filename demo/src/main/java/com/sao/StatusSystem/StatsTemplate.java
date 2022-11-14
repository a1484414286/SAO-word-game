package com.sao.StatusSystem;

import java.util.Random;

public class StatsTemplate {
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

    // public StatsTemplate(int LVL, int HP, int MP) {
    // this.EXP = 0;
    // this.HP = HP;
    // this.MP = MP;
    // this.LVL = LVL;
    // this.STR = new Random().nextInt(10);
    // this.AGI = new Random().nextInt(10);
    // this.VIT = new Random().nextInt(10);
    // this.INT = new Random().nextInt(10);
    // this.DEX = new Random().nextInt(LVL, 100);
    // this.LUK = new Random().nextInt(LVL, 100);
    // }

    public StatsTemplate() {
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

    public int getLVL() {
        return LVL;
    }

    public long getReqEXP() {
        return reqEXP;
    }

    public int getAGI() {
        return AGI;
    }

    public int getDEX() {
        return DEX;
    }

    public long getEXP() {
        return EXP;
    }

    public int getHP() {
        return HP;
    }

    public int getINT() {
        return INT;
    }

    public int getLUK() {
        return LUK;
    }

    public int getMP() {
        return MP;
    }

    public int getSTR() {
        return STR;
    }

    public int getVIT() {
        return VIT;
    }

    public void setHP(int hP) {
        HP = hP;
    }

    @Override
    public String toString() {
        String r = String.format(
                "\n HP  : %d  MP  : %d \n STR : %d   AGI : %d \n VIT : %d   INT : %d \n DEX : %d   EXP : %d", HP, MP,
                STR, AGI, VIT, INT, DEX, EXP);
        return r;
    }
}
