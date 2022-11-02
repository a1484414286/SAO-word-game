package com.sao.BagsSystem;

import java.util.ArrayList;

import com.sao.ItemsSystem.ItemElement;

public class WeaponBag implements BagTemplate{
    private ArrayList<ItemElement> weaponContainer;
    public WeaponBag()
    {
        weaponContainer = new ArrayList<>();
    }

    @Override
    public void print() {
        for(int i = 0; i < weaponContainer.size(); i++)
        {
            System.out.println(weaponContainer.get(i));
        }
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
            for(ItemElement i : weaponContainer)
            {
               totalValue += i.accept(visitor)[0];
               totalWeight += i.accept(visitor)[1];
               itemCount ++;
            }
            // System.out.println("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + " 背包物品数量 : " + itemCount);
        return new int[]{totalValue, totalWeight, itemCount};
    }

    @Override
    public Object getType() {
        // TODO Auto-generated method stub
        return null;
    }
}
