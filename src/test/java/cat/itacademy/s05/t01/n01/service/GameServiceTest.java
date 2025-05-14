package cat.itacademy.s05.t01.n01.service;

import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.repository.GameRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepo gameRepo;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private GameService gameService;


    @Test
    void testCreateGame_NewPlayer() {
        // Arrange
        String playerName = "Isaac";
        int initialBet = 50;
        Player mockPlayer = new Player(playerName);
        mockPlayer.setId(1);

        Game expectedGame = new Game(mockPlayer.getId(), initialBet);

        when(playerService.getPlayerByName(playerName)).thenReturn(Mono.empty());
        when(playerService.createPlayer(any())).thenReturn(Mono.just(mockPlayer));
        when(gameRepo.save(any(Game.class))).thenReturn(Mono.just(expectedGame));

        Mono<Game> result = gameService.createGame(playerName, initialBet);

        StepVerifier.create(result)
                .expectNextMatches(game ->
                        game.getPlayerId() == mockPlayer.getId() &&
                                game.getBet() == initialBet)
                .verifyComplete();

        verify(playerService).getPlayerByName(playerName);
        verify(playerService).createPlayer(any(Player.class));
        verify(gameRepo).save(any(Game.class));
    }

    @Test
    void testGetGameById_NotFound() {

        String invalidId = "abc123";

        when(gameRepo.findById(invalidId)).thenReturn(Mono.empty());

        Mono<Game> result = gameService.getGameById(invalidId);

        StepVerifier.create(result)
                .expectErrorMatches(error ->
                        error instanceof IllegalArgumentException &&
                                error.getMessage().contains("There isn't a game with id"))
                .verify();

        verify(gameRepo).findById(invalidId);
    }

    @Test
    void playGame() {
    }
}