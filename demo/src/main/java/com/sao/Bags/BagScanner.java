package com.sao.Bags;

import com.sao.Items.ItemTypes.AntiItem;
import com.sao.Items.ItemTypes.Crystal;
import com.sao.Items.ItemTypes.Food;
import com.sao.Items.ItemTypes.Material;
import com.sao.Items.ItemTypes.Potion;

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
