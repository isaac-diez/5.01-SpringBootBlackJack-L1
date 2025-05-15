package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.dto.GameRequest;
import cat.itacademy.s05.t01.n01.exception.NoGamesInTheDatabaseException;
import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> createGame(@RequestBody GameRequest request) {
        return gameService.createGame(request.getPlayerName(), request.getInitialBet())
                .map(game -> ResponseEntity.status(HttpStatus.CREATED).body(game));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Game>> getGameById(@PathVariable String id) {
        return gameService.getGameById(id)
                .map(game -> ResponseEntity.status(HttpStatus.FOUND).body(game))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/play")
    public Mono<ResponseEntity<Game>> playGame(@PathVariable String id) {
        System.out.println("Playing game with ID: " + id);
        return gameService.playGame(id).
                map(game -> ResponseEntity.status(HttpStatus.OK).body(game));
    }

    @GetMapping("/getAll")
    public Mono<ResponseEntity<List<Game>>> getAllGames() {
        return gameService.getAllGames()
                .map(games -> ResponseEntity.ok().body(games))
                .onErrorResume(NoGamesInTheDatabaseException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable String id) {
        return gameService.deleteGame(id).
                thenReturn(ResponseEntity.noContent().build());
    }

}
