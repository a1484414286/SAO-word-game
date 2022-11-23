package com.saoModel.MobSystem.Mobs;

import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.StatusSystem.StatsTemplate;

@Document(collection = "Mobs")
public interface MobTemplate {
    abstract void statusAdjustification();

    abstract String getAtkSyle();

    abstract StatsTemplate getStats();
}
