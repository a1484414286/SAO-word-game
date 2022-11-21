package com.saoModel.BagsSystem;

import java.util.ArrayList;
import java.util.Iterator;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Food;

public class FoodBag implements BagTemplate {
    private ArrayList<Food> foodContainer;

    public FoodBag() {
        foodContainer = new ArrayList<>();
    }

    @Override
    public void addChild(ItemElement e) {
        Food f = (Food) e;
        int index = foodContainer.size();
        if (foodContainer.size() != 0) {
            for (Food foo : foodContainer) {
                if (f.getName().equals(foo.getName())) {
                    index = foodContainer.indexOf(foo);
                    f = foo;
                }
            }
            f.incrementCount();
            foodContainer.set(index, f);
            return;
        }
        f.incrementCount();
        foodContainer.add(f);
    }

    @Override
    public String toString() {
        if (this.foodContainer.size() < 1) {
            return ("");
        } else {
            String result = "";
            Iterator<Food> foo = foodContainer.iterator();
            while (foo.hasNext()) {
                Food f = foo.next();
                result += f.getName() + " : " + f.getCount() + '\n';
            }
            return result;
        }
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
