package Bags;

import java.util.ArrayList;

import Items.ItemElement;

public class ArmorBag implements BagTemplate{
    private ArrayList<ItemElement> armorContainer;
    public ArmorBag()
    {
        armorContainer = new ArrayList<>();
    }
    @Override
    public void print() {
        for(int i = 0; i < armorContainer.size(); i++)
        {
            System.out.println(armorContainer.get(i));
        }
    }
    @Override
    public void addChild(ItemElement e) {
        
    }
    @Override
    public int[] calculate() {
            int totalValue = 0;
            int totalWeight = 0;
            int itemCount = 0;
            BagVisitor visitor = new BagScanner();
            for(ItemElement i : armorContainer)
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
