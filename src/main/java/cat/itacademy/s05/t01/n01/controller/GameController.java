package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.dto.GameRequest;
import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> createGame(@RequestBody GameRequest request) {
        return gameService.createGame(request.getPlayerName(), request.getInitialBet())
                .map(game -> ResponseEntity.status(HttpStatus.CREATED).body(game));
    }


}
