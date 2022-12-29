package com.saoModel.StatusSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BattleStats {
    public static String SUCCESS = "SUCCESS OPERATION";
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

    public String battleSave(Double HP, Double MP, int EXP) {
        this.HP = HP;
        this.MP = MP;
        this.EXP = EXP;
        return SUCCESS;
    }

    @Override
    public String toString() {
        String r = String.format(
                "\n HP : %.2f MP : %.2f \n STR : %.2f AGI : %.2f \n VIT : %.2f INT : %.2f \n DEX : %.2f EXP: %d", HP,
                MP,
                STR, AGI, VIT, INT, DEX, EXP);
        return r;
    }

}
