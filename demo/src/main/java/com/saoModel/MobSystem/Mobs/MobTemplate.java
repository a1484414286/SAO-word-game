package com.saoModel.MobSystem.Mobs;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mobs")
public interface MobTemplate {
    abstract void statusAdjustification();
}
