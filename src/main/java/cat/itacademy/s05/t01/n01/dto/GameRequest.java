package cat.itacademy.s05.t01.n01.dto;

public class GameRequest {
    private String playerName;
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

