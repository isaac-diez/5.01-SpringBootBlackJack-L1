package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.dto.GameRequest;
import cat.itacademy.s05.t01.n01.exception.NoGamesInTheDatabaseException;
import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Operation(summary = "Create new BlackJack game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))),
            @ApiResponse(responseCode = "400", description = "Invalid player name or bet",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> createGame(@RequestBody GameRequest request) {
        return gameService.createGame(request.getPlayerName(), request.getInitialBet())
                .map(game -> ResponseEntity.status(HttpStatus.CREATED).body(game));
    }

    @Operation(summary = "Get details of an already played BlackJack game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))),
            @ApiResponse(responseCode = "400", description = "Invalid game ID",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Game>> getGameById(@PathVariable String id) {
        return gameService.getGameById(id)
                .map(game -> ResponseEntity.status(HttpStatus.OK).body(game))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Play a created game with ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game played successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))),
            @ApiResponse(responseCode = "400", description = "Invalid game ID",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "204", description = "Database is EMPTY",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{id}/play")
    public Mono<ResponseEntity<Game>> playGame(@PathVariable String id) {
        System.out.println("Playing game with ID: " + id);
        return gameService.playGame(id).
                map(game -> ResponseEntity.status(HttpStatus.OK).body(game));
    }

    @Operation(summary = "Get all played games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of played games retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))),
            @ApiResponse(responseCode = "400", description = "Invalid player name or bet",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "204", description = "Database is EMPTY",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/getAll")
    public Mono<ResponseEntity<List<Game>>> getAllGames() {
        return gameService.getAllGames()
                .map(games -> ResponseEntity.ok().body(games));
    }

    @Operation(summary = "Delete a game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))),
            @ApiResponse(responseCode = "400", description = "Game ID not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "The game id is null or invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable String id) {
        return gameService.deleteGame(id).
                thenReturn(ResponseEntity.ok().build());
    }

}
