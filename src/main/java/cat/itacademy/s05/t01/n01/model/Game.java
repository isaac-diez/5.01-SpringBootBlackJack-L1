package cat.itacademy.s05.t01.n01.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "games")
public class Game {

    @Id
    private String id;

    private int playerId;
    private int bet;
    private String play = "Stand";
    private int playerFinalScore;
    private int croupierFinalScore;
    private String winner;
    private boolean isFinished = false;
    private List<Card> playerHand = new ArrayList<>();
    private List<Card> croupierHand = new ArrayList<>();
    private List<Card> splitHand = new ArrayList<>();


    public Game() {}

    public Game(int playerId, int initialBet) {
        this.playerId = playerId;
        this.bet = initialBet;
    }

    public boolean isFinished() { return isFinished; }

    public void setFinished(boolean finished) { isFinished = finished; }

    public String getPlay() { return play; }

    public void setPlay(String play) { this.play = play; }

    public String getId() {
        return id;
    }

    public int getPlayerId() { return playerId; }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public List<Card> getCroupierHand() {
        return croupierHand;
    }

    public void setCroupierHand(List<Card> croupierHand) {
        this.croupierHand = croupierHand;
    }

    public List<Card> getSplitHand() {
        return splitHand;
    }

    public void setSplitHand(List<Card> splitHand) {
        this.splitHand = splitHand;
    }

    public int getPlayerFinalScore() {
        return playerFinalScore;
    }

    public void setPlayerFinalScore(int playerFinalScore) {
        this.playerFinalScore = playerFinalScore;
    }

    public int getCroupierFinalScore() {
        return croupierFinalScore;
    }

    public void setCroupierFinalScore(int croupierFinalScore) {
        this.croupierFinalScore = croupierFinalScore;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
