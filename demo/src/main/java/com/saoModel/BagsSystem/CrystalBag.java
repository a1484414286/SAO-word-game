package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Crystal;

public class CrystalBag implements BagTemplate {
    private ArrayList<Crystal> crystalContainer = new ArrayList<>();

    public CrystalBag() {
        crystalContainer = new ArrayList<>();
    }

    @Override
    public String print() {
        if (this.crystalContainer.size() < 1) {
            return ("");
        } else {
            return (crystalContainer.iterator().next().getName() + " : "
                    + crystalContainer.iterator().next().getCount());
        }
    }

    @Override
    public String toString() {
        return this.print();
    }

    @Override
    public void addChild(ItemElement e) {
        Crystal f = (Crystal) e;
        if (crystalContainer.contains(f)) {
            Crystal tempF = crystalContainer.get(crystalContainer.indexOf(f));
            tempF.incrementCount();
            crystalContainer.add(crystalContainer.indexOf(f), tempF);
        } else {
            f.incrementCount();
            this.crystalContainer.add(f);
        }
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : crystalContainer) {
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
        return Crystal.class;
    }

    public ArrayList<Crystal> getCrystalContainer() {
        return crystalContainer;
    }

}
