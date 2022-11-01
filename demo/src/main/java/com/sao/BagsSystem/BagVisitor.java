package com.sao.BagsSystem;

import com.sao.ItemsSystem.ItemTypes.AntiItem;
import com.sao.ItemsSystem.ItemTypes.Crystal;
import com.sao.ItemsSystem.ItemTypes.Food;
import com.sao.ItemsSystem.ItemTypes.Material;
import com.sao.ItemsSystem.ItemTypes.Potion;

public interface BagVisitor {
    public int[] visit(Food f);
    public int[] visit(Potion P);
    public int[] visit(Material m);
    public int[] visit(AntiItem a);
    public int[] visit(Crystal c);
}
