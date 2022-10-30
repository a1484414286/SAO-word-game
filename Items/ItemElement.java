package Items;

import Bags.BagVisitor;

public interface ItemElement {
    public int[] accept(BagVisitor visitor);
}
