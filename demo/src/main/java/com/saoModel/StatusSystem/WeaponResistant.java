package com.saoModel.StatusSystem;

public class WeaponResistant {
    private int critResist;
    private int slashResist;
    private int smashResist;
    private int penetrateResist;

    public WeaponResistant() {
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
