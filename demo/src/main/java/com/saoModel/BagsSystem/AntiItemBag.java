package com.saoModel.BagsSystem;

import java.util.ArrayList;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.AntiItem;

public class AntiItemBag implements BagTemplate {
    private ArrayList<AntiItem> antiItemContainer;

    public AntiItemBag() {
        antiItemContainer = new ArrayList<>();
    }

    @Override
    public String print() {
        if (this.antiItemContainer.size() < 1) {
            return ("");
        } else {
            return (antiItemContainer.iterator().next().getName() + " : "
                    + antiItemContainer.iterator().next().getCount());
        }
    }

    @Override
    public String toString() {
        return this.print();
    }

    @Override
    public void addChild(ItemElement e) {
        AntiItem f = (AntiItem) e;
        if (antiItemContainer.contains(f)) {
            AntiItem tempF = antiItemContainer.get(antiItemContainer.indexOf(f));
            tempF.incrementCount();
            antiItemContainer.add(antiItemContainer.indexOf(f), tempF);
        } else {
            f.incrementCount();
            this.antiItemContainer.add(f);
        }
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : antiItemContainer) {
            totalValue += i.accept(visitor)[0];
            totalWeight += i.accept(visitor)[1];
            itemCount++;
        }
        return new int[] { totalValue, totalWeight, itemCount };
    }

    @Override
    public Object getType() {
        return AntiItem.class;
    }

    public ArrayList<AntiItem> getCategoryContainer() {
        return antiItemContainer;
    }

}
