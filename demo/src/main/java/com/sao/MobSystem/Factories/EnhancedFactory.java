package com.sao.MobSystem.Factories;

import java.util.HashMap;

import com.sao.ItemsSystem.ItemElement;
import com.sao.MobSystem.Mobs.DungeonMob;
import com.sao.MobSystem.Mobs.EliteMob;
import com.sao.MobSystem.Mobs.MobTemplate;
import com.sao.MobSystem.Mobs.RegMob;
import com.sao.MobSystem.Mobs.SpecialMob;

public class EnhancedFactory extends AbstractFactory{

    @Override
    public MobTemplate getMob(Object type, String name,int HP, int MP, int LVL, HashMap<Integer, ItemElement> drops) {
        if(type.equals(EliteMob.class))
        {
            return new EliteMob(name, LVL, drops);
        }
        else if(type.equals(SpecialMob.class))
        {
            return new SpecialMob(name, LVL, drops);
        }
        else if(type.equals(DungeonMob.class))
        {
            return new DungeonMob(name, LVL, drops);
        }
        else if(type.equals(RegMob.class))
        {
            return new RegMob(name, LVL, drops);
        }
        else
        {
            return null;
        }
    }

 
    
}
