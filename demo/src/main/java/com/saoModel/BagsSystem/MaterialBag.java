package com.saoModel.BagsSystem;

import java.util.ArrayList;
import java.util.Iterator;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Material;

public class MaterialBag implements BagTemplate {
    private ArrayList<Material> materialContainer;

    public MaterialBag() {
        materialContainer = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.materialContainer.size() < 1) {
            return ("");
        } else {
            String result = "";
            Iterator<Material> foo = materialContainer.iterator();
            while (foo.hasNext()) {
                Material f = foo.next();
                result += f.getName() + " : " + f.getCount();
            }
            return result;
        }
    }

    @Override
    public void addChild(ItemElement e) {
        Material f = (Material) e;
        int index = materialContainer.size();
        if (materialContainer.size() != 0) {
            for (Material foo : materialContainer) {
                if (f.getName().equals(foo.getName())) {
                    index = materialContainer.indexOf(foo);
                    f = foo;
                }
            }
            f.incrementCount();
            materialContainer.set(index, f);
            return;
        }
        f.incrementCount();
        materialContainer.add(f);
    }

    @Override
    public int[] calculate() {
        int totalValue = 0;
        int totalWeight = 0;
        int itemCount = 0;
        BagVisitor visitor = new BagScanner();
        for (ItemElement i : materialContainer) {
            totalValue += i.accept(visitor)[0];
            totalWeight += i.accept(visitor)[1];
            itemCount++;
        }
        return new int[] { totalValue, totalWeight, itemCount };
    }

    @Override
    public Object getType() {
        return Material.class;
    }

    public ArrayList<Material> getMaterialContainer() {
        return materialContainer;
    }
}
