package com.saoModel.BagsSystem;

import com.saoModel.ItemsSystem.ItemTypes.AntiItem;
import com.saoModel.ItemsSystem.ItemTypes.Crystal;
import com.saoModel.ItemsSystem.ItemTypes.Food;
import com.saoModel.ItemsSystem.ItemTypes.Material;
import com.saoModel.ItemsSystem.ItemTypes.Potion;

public class BagScanner implements BagVisitor {

    @Override
    public int[] visit(Food f) {
        return new int[] { f.getPrice(), f.getWeight() };
    }

    @Override
    public int[] visit(Potion p) {
        return new int[] { p.getPrice(), p.getWeight() };

    }

    @Override
    public int[] visit(Material m) {
        return new int[] { m.getPrice(), m.getWeight() };

    }

    @Override
    public int[] visit(AntiItem a) {
        return new int[] { a.getPrice(), a.getWeight() };

    }

    @Override
    public int[] visit(Crystal c) {
        return new int[] { c.getPrice(), c.getWeight() };

    }
}
