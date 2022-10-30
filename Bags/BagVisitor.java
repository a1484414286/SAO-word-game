package Bags;

import Items.ItemTypes.AntiItem;
import Items.ItemTypes.Crystal;
import Items.ItemTypes.Food;
import Items.ItemTypes.Material;
import Items.ItemTypes.Potion;

public interface BagVisitor {
    public int[] visit(Food f);
    public int[] visit(Potion P);
    public int[] visit(Material m);
    public int[] visit(AntiItem a);
    public int[] visit(Crystal c);
}
