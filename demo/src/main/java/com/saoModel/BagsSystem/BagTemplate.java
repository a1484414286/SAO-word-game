package com.saoModel.BagsSystem;

import com.saoModel.ItemsSystem.ItemElement;

public interface BagTemplate {

    public abstract void addChild(ItemElement e);

    public abstract int[] calculate();

    public abstract Object getType();
}
