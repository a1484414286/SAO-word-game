package com.saoModel.MapSystem;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Maps")
public class Map {
    @Id
    private int id;
    private ArrayList<Stage> Floor;
    private int size;

    public Map(int id, int size) {
        this.id = id;
        this.size = size;
        this.Floor = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int tileID = Integer.valueOf(i);
            Floor.add(new Stage(tileID, tileID + " 区域名字"));
        }
    }

    public String printMap() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += Floor.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Map m = new Map(0, 10);
        System.out.println(m.printMap());
    }

}
