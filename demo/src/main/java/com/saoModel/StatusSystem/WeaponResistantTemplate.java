package com.saoModel.StatusSystem;

public class WeaponResistantTemplate {
    private int critResist;
    private int slashResist;
    private int smashResist;
    private int penetrateResist;

    public WeaponResistantTemplate() {
        this.critResist = 0;
        this.slashResist = 0;
        this.smashResist = 0;
        this.penetrateResist = 0;
    }

    public int getCritResist() {
        return critResist;
    }

    public int getPenetrateResist() {
        return penetrateResist;
    }

    public int getSlashResist() {
        return slashResist;
    }

    public int getSmashResist() {
        return smashResist;
    }
}
