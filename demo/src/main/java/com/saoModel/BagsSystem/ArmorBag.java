package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;

public class ArmorBag implements BagTemplate {
    private ArrayList<ItemElement> armorContainer;

    public ArmorBag() {
        armorContainer = new ArrayList<>();
    }

    @Override
    public String print() {
        return "";
        // if (this.categoryContainer.size() < 1) {
        // return ("无物品");
        // } else {
        // return (categoryContainer.iterator().next().getName() + " : "
        // + categoryContainer.iterator().next().getCount());
        // }
    }

    @Override
    public void addChild(ItemElement e) {

    }

    @Override
    public String toString() {
        return this.print();
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : armorContainer) {
            totalValue += i.accept(visitor)[0];
            totalWeight += i.accept(visitor)[1];
            itemCount++;
        }
        // System.out.println("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + "
        // 背包物品数量 : " + itemCount);
        return new int[] { totalValue, totalWeight, itemCount };
    }

    @Override
    public Object getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<ItemElement> getArmorContainer() {
        return armorContainer;
    }

}