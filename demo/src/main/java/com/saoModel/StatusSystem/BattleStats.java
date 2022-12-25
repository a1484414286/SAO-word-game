package com.saoModel.StatusSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BattleStats {
    public static String SUCCESS = "SUCCESS OPERATION";
    private int HP;
    private int MP;
    private int LVL;
    private int STR;
    private int AGI;
    private int VIT;
    private int INT;
    private int DEX;
    private int LUK;
    private int EXP;

    public BattleStats(Stats stat) {
        this.HP = stat.getHP();
        this.MP = stat.getMP();
        this.STR = stat.getSTR();
        this.AGI = stat.getAGI();
        this.VIT = stat.getVIT();
        this.INT = stat.getINT();
        this.DEX = stat.getDEX();
        this.LUK = stat.getLUK();
        this.LVL = stat.getLVL();
        this.EXP = 0;
    }

    public String battleSave(int HP, int MP, int EXP) {
        this.HP = HP;
        this.MP = MP;
        this.EXP = EXP;
        return SUCCESS;
    }

    @Override
    public String toString() {
        String r = String.format(
                "\n HP : %d MP : %d \n STR : %d AGI : %d \n VIT : %d INT : %d \n DEX : %d EXP: %d", HP, MP,
                STR, AGI, VIT, INT, DEX, EXP);
        return r;
    }

}
