package com.saoModel.MobSystem.Mobs;

import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.StatusSystem.BaseTemplate;

@Document(collection = "Mobs")
public interface MobTemplate {
    abstract void statusJustification();

    abstract String getAtkStyle();

    abstract BaseTemplate getStats();

    abstract String saveAfterBattle(int HP);
}
