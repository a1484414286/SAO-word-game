package com.sao.StatusSystem;

import java.util.Random;

public class StatsTemplate {
    private int HP;
    private int MP;
    private long EXP;
    private int STR;
    private int AGI;
    private int VIT;
    private int INT;
    private int DEX;
    private int LUK;

    public StatsTemplate()
    {
        this.HP = 10;
        this.MP = 10;
        this.EXP = 0;
        this.STR = new Random().nextInt(1,11);
        this.AGI = new Random().nextInt(1,11);
        this.VIT = new Random().nextInt(1,11);
        this.INT = new Random().nextInt(1,11);
        this.DEX = new Random().nextInt(1,11);
        this.LUK = new Random().nextInt(1,11);
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

    @Override
    public String toString() {
        String r = String.format("\n HP  : %d \n MP  : %d \n STR : %d \n AGI : %d \n VIT : %d \n INT : %d \n DEX : %d \n EXP : %d", HP, MP,STR,AGI,VIT,INT,DEX,EXP);
        return r;
    }
}
