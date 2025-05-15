package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.service.PlayerService;
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

    @GetMapping("/ranking")
    public Mono<List<Player>> getPlayersGainRanking() {
        return playerService.getPlayersGainRanking();
    }

}