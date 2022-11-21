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

    public void setCritResist(int critResist) {
        this.critResist = critResist;
    }

    public void setPenetrateResist(int penetrateResist) {
        this.penetrateResist = penetrateResist;
    }

    public void setSlashResist(int slashResist) {
        this.slashResist = slashResist;
    }

    public void setSmashResist(int smashResist) {
        this.smashResist = smashResist;
    }

}
