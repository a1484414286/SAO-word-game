package com.sao.ItemsSystem;

import com.sao.BagsSystem.BagVisitor;

public interface ItemElement {
    public int[] accept(BagVisitor visitor);

    public String toString();
}
