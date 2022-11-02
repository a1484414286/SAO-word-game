package com.sao.BagsSystem;

import com.sao.ItemsSystem.ItemTypes.AntiItem;
import com.sao.ItemsSystem.ItemTypes.Crystal;
import com.sao.ItemsSystem.ItemTypes.Food;
import com.sao.ItemsSystem.ItemTypes.Material;
import com.sao.ItemsSystem.ItemTypes.Potion;

public class BagScanner implements BagVisitor{

    @Override
    public int[] visit(Food f) {
        return new int[]{f.getPrice(), f.getWeight()};
    }
    @Override
    public int[] visit(Potion p) {
        return new int[]{p.getPrice(), p.getWeight()};

    }
    @Override
    public int[] visit(Material m) {
        return new int[]{m.getPrice(), m.getWeight()};

    }
    @Override
    public int[] visit(AntiItem a) {
        return new int[]{a.getPrice(), a.getWeight()};

    }
    @Override
    public int[] visit(Crystal c) {
        return new int[]{c.getPrice(), c.getWeight()};

    }
}
