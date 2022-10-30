package Bags;

import java.util.HashSet;
import Items.ItemElement;
import Items.ItemTypes.AntiItem;

public class AntiItemBag implements BagTemplate{
    private static HashSet<AntiItem> categoryContainer;
    public AntiItemBag()
    {
        categoryContainer = new HashSet<>();
    }
    @Override
    public void print() {
        System.out.println(categoryContainer.iterator().next().getName() +" : "+categoryContainer.iterator().next().getCount() );
    }
    @Override
    public void addChild(ItemElement e) {
        AntiItem r = (AntiItem) e;
        r.incrementCount();
        categoryContainer.add((AntiItem) e);   
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
            int totalWeight = 0;
            int itemCount = 0;
            BagVisitor visitor = new BagScanner();
            for(ItemElement i : categoryContainer)
            {
               totalValue += i.accept(visitor)[0];
               totalWeight += i.accept(visitor)[1];
               itemCount ++;
            }
        return new int[]{totalValue, totalWeight, itemCount};
    }
    @Override
    public Object getType() {
        return AntiItem.class;
    }
    
        
}
