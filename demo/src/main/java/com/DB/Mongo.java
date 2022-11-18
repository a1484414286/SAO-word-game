package com.DB;

import com.sao.ItemsSystem.ItemTypes.Food;
import com.sao.ItemsSystem.ItemTypes.Potion;

public class Mongo {
    public static void main(String[] args) {
        MongoUtils.insertPlayer(0, "晓桐");
        MongoUtils.insertPlayer(1, "二大爷");
        MongoUtils.insertItemsToPlayerBag(1, new Potion(0, "红药", "补血", 50, 0, 0));
        MongoUtils.insertItemsToPlayerBag(1, new Potion(1, "蓝药", "补魔", 50, 0, 0));
        MongoUtils.insertItemsToPlayerBag(1, new Food(0, "茶叶蛋", "饱腹", 20, 0, 0));
    }
}
