package com.saoModel.MobSystem.Factories;

import java.util.HashMap;

import com.saoModel.ItemsSystem.ItemElement;
import com.saoModel.MobSystem.Mobs.DungeonMob;
import com.saoModel.MobSystem.Mobs.EliteMob;
import com.saoModel.MobSystem.Mobs.MobTemplate;
import com.saoModel.MobSystem.Mobs.RegMob;
import com.saoModel.MobSystem.Mobs.SpecialMob;

public class EnhancedFactory extends AbstractFactory {

    @Override
    public MobTemplate getMob(Object type, int id, String name, int HP, int MP, int LVL,
            HashMap<Integer, ItemElement> drops) {
        if (type.equals(EliteMob.class)) {
            return new EliteMob(id, name, LVL, drops);
        } else if (type.equals(SpecialMob.class)) {
            return new SpecialMob(id, name, LVL, drops);
        } else if (type.equals(DungeonMob.class)) {
            return new DungeonMob(id, name, LVL, drops);
        } else if (type.equals(RegMob.class)) {
            return new RegMob(id, name, LVL, drops);
        } else {
            return null;
        }
    }
}
