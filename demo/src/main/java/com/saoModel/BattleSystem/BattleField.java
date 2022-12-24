package com.saoModel.BattleSystem;

import java.util.HashMap;

import com.saoModel.MapSystem.Stage;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoModel.PlayerSystem.Player;

public class BattleField {
    private Stage stage;
    private Player Attacker;
    private MobTemplate Defender;
    private HashMap<String, HashMap<String, Double>> algo;
    private HashMap<String, Object> logger;

    public BattleField() {
        algo = new HashMap<>();
        HashMap<String, Double> scissorHash = new HashMap<>();
        scissorHash.put("石头", 0.7);
        scissorHash.put("剪刀", 1.0);
        scissorHash.put("布", 1.2);

        HashMap<String, Double> rockHash = new HashMap<>();
        rockHash.put("布", 0.7);
        rockHash.put("石头", 1.0);
        rockHash.put("剪刀", 1.2);

        HashMap<String, Double> paperHash = new HashMap<>();
        paperHash.put("剪刀", 0.7);
        paperHash.put("布", 1.0);
        paperHash.put("石头", 1.2);

        algo.put("剪刀", scissorHash);
        algo.put("石头", rockHash);
        algo.put("布", paperHash);

        logger = new HashMap<>();
        logger.put("Atk", null);
        logger.put("Def", null);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void provokeBattle(Player p1, MobTemplate mob) {
        this.Attacker = p1;
        this.Defender = mob;
    }

    public void provokeBattle(Player p1, Player p2) {
        // todo
    }

    public void Notify(MobTemplate mob) {
        // todo
    }

    public void Notify(Player p1, MobTemplate mob) {
        // todo
    }

    public String battleLog(Double effective, Double dmgDealth, Double remainingHP) {
        String result = "";
        result += Attacker.getBaseTemplate().battleLogString() + " has dealt " + dmgDealth + " to "
                + Defender.getStats().battleLogString();
        if (effective.equals(0.7)) {
            result += " Ineffective attack " + remainingHP;
        } else if (effective.equals(1.2)) {
            result += " Super effective attack " + remainingHP;
        } else if (effective.equals(1.0)) {
            result += " Neutral attack " + remainingHP;
        }
        return result;
    }

    public HashMap<String, Object> battle(String urStyle) {
        double sumHP1 = 0;
        int HP1 = Attacker.getStats().getHP();
        int ATK1 = Attacker.getStats().getSTR();

        double sumHP2 = 0;
        int HP2 = Defender.getStats().getStats().getHP();
        int ATK2 = Defender.getStats().getStats().getSTR();

        String oppAttackStyle = Defender.getAtkSyle();

        // u attack opp
        Double uAttack = algo.get(urStyle).get(oppAttackStyle);
        double dmgDealt1 = ((ATK1 * uAttack) / 3);
        sumHP2 = HP2 - dmgDealt1;

        // opp attack u
        Double oppAttack = algo.get(oppAttackStyle).get(urStyle);
        double dmgDealt2 = ((ATK2 * oppAttack) / 3);
        sumHP1 = HP1 - dmgDealt2;

        String selfLog = battleLog(uAttack, dmgDealt1, sumHP2);
        String oppoLog = battleLog(oppAttack, dmgDealt2, sumHP1);
        logger.put("Atk", selfLog);
        logger.put("Def", oppoLog);

        return logger;
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        Player p1 = new Player(0, "吴针");
        RegMob m1 = new RegMob(0, "野猪", 5, new HashMap<>());
        BattleField field = new BattleField();
        field.provokeBattle(p1, m1);
        // System.out.println(field.battle("剪刀")[0]);
        // System.out.println(field.battle("剪刀")[1]);

    }
}
