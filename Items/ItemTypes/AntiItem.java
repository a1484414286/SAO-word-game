package Items.ItemTypes;

import Bags.BagVisitor;
import Items.ItemElement;

public class AntiItem implements ItemElement{
    private int id;
    private String name;
    private String description;
    private int price;
    private int weight;
    private int durability;
    private int count;

    public AntiItem(int id, String name, String description, int price, int weight, int durability)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.durability = durability;
        this.count = 0;
    }
    public void incrementCount()
    {
        this.count += 1;
    }

    public int getCount() {
        return count;
    }
    public String getDescription() {
        return description;
    }
    public int getDurability() {
        return durability;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getWeight() {
        return weight;
    }
    public String toString() {
        return description;
    }
    @Override
    public int[] accept(BagVisitor visitor) {
        return visitor.visit(this);
    }

}
