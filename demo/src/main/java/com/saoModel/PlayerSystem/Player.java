package com.saoModel.PlayerSystem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saoModel.BagsSystem.GeneralBag;
import com.saoModel.StatusSystem.BaseTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Players")
public class Player {
    @Id
    private String name;
    private BaseTemplate stats;
    private GeneralBag bag;
    private int id;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.stats = new BaseTemplate(id, name);
        this.bag = new GeneralBag();
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

    public GeneralBag getBag() {
        return bag;
    }

}
