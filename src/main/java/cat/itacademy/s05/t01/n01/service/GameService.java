package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.exception.GameAlreadyPlayedException;
import cat.itacademy.s05.t01.n01.exception.GameCreationParamsMissing;
import cat.itacademy.s05.t01.n01.exception.NoGamesInTheDatabaseException;
import cat.itacademy.s05.t01.n01.exception.NoPlayersInTheDatabaseException;
import cat.itacademy.s05.t01.n01.model.*;
import cat.itacademy.s05.t01.n01.repository.GameRepo;
import cat.itacademy.s05.t01.n01.session.GameSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
        if (playerName.isEmpty() || initialBet == 0) {
            return Mono.error(new GameCreationParamsMissing("The name of the player and/or the bet are null or invalid"));
        }
        return playerService.getPlayerByName(playerName)
                .switchIfEmpty(Mono.defer(() -> {
                    Player newPlayer = new Player(playerName);
                    return playerService.createPlayer(newPlayer);
                }))
                .flatMap(savedPlayer -> {
                    Game game = new Game(savedPlayer.getId(), initialBet);
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

    public Mono<Game> playGame(String gameId) {
        return gameRepo.findById(gameId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Game not found with ID: " + gameId)))
                .flatMap(game -> {
                    if (game.isFinished()) {
                        return Mono.error(new GameAlreadyPlayedException("This game has already been played."));
                    }

                    return playerService.getPlayerById(game.getPlayerId())
                            .flatMap(player -> {
                                GameSessionContext context = new GameSessionContext(game, player);
                                context.startGame();

                                game.setFinished(true);

                                return playerService.updatePlayer(player)
                                        .then(gameRepo.save(game));
                            });
                });
    }

    public Mono<List<Game>> getAllGames() {
        return gameRepo.findAll().
                collectList().
                flatMap(games -> {
                    if (games.isEmpty()) {
                        return Mono.error(new NoGamesInTheDatabaseException("The database is empty"));
                    }
                    return Mono.just(games);
                });

    }





}
