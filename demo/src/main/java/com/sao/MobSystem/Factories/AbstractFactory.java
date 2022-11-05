package com.sao.MobSystem.Factories;

import java.util.HashMap;

import com.sao.ItemsSystem.ItemElement;
import com.sao.MobSystem.Mobs.MobTemplate;

public abstract class AbstractFactory {
    abstract MobTemplate getMob(boolean enhanced, int HP, int MP, int LVL, HashMap<Integer, ItemElement> drops);
}
