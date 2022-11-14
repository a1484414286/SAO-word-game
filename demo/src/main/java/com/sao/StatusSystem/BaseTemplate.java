package com.sao.StatusSystem;

import java.util.HashMap;

public class BaseTemplate {
    private final long id;
    private final String name;
    private StatsTemplate stats;
    private WeaponResistantTemplate weaponResist;

    public BaseTemplate(long id, String name) {
        this.id = id;
        this.name = name;
        this.stats = new StatsTemplate();
        this.weaponResist = new WeaponResistantTemplate();
    }

    public long getId() {
        return id;
    }

    public StatsTemplate getStats() {
        return stats;
    }

    public WeaponResistantTemplate getWeaponResist() {
        return weaponResist;
    }

    public HashMap<String, Object> getDataFromStats() {
        return this.stats.getData();
    }

    @Override
    public String toString() {
        return String.format(
                "名字 : %s  等级 : %s \n HP  : %d    MP  : %d  \n STR : %d     AGI : %d  \n VIT : %d     INT : %d \n DEX : %d     EXP : %d",
                this.name, stats.getLVL(), stats.getHP(), stats.getMP(), stats.getSTR(), stats.getAGI(), stats.getVIT(),
                stats.getINT(), stats.getDEX(), stats.getEXP());
    }
}
