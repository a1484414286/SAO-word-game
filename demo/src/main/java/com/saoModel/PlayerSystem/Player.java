package com.saoModel.PlayerSystem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.BagsSystem.GeneralBag;
import com.saoModel.StatusSystem.BaseTemplate;

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

    public BaseTemplate getStats() {
        return stats;
    }

    public GeneralBag getBaggage() {
        return baggage;
    }

}
