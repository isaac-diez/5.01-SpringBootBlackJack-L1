package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.exception.NoPlayersInTheDatabaseException;
import cat.itacademy.s05.t01.n01.exception.PlayerNotFoundInDataBaseExeption;
import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
                        return Mono.error(new NoPlayersInTheDatabaseException("The database is empty"));
                    }
                    return Mono.just(players);
                });

    }

    public Mono<Player> getPlayerById(int id) {
        if (id==0) {
            return Mono.error(new PlayerNotFoundInDataBaseExeption("The id is null or invalid"));
        }
        return playerRepo.findById(id).
                switchIfEmpty(Mono.error(new PlayerNotFoundInDataBaseExeption("There isn't a player with id " + id + " in the DataBase")));
    }

    public Mono<Player> getPlayerByName(String name) {
        if (name.isEmpty()) {
            return Mono.error(new PlayerNotFoundInDataBaseExeption("The name is null or invalid"));
        }
        return playerRepo.findByName(name).
                switchIfEmpty((Mono.empty()));
    }

    public Mono<Player> updatePlayer(Player player){
        if (player.getId() == 0) {
            return Mono.error(new PlayerNotFoundInDataBaseExeption("The id is null or invalid"));
        }

        return playerRepo.existsById(player.getId()).
                flatMap(exists -> {
                    if (exists) {
                        return playerRepo.save(player);
                    } else {
                        return Mono.error(new PlayerNotFoundInDataBaseExeption("The player " + player.getName() + " doesn't exist in the Database"));
                    }
                });
    }

    public Mono<Void> deletePlayer(int id) {
        if (id==0) {
                return Mono.error(new PlayerNotFoundInDataBaseExeption("The id is null or invalid"));
            }

            return playerRepo.existsById(id).
                    flatMap(exists -> {
                        if (exists) {
                            return playerRepo.deleteById(id);
                        } else {
                        return Mono.error(new PlayerNotFoundInDataBaseExeption("The player with id " + id + " doesn't exist in the Database"));
                    }
                });
    }

    public Mono<List<Player>> getPlayersGainRanking() {
        return playerRepo.findAll()
                .collectList()
                .map(players -> players.stream()
                        .sorted(Comparator.comparing(Player::getGains).reversed()).collect(Collectors.toList()));
    }


}
