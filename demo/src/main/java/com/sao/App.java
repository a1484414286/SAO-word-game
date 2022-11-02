package com.sao;

import java.util.HashMap;

import com.sao.BattleSystem.BattleField;
import com.sao.ItemsSystem.ItemElement;
import com.sao.ItemsSystem.ItemTypes.Potion;
import com.sao.MobSystem.MobTemplate;
import com.sao.MobSystem.RegMob;
import com.sao.PlayerSystem.Player;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Player player1 = new Player(0, "晓铜");
        System.out.println(player1.getStats());
        Potion po = new Potion(0, "红药水", " +10 HP", 20, 5, 1);
        player1.getBag().addChild(po);
        System.out.println();
        //查看药水背包:
        System.out.println("药水背包: ");
        player1.getBag().getCategoryBag(5).print();;
        System.out.println(player1.getBag());
        System.out.println("\n 介绍 : " + po.getName() + po + ", " + " 重量 " + po.getWeight() + " 价值 " + po.getPrice());

        //怪物

        MobTemplate mob1 = new RegMob(0, "野猪", new HashMap<Integer, ItemElement>(), 0);
        BattleField field = new BattleField(player1.getStats().getStats(), mob1.getStats());
        field.battle();
        System.out.println(mob1.getStats().getHP());
        System.out.println(player1.getStats().getStats().getHP());
    }
}
