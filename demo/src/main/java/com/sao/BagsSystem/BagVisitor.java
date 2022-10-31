package com.sao.Bags;

import com.sao.Items.ItemTypes.AntiItem;
import com.sao.Items.ItemTypes.Crystal;
import com.sao.Items.ItemTypes.Food;
import com.sao.Items.ItemTypes.Material;
import com.sao.Items.ItemTypes.Potion;

public interface BagVisitor {
    public int[] visit(Food f);
    public int[] visit(Potion P);
    public int[] visit(Material m);
    public int[] visit(AntiItem a);
    public int[] visit(Crystal c);
}
