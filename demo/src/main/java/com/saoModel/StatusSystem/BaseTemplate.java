package com.saoModel.StatusSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTemplate {
    private final String name;
    private Stats stats;
    private BattleStats battleStats;
    private WeaponResistantTemplate weaponResist;

    public BaseTemplate(String name) {
        this.name = name;
        this.stats = new Stats();
        this.battleStats = new BattleStats(stats);
        this.weaponResist = new WeaponResistantTemplate();
    }

    public Stats getStats() {
        return stats;
    }

    public WeaponResistantTemplate getWeaponResist() {
        return weaponResist;
    }

    public String battleLogString() {
        return String.format(
                "名字 : %s  等级 : %s \n HP  : %d    MP  : %d  ",
                this.name, stats.getLVL(), stats.getHP(), stats.getMP());
    }

    public String battleMobString() {
        return String.format(
                "名字 : %s  等级 : %s \n HP  : %d    MP  : %d  ",
                this.name, stats.getLVL(), stats.getHP(), stats.getMP());

    }

    @Override
    public String toString() {
        return String.format(
                "名字 : %s  等级 : %s \n HP  : %d    MP  : %d  \n STR : %d     AGI : %d  \n VIT : %d     INT : %d \n DEX : %d     EXP : %d",
                this.name, stats.getLVL(), stats.getHP(), stats.getMP(), stats.getSTR(), stats.getAGI(), stats.getVIT(),
                stats.getINT(), stats.getDEX(), stats.getEXP());
    }

}
