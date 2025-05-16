package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/players")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Crear un nuevo jugador (no incluir id)")
    @PostMapping("/add")
    public Mono<Player> createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @Operation(summary = "Recuperar todos los jugadores")
    @GetMapping("/getAll")
    public Mono<List<Player>> getAll() {
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Recuperar un jugador por su id")
    @GetMapping("/getOne/{id}")
    public Mono<Player> getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @Operation(summary = "Actualizar los datos de un jugador incluyendo su id en el body")
    @PutMapping("/update")
    public Mono<Player> updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }

    @Operation(summary = "Borrar un jugador por su id")
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePlayer(@PathVariable int id) {
        return playerService.deletePlayer(id);
    }



}
