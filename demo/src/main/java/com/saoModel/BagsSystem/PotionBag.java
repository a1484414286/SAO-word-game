package com.saoModel.BagsSystem;

import java.util.ArrayList;
import java.util.Iterator;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Potion;

public class PotionBag implements BagTemplate {
    private ArrayList<Potion> potionContainer;

    public PotionBag() {
        potionContainer = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.potionContainer.size() < 1) {
            return ("");
        } else {
            String result = "";
            Iterator<Potion> foo = potionContainer.iterator();
            while (foo.hasNext()) {
                Potion f = foo.next();
                result += f.getName() + " : " + f.getCount();
            }
            return result;
        }
    }

    @Override
    public void addChild(ItemElement e) {
        Potion f = (Potion) e;
        int index = potionContainer.size();
        if (potionContainer.size() != 0) {
            for (Potion foo : potionContainer) {
                if (f.getName().equals(foo.getName())) {
                    index = potionContainer.indexOf(foo);
                    f = foo;
                }
            }
            f.incrementCount();
            potionContainer.set(index, f);
            return;
        }
        f.incrementCount();
        potionContainer.add(f);
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
