package com.saoModel.ItemsSystem;

import com.saoModel.BagsSystem.BagVisitor;

public interface ItemElement {
    public int[] accept(BagVisitor visitor);

    public String toString();

    public String getName();
}
