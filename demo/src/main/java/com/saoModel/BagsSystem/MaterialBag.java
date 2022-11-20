package com.saoModel.BagsSystem;

import java.util.HashSet;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.ItemsSystem.ItemTypes.Material;

public class MaterialBag implements BagTemplate {
    private HashSet<Material> materialContainer;

    public MaterialBag() {
        materialContainer = new HashSet<>();
    }

    @Override
    public String print() {
        if (this.materialContainer.size() < 1) {
            return ("");
        } else {
            return (materialContainer.iterator().next().getName() + " : "
                    + materialContainer.iterator().next().getCount());
        }
    }

    @Override
    public String toString() {
        return this.print();
    }

    @Override
    public void addChild(ItemElement e) {
        Material f = (Material) e;
        f.incrementCount();
        this.materialContainer.add((Material) e);
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
        // System.out.println("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + "
        // 背包物品数量 : " + itemCount);
        return new int[] { totalValue, totalWeight, itemCount };
    }

    @Override
    public Object getType() {
        return Material.class;
    }

    public HashSet<Material> getMaterialContainer() {
        return materialContainer;
    }
}
