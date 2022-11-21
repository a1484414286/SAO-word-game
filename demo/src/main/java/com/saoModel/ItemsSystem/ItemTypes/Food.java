package com.saoModel.ItemsSystem.ItemTypes;

import com.saoModel.BagsSystem.BagVisitor;
import com.saoModel.ItemsSystem.ItemElement;

public class Food implements ItemElement {
    private String name;
    private String description;
    private int price;
    private int weight;
    private int durability;
    private int count;

    public Food(String name, String description, int price, int weight, int durability) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.durability = durability;
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count += 1;
    }

    public String getDescription() {
        return description;
    }

    public int getDurability() {
        return durability;
    }

    @Override
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
