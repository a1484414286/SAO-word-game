package com.saoModel.BattleSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattleLog {
    private double damageDealt;
    private String battleResult;

    public BattleLog(Double dmgDealt, String battleResult) {
        this.damageDealt = dmgDealt;
        this.battleResult = battleResult;
    }

    @Override
    public String toString() {
        return this.battleResult;
    }

}
