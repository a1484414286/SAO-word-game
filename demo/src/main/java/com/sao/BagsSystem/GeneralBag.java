package com.sao.BagsSystem;

import java.util.ArrayList;

import com.sao.ItemsSystem.ItemElement;

public class GeneralBag {
    private ArrayList<BagTemplate> bags;

    public GeneralBag() {
        bags = new ArrayList<>();
        bags.add(new AntiItemBag());
        bags.add(new ArmorBag());
        bags.add(new CrystalBag());
        bags.add(new FoodBag());
        bags.add(new MaterialBag());
        bags.add(new PotionBag());
        bags.add(new WeaponBag());
    }

    public BagTemplate getCategoryBag(int i) {
        return bags.get(i);
    }

    public void addChild(ItemElement e) {
        for (int i = 0; i < bags.size(); i++) {
            if (e.getClass().equals(this.bags.get(i).getType())) {
                this.bags.get(i).addChild(e);
            }
        }

    }

    public String calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        for (BagTemplate i : bags) {
            totalValue += i.calculate()[0];
            totalWeight += i.calculate()[1];
            itemCount += i.calculate()[2];
        }
        return ("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + " 背包物品数量 : " + itemCount);
    }

    @Override
    public String toString() {
        return this.calculate();
    }

}
