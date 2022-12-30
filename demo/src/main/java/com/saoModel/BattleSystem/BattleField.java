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
        logger.put("Self", null);
        logger.put("Opponent", null);
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
        } else if (effective.equals(1.3)) {
            effectiveness += "\n    Super effective !!!";
        } else if (effective.equals(1.0)) {
            effectiveness += "\n    Neutral ";
        }
        result += effectiveness + "\n you have dealt " + dmgDealt + " damage to "
                + Defender.getBase().getName() + "\n \n \n";
        return result;
    }

    public HashMap<String, ArrayList<Object>> battle(String urStyle) {
        double selfSumHP = 0;
        double selfHP = Attacker.getBase().getBattleHP();
        double selfATK = Attacker.getBase().getBattleSTR();

        double oppSumHP = 0;
        double oppHP = Defender.getBase().getBattleHP();
        double oppATK = Defender.getBase().getBattleSTR();

        ArrayList<Object> playerArr;
        ArrayList<Object> mobArr;
        if (oppHP != 0 && selfHP != 0) {

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

            String selfLog = battleLog(oppAttackRatio, dmgDealingToPlayer, selfSumHP);
            String oppLog = battleLog(selfAttackRatio, dmgDealingToMob, oppSumHP);
            playerArr = new ArrayList<>();
            mobArr = new ArrayList<>();
            playerArr.add(new BattleLog(selfSumHP, selfLog));
            playerArr.add(Attacker);

            mobArr.add(new BattleLog(oppSumHP, oppLog));
            mobArr.add(Defender);

            logger.put("Self", playerArr);
            logger.put("Opponent", mobArr);

            Defender.getBase().saveAfterBattle(oppSumHP);
            Attacker.getBase().saveAfterBattle(selfSumHP);
            return logger;
        } else if (oppHP == 0) {

            playerArr = new ArrayList<>();
            mobArr = new ArrayList<>();
            logger.put("Self", null);
            logger.put("Opponent", null);
        } else if (selfHP == 0) {
            playerArr = new ArrayList<>();
            mobArr = new ArrayList<>();
            playerArr.add(Attacker);
            mobArr.add(Defender);
            logger.put("Self", playerArr);
            logger.put("Opponent", mobArr);

        }
        return null;
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
        field.battle("Scissor").get("Opponent");
        System.out.println(field.logger.get("Opponent"));
        System.out.println(m1.getBase().battleLogString());
        System.out.println("————————————————————————————————————————————————————————————————————————————————————");
        ArrayList<Object> mapper = field.battle("Scissor").get("Opponent");
        System.out.println(mapper);
        // System.out.println(field.getLogger().get("Opponent"));
        // System.out.println(m1.getBase().battleLogString());
        System.out.println("————————————————————————————————————————————————————————————————————————————————————");

    }
}
