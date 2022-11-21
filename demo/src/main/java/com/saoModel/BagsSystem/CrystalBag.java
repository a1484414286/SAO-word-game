package com.saoModel.BagsSystem;

import java.util.ArrayList;
import java.util.Iterator;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Crystal;

public class CrystalBag implements BagTemplate {
    private ArrayList<Crystal> crystalContainer = new ArrayList<>();

    public CrystalBag() {
        crystalContainer = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.crystalContainer.size() < 1) {
            return ("");
        } else {
            String result = "";
            Iterator<Crystal> foo = crystalContainer.iterator();
            while (foo.hasNext()) {
                Crystal f = foo.next();
                result += f.getName() + " : " + f.getCount();
            }
            return result;
        }
    }

    @Override
    public void addChild(ItemElement e) {
        Crystal f = (Crystal) e;
        int index = crystalContainer.size();
        if (crystalContainer.size() != 0) {
            for (Crystal foo : crystalContainer) {
                if (f.getName().equals(foo.getName())) {
                    index = crystalContainer.indexOf(foo);
                    f = foo;
                }
            }
            f.incrementCount();
            crystalContainer.set(index, f);
            return;
        }
        f.incrementCount();
        crystalContainer.add(f);
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : crystalContainer) {
            totalValue += i.accept(visitor)[0];
            totalWeight += i.accept(visitor)[1];
            itemCount++;
        }
        return new int[] { totalValue, totalWeight, itemCount };
    }

    @Override
    public Object getType() {
        return Crystal.class;
    }

    public ArrayList<Crystal> getCrystalContainer() {
        return crystalContainer;
    }

}
