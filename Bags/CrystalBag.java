package Bags;

import java.util.ArrayList;

import Items.ItemElement;
import Items.ItemTypes.Crystal;

public class CrystalBag implements BagTemplate{
    private ArrayList<Crystal> crystalContainer = new ArrayList<>();
    public CrystalBag()
    {
        crystalContainer = new ArrayList<>();
    }

    @Override
    public void print() {
        for(int i = 0; i < crystalContainer.size(); i++)
        {
            System.out.println(crystalContainer.get(i));
        }
        
    }

    

    @Override
    public void addChild(ItemElement e) {
        Crystal r = (Crystal) e;
        r.incrementCount();
        this.crystalContainer.add((Crystal)e);
    }
    @Override
    public int[] calculate() {
            int totalValue = 0;
            int totalWeight = 0;
            int itemCount = 0;
            BagVisitor visitor = new BagScanner();
            for(ItemElement i : crystalContainer)
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
        return Crystal.class;
    }
  
}
