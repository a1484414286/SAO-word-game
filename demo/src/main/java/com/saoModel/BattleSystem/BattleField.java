package com.saoModel.BattleSystem;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.saoModel.MapSystem.Stage;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoModel.PlayerSystem.Player;

public class BattleField {
    private Stage stage;
    private Player Attacker;
    private MobTemplate Defender;
    private HashMap<String, HashMap<String, Double>> algo;
    private HashMap<String, BattleLog> logger;
    public Random rand;

    public BattleField() {
        algo = new HashMap<>();
        HashMap<String, Double> scissorHash = new HashMap<>();
        scissorHash.put("石头", 0.7);
        scissorHash.put("剪刀", 1.0);
        scissorHash.put("布", 1.3);

        HashMap<String, Double> rockHash = new HashMap<>();
        rockHash.put("布", 0.7);
        rockHash.put("石头", 1.0);
        rockHash.put("剪刀", 1.3);

        HashMap<String, Double> paperHash = new HashMap<>();
        paperHash.put("剪刀", 0.7);
        paperHash.put("布", 1.0);
        paperHash.put("石头", 1.3);

        algo.put("剪刀", scissorHash);
        algo.put("石头", rockHash);
        algo.put("布", paperHash);

        HashMap<String, Double> regAtk = new HashMap<>();
        regAtk.put("剪刀", 0.7);
        regAtk.put("布", 0.7);
        regAtk.put("石头", 0.7);

        algo.put("攻击", regAtk);
        algo.put("攻击", regAtk);
        algo.put("攻击", regAtk);

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

    public boolean battleEndCheck() {
        return true;
    }

    public String battleLog(Double effective, Double dmgDealt, Double remainingHP) {
        String result = "";
        String effectiveness = "";
        result += Defender.getBase().battleLogString();
        if (effective.equals(0.7)) {
            effectiveness += "\n    Ineffective ";
        } else if (effective.equals(1.2)) {
            effectiveness += "\n    Super effective !!!";
        } else if (effective.equals(1.0)) {
            effectiveness += "\n    Neutral ";
        }
        result += effectiveness + "\n you have dealt " + dmgDealt + " damage to "
                + Defender.getBase().getName();
        return result;
    }

    public HashMap<String, BattleLog> battle(String urStyle) {
        double sumHP1 = 0;
        double HP1 = Attacker.getBase().getBattleHP();
        double ATK1 = Attacker.getBase().getBattleSTR();

        double sumHP2 = 0;
        double HP2 = Defender.getBase().getBattleHP();
        double ATK2 = Defender.getBase().getBattleSTR();

        String oppAttackStyle = Defender.getAtkStyle();

        rand = new Random();
        // u attack opp
        Double uAttack = algo.get(urStyle).get(oppAttackStyle);
        Double dmgDealt1 = (double) Math.round(((ATK1 * uAttack) / 3)) * 100.0 / 100.0;
        dmgDealt1 = (double) Math
                .round((ThreadLocalRandom.current().nextDouble(dmgDealt1, dmgDealt1 + 2)) * 100.0 / 100.0);

        sumHP2 = HP2 - dmgDealt1;
        System.out.println("_________________________________");
        rand = new Random();

        Double oppAttack = algo.get(oppAttackStyle).get(urStyle);
        Double dmgDealt2 = (Double) ((ATK2 * oppAttack) / 3);
        dmgDealt2 = (double) Math
                .round(ThreadLocalRandom.current().nextDouble(dmgDealt2, dmgDealt2 + 2) * 100.0 / 100.0);
        System.out.println(dmgDealt2);
        // dmgDealt2 = rand.nextInt(dmgDealt2) + dmgDealt2 + 1;
        sumHP1 = HP1 - dmgDealt2;
        String selfLog = battleLog(uAttack, dmgDealt1, sumHP2);
        String oppLog = battleLog(oppAttack, dmgDealt2, sumHP1);
        logger.put("Atk", new BattleLog(sumHP1, selfLog));
        logger.put("Def", new BattleLog(sumHP2, oppLog));
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

        System.out.println(field.battle("剪刀").get("Atk"));
        Double sumHp = field.battle("剪刀").get("Def").getDamageDealt();
        m1.saveAfterBattle(sumHp);
        System.out.println(m1.getBase().battleLogString());

        // System.out.println(field.battle("剪刀").get("Atk"));
        // int sumHp1 = field.battle("剪刀").get("Def").getDamageDealt();
        // m1.saveAfterBattle(sumHp1);
        // System.out.println(m1.getBase().battleLogString());

        // System.out.println(field.battle("剪刀").get("Atk"));
        // int sumHp2 = field.battle("剪刀").get("Def").getDamageDealt();
        // m1.saveAfterBattle(sumHp2);
        // System.out.println(m1.getBase().battleLogString());

    }
}
