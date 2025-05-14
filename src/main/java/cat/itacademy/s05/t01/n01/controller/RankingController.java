package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.exception.NoPlayersInTheDatabaseException;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RankingController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/ranking")
    public Mono<List<Player>> getPlayersGainRanking() {
        return playerService.getPlayersGainRanking();
    }

    @ExceptionHandler(NoPlayersInTheDatabaseException.class)
    public ResponseEntity<String> handleNoPlayersInDB(NoPlayersInTheDatabaseException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }

}