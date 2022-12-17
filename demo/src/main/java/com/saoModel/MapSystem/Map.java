package com.saoModel.MapSystem;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "Maps")
public class Map {
    @Id
    private int id;
    private ArrayList<ArrayList<Stage>> Floor;
    private int size;

    public Map(int id, int size) {
        this.id = id;
        this.size = size;
        this.Floor = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Floor.add(new ArrayList<>());
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tileID = Integer.valueOf(i + "" + j);
                Floor.get(i).add(new Stage(tileID, tileID + " 名字"));
            }
        }
    }

    public String printMap() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += Floor.get(i) + "\n";
        }
        return result;
    }

}
