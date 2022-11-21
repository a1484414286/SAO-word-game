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
    public String toString() {
        if (this.antiItemContainer.size() < 1) {
            return ("");
        } else {
            return (antiItemContainer.iterator().next().getName() + " : "
                    + antiItemContainer.iterator().next().getCount());
        }
    }

    @Override
    public void addChild(ItemElement e) {
        AntiItem f = (AntiItem) e;
        int index = antiItemContainer.size();
        if (antiItemContainer.size() != 0) {
            for (AntiItem foo : antiItemContainer) {
                if (f.getName().equals(foo.getName())) {
                    index = antiItemContainer.indexOf(foo);
                    f = foo;
                }
            }
            f.incrementCount();
            antiItemContainer.set(index, f);
            return;
        }
        f.incrementCount();
        antiItemContainer.add(f);
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
