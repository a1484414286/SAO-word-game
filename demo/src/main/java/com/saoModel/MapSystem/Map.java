package com.saoModel.MapSystem;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Document(collection = "Maps")
@ToString
public class Map {
    @Id
    public int id;
    public ArrayList<ArrayList<LevelStage>> Floor;

    public Map(int id, ArrayList<ArrayList<LevelStage>> floor) {
        this.id = id;
        this.Floor = floor;
    }

    public Map(int id) {
        this.id = id;
        this.Floor = new ArrayList<>();
    }

}
