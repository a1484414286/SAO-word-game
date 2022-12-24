package com.saoModel.StatusSystem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class BattleStats {
    private int HP;
    private int MP;
    private int STR;
    private int AGI;
    private int VIT;
    private int INT;
    private int DEX;
    private int LUK;
    private int EXP;

    // public BattleStats(int HP, int MP, int STR, int AGI, int VIT, int INT, int
    // DEX, int LUK) {
    // this.HP = HP;
    // this.MP = MP;
    // this.STR = STR;
    // this.AGI = AGI;
    // this.VIT = VIT;
    // this.INT = INT;
    // this.DEX = DEX;
    // this.LUK = LUK;
    // this.EXP = 0;
    // }

    public BattleStats(Stats stat) {
        this.HP = stat.getHP();
        this.MP = stat.getMP();
        this.STR = stat.getSTR();
        this.AGI = stat.getAGI();
        this.VIT = stat.getVIT();
        this.INT = stat.getINT();
        this.DEX = stat.getDEX();
        this.LUK = stat.getLUK();
        this.EXP = 0;
    }
    // @Override
    // public String toString() {
    // String r = String.format(
    // "\n HP : %d MP : %d \n STR : %d AGI : %d \n VIT : %d INT : %d \n DEX : %d
    // EXP: %d", HP, MP,
    // STR, AGI, VIT, INT, DEX, EXP);
    // return r;
    // }

    // public static void main(String[] args) {
    // Player p = new Player(0, "10");
    // System.out.println(p.getStats());
    // System.out.println(p.getBattleStats());

    // p.getBattleStats().setHP(9);
    // p.getStats().setHP(8);
    // System.out.println("__________________________");
    // System.out.println(p.getStats());
    // System.out.println(p.getBattleStats());
    // // p.getStats();
    // // p.getBaseTemplate().getStats();
    // }
}
