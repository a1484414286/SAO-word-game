package com.sao.Bags;

import com.sao.Items.ItemElement;

public interface BagTemplate {
    public abstract void print();
    public abstract void addChild(ItemElement e);
    public abstract int[] calculate();
    public abstract Object getType();
}
