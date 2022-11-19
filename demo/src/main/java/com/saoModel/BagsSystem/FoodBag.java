package com.saoModel.BagsSystem;

import java.util.HashSet;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Food;

public class FoodBag implements BagTemplate {
    private HashSet<Food> foodContainer;

    public FoodBag() {
        foodContainer = new HashSet<>();
    }

    @Override
    public String print() {
        if (this.foodContainer.size() < 1) {
            return ("");
        } else {
            return (foodContainer.iterator().next().getName() + " : "
                    + foodContainer.iterator().next().getCount());
        }
    }

    @Override
    public void addChild(ItemElement e) {
        Food f = (Food) e;
        f.incrementCount();
        this.foodContainer.add((Food) e);
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : foodContainer) {
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
        return Food.class;
    }

}
