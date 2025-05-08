package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    public Mono<Player> createPlayer(Player player) {
        return playerRepo.save(player);
    }



}
