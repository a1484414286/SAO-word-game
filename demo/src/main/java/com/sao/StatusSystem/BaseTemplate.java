package com.sao.StatusSystem;


public class BaseTemplate {
    private final long id;
    private final String name;
    private StatsTemplate stats;
    private WeaponResistantTemplate weaponResist;

    public BaseTemplate(long id, String name)
    {
        this.id = id;
        this.name = name;
        stats = new StatsTemplate();
        weaponResist = new WeaponResistantTemplate();
    }
    public long getId() {
        return id;
    }
    public StatsTemplate getStats() {
        return stats;
    }
    @Override
    public String toString() {
        return
        String.format("名字 : %s \n HP  : %d    暴击抗性 : %d \n MP  : %d    斩击抗性 : %d \n STR : %d     钝击抗性 : %d \n AGI : %d     穿透抗性 : %d \n VIT : %d \n INT : %d \n DEX : %d \n EXP : %d", 
        this.name , stats.getHP(), weaponResist.getCritResist(), stats.getMP(), weaponResist.getSlashResist(),stats.getSTR(),
        weaponResist.getSmashResist() ,stats.getAGI(), weaponResist.getPenetrateResist(),stats.getVIT(), stats.getINT(), stats.getDEX(),stats.getEXP());
    }
}
