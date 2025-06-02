package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.exception.NoPlayersInTheDatabaseException;
import cat.itacademy.s05.t01.n01.exception.PlayerNotFoundInDataBaseExeption;
import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

@Service
public class PlayerService {

    private final PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Mono<Player> createPlayer(Player player) {
        return playerRepo.save(player);
    }

    public Mono<List<Player>> getAllPlayers() {
        return playerRepo.findAll()
                .collectList()
                .defaultIfEmpty(new ArrayList<>());
    }

    public Mono<Player> getPlayerById(int id) {
        if (id == 0) {
            return Mono.error(new IllegalArgumentException("Player ID cannot be null, empty, or invalid."));
        }
        return playerRepo.findById(id).
                switchIfEmpty(Mono.error(new PlayerNotFoundInDataBaseExeption("There isn't a player with id " + id + " in the DataBase")));
    }

    public Mono<Player> getPlayerByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Mono.error(new PlayerNotFoundInDataBaseExeption("Player name cannot be null or empty"));
        }
        return playerRepo.findByName(name);
    }

    public Mono<Player> updatePlayer(Player player){
        if (player == null || player.getId() == null) {
            return Mono.error(new IllegalArgumentException("Player ID for update cannot be null, empty, or invalid."));
        }

        return playerRepo.existsById(player.getId())
                .switchIfEmpty(Mono.error(new PlayerNotFoundInDataBaseExeption("Player with ID " + player.getId() + " not found for update.")))
                .flatMap(existingPlayer -> {
                    return playerRepo.save(player);
                });
    }

    public Mono<Void> deletePlayer(int id) {
        if (id == 0) {
            return Mono.error(new IllegalArgumentException("Player ID for deletion cannot be null, empty, or invalid."));
        }

        return playerRepo.findById(id)
                .switchIfEmpty(Mono.error(new PlayerNotFoundInDataBaseExeption("Player with ID " + id + " not found for deletion.")))
                .flatMap(playerToDelete -> playerRepo.deleteById(playerToDelete.getId()));
    }

    public Mono<List<Player>> getPlayersGainRanking() {
        return playerRepo.findAll()
                .collectList()
                .map(players -> players.stream()
                        .sorted(Comparator.comparing(Player::getGains).reversed())
                        .collect(Collectors.toList()));
    }


}
