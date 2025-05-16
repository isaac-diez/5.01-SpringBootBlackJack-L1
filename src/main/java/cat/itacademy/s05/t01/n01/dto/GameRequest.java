package cat.itacademy.s05.t01.n01.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Petición para crear una partida")
public class GameRequest {
    @Schema(description = "Nombre del jugador", example = "Isaac")
    private String playerName;

    @Schema(description = "Apuesta inicial", example = "100")
    private int initialBet;

    public GameRequest(String playerName, int initialBet) {
        this.playerName = playerName;
        this.initialBet = initialBet;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getInitialBet() {
        return initialBet;
    }

    public void setInitialBet(int initialBet) {
        this.initialBet = initialBet;
    }
}

