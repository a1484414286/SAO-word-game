package com.sao.Player;

import com.sao.Bags.GeneralBag;
import com.sao.Items.ItemTypes.Potion;
import com.sao.Status.BaseTemplate;

public class Player {
    private String name;
    private BaseTemplate stats;
    private GeneralBag bag;
    
    public Player(int id, String name)
    {
        this.stats = new BaseTemplate(id, name);
        this.bag = new GeneralBag();
    }
    public String getName() {
        return name;
    }

    public BaseTemplate getStats() {
        return stats;
    }

    public GeneralBag getBag() {
        return bag;
    }

    public static void main(String[] args) {
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


        
    }
}
