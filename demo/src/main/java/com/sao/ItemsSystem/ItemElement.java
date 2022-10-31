package com.sao.Items;

import com.sao.Bags.BagVisitor;

public interface ItemElement {
    public int[] accept(BagVisitor visitor);
}
