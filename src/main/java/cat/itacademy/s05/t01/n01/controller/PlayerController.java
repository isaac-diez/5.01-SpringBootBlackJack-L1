package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.exception.GameAlreadyPlayedException;
import cat.itacademy.s05.t01.n01.exception.NoPlayersInTheDatabaseException;
import cat.itacademy.s05.t01.n01.exception.PlayerNotFoundInDataBaseExeption;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.PlayerRepo;
import cat.itacademy.s05.t01.n01.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/players")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    public Mono<Player> createPlayer(@RequestBody Player player) {
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

    @PutMapping("/update")
    public Mono<Player> updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePlayer(@PathVariable int id) {
        return playerService.deletePlayer(id);
    }

    @ExceptionHandler(NoPlayersInTheDatabaseException.class)
    public ResponseEntity<String> handleNoPlayersInDB(NoPlayersInTheDatabaseException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }

}
