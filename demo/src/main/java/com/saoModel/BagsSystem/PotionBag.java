package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Potion;

public class PotionBag implements BagTemplate {
    private ArrayList<Potion> potionContainer;

    public PotionBag() {
        potionContainer = new ArrayList<>();
    }

    @Override
    public String print() {
        if (this.potionContainer.size() < 1) {
            return ("");
        } else {
            return (potionContainer.iterator().next().getName() + " : "
                    + potionContainer.iterator().next().getCount());
        }
    }

    @Override
    public String toString() {
        return this.print();
    }

    @Override
    public void addChild(ItemElement e) {
        Potion f = (Potion) e;
        if (potionContainer.contains(f)) {
            Potion tempF = potionContainer.get(potionContainer.indexOf(f));
            tempF.incrementCount();
            potionContainer.add(potionContainer.indexOf(f), tempF);
        } else {
            f.incrementCount();
            this.potionContainer.add(f);
        }
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : potionContainer) {
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
        return Potion.class;
    }

    public ArrayList<Potion> getPotionContainer() {
        return potionContainer;
    }
}
