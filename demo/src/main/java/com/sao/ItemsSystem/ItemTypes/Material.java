package com.sao.Items.ItemTypes;

import com.sao.Items.ItemElement;
import com.sao.Bags.BagVisitor;

public class Material implements ItemElement{
    private int id;
    private String name;
    private String description;
    private int price;
    private int weight;
    private int durability;
    private int count;

    public Material(int id, String name, String description, int price, int weight, int durability)
    {
        this.id = id;
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
    public int[] accept(BagVisitor visitor) {
        return visitor.visit(this);
    }

    public void incrementCount()
    {
        this.count += 1;
    }

}