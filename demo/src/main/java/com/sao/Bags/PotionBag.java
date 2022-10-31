package com.sao.Bags;

import java.util.HashSet;

import com.sao.Items.ItemTypes.Potion;

public class PotionBag implements BagTemplate {
    private HashSet<Potion> potionContainer;

    public PotionBag()
    {
        potionContainer = new HashSet<>();
    }
    @Override
    public void print() {
        if(this.potionContainer.size() < 1)
        {
            System.out.println("无物品");
        }
        System.out.println(potionContainer.iterator().next().getName() +" : "+potionContainer.iterator().next().getCount() );
    }

    @Override
    public void addChild(ItemElement e) {
        Potion f = (Potion)e;
        f.incrementCount();
        this.potionContainer.add((Potion) e);
    }
    
    @Override
    public int[] calculate() {
        int totalValue = 0;
            int totalWeight = 0;
            int itemCount = 0;
            BagVisitor visitor = new BagScanner();
            for(ItemElement i : potionContainer)
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
        return Potion.class;
    }
}