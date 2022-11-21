package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;

public class ArmorBag implements BagTemplate {
    private ArrayList<ItemElement> armorContainer;

    public ArmorBag() {
        armorContainer = new ArrayList<>();
    }

    @Override
    public void addChild(ItemElement e) {

    }

    @Override
    public String toString() {
        return "";
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
