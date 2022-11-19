package com.saoModel.BagsSystem;

import com.saoModel.ItemsSystem.ItemTypes.AntiItem;
import com.saoModel.ItemsSystem.ItemTypes.Crystal;
import com.saoModel.ItemsSystem.ItemTypes.Food;
import com.saoModel.ItemsSystem.ItemTypes.Material;
import com.saoModel.ItemsSystem.ItemTypes.Potion;

public interface BagVisitor {
    public int[] visit(Food f);

    public int[] visit(Potion P);

    public int[] visit(Material m);

    public int[] visit(AntiItem a);

    public int[] visit(Crystal c);
}
