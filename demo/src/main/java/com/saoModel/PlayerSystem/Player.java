package com.saoModel.PlayerSystem;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.BagsSystem.GeneralBag;
import com.saoModel.MapSystem.LevelStage;
import com.saoModel.StatusSystem.BaseTemplate;
import com.saoModel.StatusSystem.StatsTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@Document(collection = "Players")

public class Player {
    @Id
    private int id;
    private String name;
    private BaseTemplate stats;
    private GeneralBag baggage;
    private LevelStage current;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.stats = new BaseTemplate(name);
        this.baggage = new GeneralBag();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BaseTemplate getBaseTemplate() {
        return stats;
    }

    public StatsTemplate getStats() {
        return stats.getStats();
    }

    public GeneralBag getBaggage() {
        return baggage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

}
