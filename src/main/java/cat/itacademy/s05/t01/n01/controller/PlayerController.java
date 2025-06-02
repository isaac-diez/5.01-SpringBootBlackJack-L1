package cat.itacademy.s05.t01.n01.controller;

import cat.itacademy.s05.t01.n01.model.Game;
import cat.itacademy.s05.t01.n01.model.Player;
import cat.itacademy.s05.t01.n01.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/players")

public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Create a new player (do not specify id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New player created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "500", description = "Only enter name and type for the player",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/add")
    public Mono<Player> createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @Operation(summary = "Get all the players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "204", description = "Database is EMPTY",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/getAll")
    public Mono<List<Player>> getAll() {
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Get a player from their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Player not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/getOne/{id}")
    public Mono<Player> getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @Operation(summary = "Update player info from their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "Player not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/update")
    public Mono<Player> updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }

    @Operation(summary = "Delete a player from their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Player ID not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "The player id is null or invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePlayer(@PathVariable int id) {
        return playerService.deletePlayer(id);
    }



}
