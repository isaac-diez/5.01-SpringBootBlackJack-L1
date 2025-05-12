package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.model.PlayerType;
import cat.itacademy.s05.t01.n01.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameService {

    @Autowired
    private GameRepo gameRepo;
    private PlayerService playerService;

    public GameService(PlayerService playerService, GameRepo gameRepo) {
        this.playerService = playerService;
        this.gameRepo = gameRepo;
    }

    public Mono<Game> createGame(String playerName, int initialBet) {
        return playerService.getPlayerByName(playerName)
                .switchIfEmpty(Mono.defer(() -> {
                    Player newPlayer = new Player(playerName, PlayerType.PLAYER);
                    return playerService.createPlayer(newPlayer);
                }))
                .flatMap(savedPlayer -> {
                    Game game = new Game(savedPlayer, initialBet);
                    return gameRepo.save(game)
                        .doOnNext(savedGame -> System.out.println("Game saved: " + savedGame))
                        .doOnError(e -> System.err.println("Error saving game: " + e.getMessage()));
                    });
        }

    public Mono<Game> getGameById(String id) {
        if (id.isEmpty() || id.equals("0")) {
            return Mono.error(new IllegalArgumentException("The id is null or invalid"));
        }
        return gameRepo.findById(id).
                switchIfEmpty(Mono.error(new IllegalArgumentException("There isn't a game with id" + id + "in the DataBase")));
    }




}
