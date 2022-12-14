package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;

public class WeaponBag implements BagTemplate {
    private ArrayList<ItemElement> weaponContainer;

    public WeaponBag() {
        weaponContainer = new ArrayList<>();
    }

    public void setWeaponContainer(ArrayList<ItemElement> weaponContainer) {
        this.weaponContainer = weaponContainer;
    }

    @Override
    public String toString() {

        // if (this.foodContainer.size() < 1) {
        // return ("");
        // } else {
        // String result = "";
        // Iterator<Food> foo = foodContainer.iterator();
        // while (foo.hasNext()) {
        // Food f = foo.next();
        // result += f.getName() + " : " + f.getCount();
        // }
        return "";
        // }

    }

    @Override
    public void addChild(ItemElement e) {
        // TODO Auto-generated method stub

    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : weaponContainer) {
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

    public ArrayList<ItemElement> getWeaponContainer() {
        return weaponContainer;
    }
}
