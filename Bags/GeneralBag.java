package Bags;

import java.util.ArrayList;

import Items.ItemElement;
import Items.ItemTypes.AntiItem;
public class GeneralBag{
    private ArrayList<BagTemplate> bag;

    public GeneralBag()
    {
        bag = new ArrayList<>();
        bag.add(new AntiItemBag());
        bag.add(new ArmorBag());
        bag.add(new CrystalBag());
        bag.add(new FoodBag());
        bag.add(new MaterialBag());
        bag.add(new PotionBag());
        bag.add(new WeaponBag());
    }
    public static void main(String[] args) {
        GeneralBag gb = new GeneralBag();
        AntiItem a = new AntiItem(0, "anti teleport", null, 50, 2, 0);
        gb.addChild(a);      
        gb.addChild(a);      
        gb.addChild(a);
        gb.bag.get(0).print();
        gb.calculate(gb.bag);
    }

    public void addChild(ItemElement e) 
    {
        for(int i = 0 ; i < bag.size(); i ++)
        {
            if(e.getClass().equals(this.bag.get(i).getType()))
            {
                this.bag.get(i).addChild(e);
            }
        }     
        
    }

    public void calculate(ArrayList<BagTemplate> arr)
    {

        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        for(BagTemplate i : arr)
        {
            totalValue += i.calculate()[0];
            totalWeight += i.calculate()[1];
            itemCount += i.calculate()[2];
        }
        System.out.println("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + " 背包物品数量 : " + itemCount);
    }


 
}
