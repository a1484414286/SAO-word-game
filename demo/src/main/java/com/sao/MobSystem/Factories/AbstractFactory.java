package com.sao.MobSystem.Factories;

import java.util.HashMap;

import com.sao.ItemsSystem.ItemElement;
import com.sao.MobSystem.Mobs.MobTemplate;

public abstract class AbstractFactory {
    public abstract MobTemplate getMob(Object mobType,String name, int HP, int MP, int LVL, HashMap<Integer, ItemElement> hashMap);

}
