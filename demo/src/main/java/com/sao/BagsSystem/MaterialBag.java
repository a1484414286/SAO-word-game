package com.sao.Bags;


import java.util.HashSet;

import com.sao.Items.ItemElement;
import com.sao.Items.ItemTypes.Material;



public class MaterialBag implements BagTemplate{
    private HashSet<Material> materialContainer;
    public MaterialBag()
    {
        materialContainer = new HashSet<>();
    }
    @Override
    public void print() {
        System.out.println(materialContainer.iterator().next().getName() +" : "+materialContainer.iterator().next().getCount() );
    }
    @Override
    public void addChild(ItemElement e) {
        Material f = (Material)e;
        f.incrementCount();
        this.materialContainer.add((Material) e);
    }
    
    @Override
    public int[] calculate() {
            int totalValue = 0;
            int totalWeight = 0;
            int itemCount = 0;
            BagVisitor visitor = new BagScanner();
            for(ItemElement i : materialContainer)
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
        return Material.class;
    }
}
