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
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void provokeBattle(Player p1, MobTemplate mob) {
        this.Attacker = p1;
        this.Defender = mob;
    }

    // public void provokeBattle(Player p1, ArrayList<MobTemplate> mobs) {

    // }

    public void provokeBattle(Player p1, Player p2) {

    }

    public void Notify(MobTemplate mob) {

    }

    // public void Notify(ArrayList<MobTemplate> mob) {

    // }

    public void Notify(Player p1, MobTemplate mob) {

    }

    public double[] battle(String message) {
        double sumHP1 = 0;
        int HP1 = Attacker.getStats().getHP();
        int ATK1 = Attacker.getStats().getSTR();

        double sumHP2 = 0;
        int HP2 = Defender.getStats().getHP();
        int ATK2 = Defender.getStats().getSTR();

        String oppAttackStyle = Defender.getAtkSyle();

        Double uAttack = algo.get(message).get(oppAttackStyle);
        Double oppAttack = algo.get(oppAttackStyle).get(message);

        sumHP1 = HP1 - ((ATK2 * oppAttack) / 3);
        sumHP2 = HP2 - ((ATK1 * uAttack) / 3);
        return new double[] { sumHP1, sumHP2 };
    }

    public static void main(String[] args) {
        Player p1 = new Player(0, "✌");
        RegMob m1 = new RegMob(0, "野猪", 5, new HashMap<>());
        BattleField field = new BattleField();
        field.provokeBattle(p1, m1);
        System.out.println(field.battle("剪刀")[0]);
        System.out.println(field.battle("剪刀")[1]);

    }
}
