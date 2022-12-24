package com.saoModel.StatusSystem;

public class BaseTemplate {
    private final String name;
    private StatsTemplate stats;
    private WeaponResistantTemplate weaponResist;

    public BaseTemplate(String name) {
        this.name = name;
        this.stats = new StatsTemplate(name);
        this.weaponResist = new WeaponResistantTemplate();
    }

    public StatsTemplate getStats() {
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

    @Override
    public String toString() {
        return String.format(
                "名字 : %s  等级 : %s \n HP  : %d    MP  : %d  \n STR : %d     AGI : %d  \n VIT : %d     INT : %d \n DEX : %d     EXP : %d",
                this.name, stats.getLVL(), stats.getHP(), stats.getMP(), stats.getSTR(), stats.getAGI(), stats.getVIT(),
                stats.getINT(), stats.getDEX(), stats.getEXP());
    }

}
