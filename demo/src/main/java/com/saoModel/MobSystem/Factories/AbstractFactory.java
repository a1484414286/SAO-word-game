package com.saoModel.MobSystem.Factories;

import java.util.HashMap;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.MobSystem.Mobs.MobTemplate;

public abstract class AbstractFactory {
    public abstract MobTemplate getMob(Object mobType, int id, String name, int HP, int MP, int spawnTime,
            HashMap<Integer, ItemElement> hashMap);

}
