package com.saoModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Potion;
import com.saoModel.JsonModel.ItemJsonUtil;
import com.saoModel.JsonModel.LoadAllJsonModel;
import com.saoModel.MapSystem.LevelStage;
import com.saoModel.MobSystem.Factories.AbstractFactory;
import com.saoModel.MobSystem.Factories.FactoryProducer;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoModel.PlayerSystem.Player;

/**
 * Hello world!
 *
 */
public class App {
    // class User {
    // public int[][] wpj;
    // public int abc;
    // public String cde;
    // }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 加载所有的JsonModel
        LoadAllJsonModel.load();
        // System.out.println(LevelJsonUtil.getModel(1).hp);
        // System.out.println(LevelJsonUtil.getModel(2).mp);
        // System.out.println(LevelJsonUtil.getModel(3));

        for (Long i : ItemJsonUtil.getMap().keySet()) {
            System.out.println(ItemJsonUtil.getModel(i).name);
            System.out.println(ItemJsonUtil.getModel(i).desc);
            System.out.println(ItemJsonUtil.getModel(i).statck);
        }
        Player player1 = new Player(0, "晓铜");
        System.out.println(player1.getStats());
        Potion po = new Potion(0, "红药", " +10 HP", 20, 5, 1);
        player1.getBag().addChild(po);
        System.out.println();
        // 查看药水背包:
        System.out.println("药水背包: ");
        player1.getBag().getCategoryBag(5).print();
        System.out.println(player1.getBag());
        System.out
                .println("\n 介绍 : " + po.getName() + po + ", " + " 重量 " + po.getWeight() + " 价格 " + po.getPrice());

        // 怪物
        AbstractFactory mob = FactoryProducer.getFactory(false);

        MobTemplate firstMob = mob.getMob(RegMob.class, "野猪", 10, 10, 1, new HashMap<Integer, ItemElement>());
        System.out.println(firstMob);

        // 地图

        // 这一层的地图
        HashMap<Integer, ArrayList<LevelStage>> floors = new HashMap<>();

        // 这块区域
        ArrayList<LevelStage> firstFloor = new ArrayList<>();

        // 这个关卡
        LevelStage stage1 = new LevelStage("草原小路1");

        stage1.addPlayer(player1);
        // 玩家加入关卡,草原小路1

        firstFloor.add(stage1);
        // 添加到区�?

        floors.put(1, firstFloor);
        // 添加到总地�?/层地�?

        System.out.println(floors);
        // System.out.println(firstMob);
        // MobTemplate mob1 = new RegMob(0, "野猪", new HashMap<Integer, ItemElement>(),
        // 0);
        // BattleField field = new BattleField(player1.getStats().getStats(),
        // mob1.getStats());
        // field.battle();
        // System.out.println(mob1.getStats().getHP());
        // System.out.println(player1.getStats().getStats().getHP());

        // 测试fastjson2
        // String text = "{\"wpj\":[[123,123],[123]],\"abc\":\"134\",
        // \"cde\":\"sdfad\"}";
        // JSONObject jsonObject = JSONObject.parseObject(text);
        // User user = jsonObject.to(User.class);
        // System.out.println(user);
    }
}
