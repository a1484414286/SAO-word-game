package com.saoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saoModel.PlayerSystem.Player;
import com.saoView.PlayersData;

@RestController
public class PlayerController {
    @Autowired
    private PlayersData repo;

    @PostMapping("/addPlayer/{id}/{name}")
    public String savePlayer(@PathVariable int id, String name) {
        repo.insert(new Player(id, name));
        return id + " " + name + " has been added to the database";
    }

    @GetMapping("/All")
    public List<Player> retrivePlayers() {
        return repo.findAll();
    }
}
