package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.PlayerRepo;
import cat.itacademy.s05.t01.n01.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/players")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    public Mono<Player> createPlayer(@RequestBody Player player){
        return playerService.createPlayer(player);
    }

    @GetMapping("/getAll")
    public Mono<List<Player>> getAll() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/getOne/{id}")
    public Mono<Player> getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

}
