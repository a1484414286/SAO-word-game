package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Food;

public class FoodBag implements BagTemplate {
    private ArrayList<Food> foodContainer;

    public FoodBag() {
        foodContainer = new ArrayList<>();
    }

    @Override
    public String print() {
        if (this.foodContainer.size() < 1) {
            return ("");
        } else {
            String result = "";
            result += foodContainer.iterator().next().getName() + " : "
                    + foodContainer.iterator().next().getCount();
            System.out.println(result);
            return result;
        }
    }

    @Override
    public void addChild(ItemElement e) {
        Food f = (Food) e;
        if (foodContainer.contains(f)) {
            Food tempF = foodContainer.get(foodContainer.indexOf(f));
            tempF.incrementCount();
            foodContainer.add(foodContainer.indexOf(f), tempF);
        } else {
            f.incrementCount();
            this.foodContainer.add((Food) f);
        }
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

    public ArrayList<Food> getFoodContainer() {
        return foodContainer;
    }

}
