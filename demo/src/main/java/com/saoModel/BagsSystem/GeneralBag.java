package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;

public class GeneralBag {
    private ArrayList<BagTemplate> baggage;

    public GeneralBag() {
        baggage = new ArrayList<>();
        baggage.add(new AntiItemBag());
        baggage.add(new ArmorBag());
        baggage.add(new CrystalBag());
        baggage.add(new FoodBag());
        baggage.add(new MaterialBag());
        baggage.add(new PotionBag());
        baggage.add(new WeaponBag());
    }

    public BagTemplate getCategoryBag(int i) {
        return baggage.get(i);
    }

    public void addChild(ItemElement e) {
        for (int i = 0; i < baggage.size(); i++) {
            if (e.getClass().equals(this.baggage.get(i).getType())) {
                this.baggage.get(i).addChild(e);
            }
        }
    }

    public String getBaggage() {
        String result = "";
        for (BagTemplate i : baggage) {
            if (i.toString() != result) {
                result += i.toString() + '\n';
            }
        }
        return result;
    }

    public String getCalculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        for (BagTemplate i : baggage) {
            totalValue += i.calculate()[0];
            totalWeight += i.calculate()[1];
            itemCount += i.calculate()[2];
        }
        return ("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + " 背包物品数量 : " + itemCount);
    }

    public String collect() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        for (BagTemplate i : baggage) {
            totalValue += i.calculate()[0];
            totalWeight += i.calculate()[1];
            itemCount += i.calculate()[2];
        }
        return ("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + " 背包物品数量 : " + itemCount);
    }

    @Override
    public String toString() {
        return this.getBaggage();
    }

}
