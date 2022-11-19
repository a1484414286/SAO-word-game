package com.saoModel.MobSystem.Factories;

import java.util.HashMap;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.MobSystem.Mobs.MobTemplate;

public abstract class AbstractFactory {
    public abstract MobTemplate getMob(Object mobType, String name, int HP, int MP, int LVL,
            HashMap<Integer, ItemElement> hashMap);

}
