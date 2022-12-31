package com.saoModel.BattleSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.ibm.icu.text.DecimalFormat;
import com.saoModel.MapSystem.Stage;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoModel.PlayerSystem.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BattleField {
    private Stage stage;
    private Player Attacker;
    private MobTemplate Defender;
    private HashMap<String, HashMap<String, Double>> algo;
    private HashMap<String, ArrayList<Object>> logger;
    public Random rand;

    public BattleField() {
        algo = new HashMap<>();
        HashMap<String, Double> scissorHash = new HashMap<>();
        scissorHash.put("Rock", 0.7);
        scissorHash.put("Scissor", 1.0);
        scissorHash.put("Paper", 1.3);
        scissorHash.put("Attack", 1.0);

        HashMap<String, Double> rockHash = new HashMap<>();
        rockHash.put("Paper", 0.7);
        rockHash.put("Rock", 1.0);
        rockHash.put("Scissor", 1.3);
        rockHash.put("Attack", 1.0);

        HashMap<String, Double> paperHash = new HashMap<>();
        paperHash.put("Scissor", 0.7);
        paperHash.put("Paper", 1.0);
        paperHash.put("Rock", 1.3);
        paperHash.put("Attack", 1.0);

        algo.put("Scissor", scissorHash);
        algo.put("Rock", rockHash);
        algo.put("Paper", paperHash);

        HashMap<String, Double> regAtk = new HashMap<>();
        regAtk.put("Scissor", 0.7);
        regAtk.put("Paper", 0.7);
        regAtk.put("Rock", 0.7);
        regAtk.put("Attack", 0.7);

        algo.put("Attack", regAtk);

        logger = new HashMap<>();
        logger.put("Attacker", null);
        logger.put("Defender", null);
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

    public String battleLog(Double effective, Double dmgDealt, Double remainingHP, Object target) {
        String result = "";
        if (target instanceof MobTemplate) {
            String effectiveness = "";
            result += Defender.getBase().battleLogString();
            if (effective.equals(0.7)) {
                effectiveness += "\n    Ineffective ";
            } else if (effective.equals(1.3)) {
                effectiveness += "\n    Super effective !!!";
            } else if (effective.equals(1.0)) {
                effectiveness += "\n    Neutral ";
            }
            result += effectiveness + "\n you have dealt " + dmgDealt + " damage to "
                    + Defender.getBase().getName() + "\n \n \n";
        } else if (target instanceof Player) {
            String effectiveness = "";
            result += Attacker.getBase().battleLogString();
            if (effective.equals(0.7)) {
                effectiveness += "\n    Ineffective ";
            } else if (effective.equals(1.3)) {
                effectiveness += "\n    Super effective !!!";
            } else if (effective.equals(1.0)) {
                effectiveness += "\n    Neutral ";
            }
            result += effectiveness + "\n " + Defender.getBase().getName() + " have dealt " + dmgDealt
                    + " damage to "
                    + Attacker.getBase().getName() + "\n \n \n";
        }

        return result;

    }

    public HashMap<String, ArrayList<Object>> battle(String urStyle) {
        String attackerLog = "";
        String defenderLog = "";
        double selfSumHP = 0;
        double selfHP = Attacker.getBase().getBattleHP();
        double selfATK = Attacker.getBase().getBattleSTR();

        double oppSumHP = 0;
        double oppHP = Defender.getBase().getBattleHP();
        double oppATK = Defender.getBase().getBattleSTR();

        ArrayList<Object> playerArr;
        ArrayList<Object> mobArr;

        String oppAttackStyle = Defender.getAtkStyle();
        DecimalFormat df = new DecimalFormat("0.00");
        rand = new Random();
        // u attack opp
        Double selfAttackRatio = algo.get(urStyle).get(oppAttackStyle);

        Double dmgDealingToMob = ((selfATK * selfAttackRatio) / 3) * 100.0 / 100.0;
        dmgDealingToMob = Double.parseDouble(
                df.format(ThreadLocalRandom.current().nextDouble(dmgDealingToMob, dmgDealingToMob + 2)));

        oppSumHP = oppHP - dmgDealingToMob;

        rand = new Random();
        Double oppAttackRatio = algo.get(oppAttackStyle).get(urStyle);
        Double dmgDealingToPlayer = (Double) ((oppATK * oppAttackRatio) / 3);
        dmgDealingToPlayer = Double.parseDouble(
                df.format(ThreadLocalRandom.current().nextDouble(dmgDealingToPlayer, dmgDealingToPlayer + 2)));
        // dmgDealt2 = rand.nextInt(dmgDealt2) + dmgDealt2 + 1;
        selfSumHP = selfHP - dmgDealingToPlayer;

        attackerLog = battleLog(oppAttackRatio, dmgDealingToPlayer, selfSumHP, Attacker);
        defenderLog = battleLog(selfAttackRatio, dmgDealingToMob, oppSumHP, Defender);
        playerArr = new ArrayList<>();
        mobArr = new ArrayList<>();

        logger.put("Attacker", playerArr);
        logger.put("Defender", mobArr);

        Defender.getBase().saveAfterBattle(oppSumHP);
        Attacker.getBase().saveAfterBattle(selfSumHP);
        playerArr.add(Attacker);
        playerArr.add(new BattleLog(selfSumHP, attackerLog));

        mobArr.add(Defender);
        mobArr.add(new BattleLog(oppSumHP, defenderLog));
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

        // the calculation
        ArrayList<Object> mapper1 = field.battle("Scissor").get("Attacker");
        System.out.println(mapper1);
        System.out.println("————————————————————————————————————————————————————————————————————————————————————");
        ArrayList<Object> mapper2 = field.battle("Scissor").get("Attacker");
        System.out.println(mapper2);
        System.out.println("————————————————————————————————————————————————————————————————————————————————————");

    }
}
