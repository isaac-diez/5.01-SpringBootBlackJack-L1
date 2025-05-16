package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/")
public class RankingController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Obtener un listado con el ranking de jugadores por sus ganancias")
    @GetMapping("/ranking")
    public Mono<List<Player>> getPlayersGainRanking() {
        return playerService.getPlayersGainRanking();
    }

}