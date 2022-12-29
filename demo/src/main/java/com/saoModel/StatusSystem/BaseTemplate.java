package com.saoModel.StatusSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTemplate {
    private final String name;
    private Stats stats;
    private BattleStats battleStats;
    private WeaponResistant weaponResist;

    public BaseTemplate(String name) {
        this.name = name;
        this.stats = new Stats();
        this.battleStats = new BattleStats(stats);
        this.weaponResist = new WeaponResistant();
    }

    public Stats getStats() {
        return stats;
    }

    public double getBattleHP() {
        return this.battleStats.getHP();
    }

    public double getBattleDEX() {
        return this.battleStats.getDEX();
    }

    public double getBattleAGI() {
        return this.battleStats.getAGI();
    }

    public double getBattleSTR() {
        return this.battleStats.getSTR();
    }

    public double getBattleINT() {
        return this.battleStats.getINT();
    }

    public double getBattleMP() {
        return this.battleStats.getMP();
    }

    public double getBattleLUK() {
        return this.battleStats.getLUK();
    }

    public int getBattleEXP() {
        return this.battleStats.getEXP();
    }

    public WeaponResistant getWeaponResist() {
        return weaponResist;
    }

    public void saveAfterBattle(Double HP) {
        this.battleStats.setHP(HP);
    }

    public String battleLogString() {
        return String.format(
                " 名字 : %s  等级 : %s \n HP  : %.2f    MP  : %.2f  ",
                this.name, battleStats.getLVL(), battleStats.getHP(), battleStats.getMP());
    }

    @Override
    public String toString() {
        return String.format(
                " 名字 : %s 等级 : %s \n HP : %.2f MP : %.2f \n STR : %.2f AGI : %.2f \n VIT : %.2f INT : %.2f \n DEX : %.2f EXP : %f",
                this.name, stats.getLVL(), stats.getHP(), stats.getMP(), stats.getSTR(),
                stats.getAGI(), stats.getVIT(),
                stats.getINT(), stats.getDEX(), stats.getEXP());
    }

}
