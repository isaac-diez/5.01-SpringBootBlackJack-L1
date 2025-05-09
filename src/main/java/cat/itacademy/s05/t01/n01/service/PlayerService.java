package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.exception.NoPlayersInTheDatabaseException;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    public Mono<Player> createPlayer(Player player) {
        return playerRepo.save(player);
    }

    public Mono<List<Player>> getAllPlayers() {
        return playerRepo.findAll().
                collectList().
                flatMap(players -> {
                    if (players.isEmpty()) {
                        return Mono.error(new NoPlayersInTheDatabaseException("No hay jugadores en la base de datos"));
                    }
                    return Mono.just(players);
                });

    }

    public Mono<Player> getPlayerById(int id) {
        if (id==0) {
            return Mono.error(new IllegalArgumentException("The id is null or invalid"));
        }
        return playerRepo.findById(id).
                switchIfEmpty(Mono.error(new IllegalArgumentException("There isn't a player with id" + id + "in the DataBase")));
    }





}
