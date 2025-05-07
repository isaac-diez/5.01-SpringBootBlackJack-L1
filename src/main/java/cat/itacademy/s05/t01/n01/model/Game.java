package cat.itacademy.s05.t01.n01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Table(name="games")

public class Game {

    //get player & croupier
    //place player bet
    //draw player cards, calculate result, if player not-bust, take decision
    //draw croupier cards, calculate result
    //calculate game winner

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    private Player player;
    private Player croupier;
    private Deck deck = new Deck();
    private int initialBet;
    private List<Integer> betTotal;
    private Hand playerHand;
    private Hand croupierHand;
    private Hand splitHand;
    private PlayerDecision playerDecision;

    public Game(Player player, int initialBet) {
        this.player = player;
        this.croupier = new Player("Croupier", PlayerType.CROUPIER);
        this.deck = this.deck.shuffleDeck();
        this.playerHand = new Hand(this.player);
        this.croupierHand = new Hand(this.croupier);
        this.initialBet = initialBet;
        this.betTotal = new ArrayList<>();
    }

    public void gameInit() {

        this.setPlayerBet(this.initialBet);

        //Draw cards for player
        this.playerHand.addCardToHand(drawCard(this.deck));
        this.playerHand.addCardToHand(drawCard(this.deck));

        //Draw cards for croupier
        this.croupierHand.addCardToHand(drawCard(this.deck));
        this.croupierHand.addCardToHand(drawCard(this.deck));

    }

    public void gameMainPart(Hand handToPlay) {

        if (getPlayerTotalBet() == 0) {
            throw new RuntimeException("Initial bet is not set.");
        }

        if (handToPlay.getHandList().size() == 2 && (handToPlay.getHandList().get(0).getValue() == handToPlay.getHandList().get(1).getValue())) {
            gameAfterSplitDecision();
        }

        if (handToPlay.getHandValue() > 11) {
            gameAfterDoubleDownDecision();
        }

        while (handToPlay.getHandValue() < 12) {
            gameAfterHitDecision();
        }

        if (handToPlay.getHandValue() > 18) {
            gameAfterStandDecision();
        }



    }

    public void setPlayerBet(int individualBet) {
        this.addBetToTotalBet(individualBet);

    }

    public boolean betIsSet(boolean set) {
        if (set) {
            return true;
        } else {
            return false;
        }
    }

    public void addBetToTotalBet(int individualBet) {
        betTotal.add(individualBet);
    }

    public Integer getPlayerTotalBet() {

        int fullBet = 0;
        for (int amountBet : betTotal) {
            fullBet += amountBet;
        }
        return fullBet;
    }

    public Card drawCard(Deck shuffledDeck){

        Iterator<Card> iteratorShuffledDeck = shuffledDeck.getDeckList().iterator();

        if (!iteratorShuffledDeck.hasNext()) {
            throw new NoSuchElementException();
        }
        return shuffledDeck.removeCardFromDeck();
    }

    public Hand getPlayerHand(PlayerType playerType) {

        if (playerType == PlayerType.PLAYER) {
            return this.playerHand;

        } else {
            return this.croupierHand;
        }
    }

    public Hand getPlayerSplitHand() {

            return this.splitHand;

    }

    public boolean isBust(boolean isBust) {
        if (isBust) {
            return true;
        } else {return false; }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getCroupier() {
        return croupier;
    }

    public Deck getDeck() {
        return deck;
    }

    public String handWinner() {

        if (this.getPlayerHand(PlayerType.PLAYER).getHandValue() > 21) {
            return this.croupier.getName();
        }

        if (this.getPlayerHand(PlayerType.PLAYER).getHandValue() < this.getPlayerHand(PlayerType.CROUPIER).getHandValue()) {
            return this.croupier.getName();
        }

        if (this.getPlayerHand(PlayerType.PLAYER).getHandValue() == this.getPlayerHand(PlayerType.CROUPIER).getHandValue()) {
            return "Empate";
        }
        return this.player.getName();
    }

    public void gameAfterStandDecision() {

        while (this.croupierHand.getHandValue() < 17 && this.croupierHand.isBust()) {
            this.croupierHand.addCardToHand(drawCard(this.deck));
        }

        this.handWinner();

        System.out.println("HAND WINNER: " + this.handWinner());

    }

    public void gameAfterHitDecision() {

        if (!splitHand.getHand().isEmpty()) {
            splitHand.addCardToHand(drawCard(this.deck));
        } else {
            this.playerHand.addCardToHand(drawCard(this.deck));
        }

    }

    public void gameAfterDoubleDownDecision() {

        this.setPlayerBet(initialBet);
        this.playerHand.addCardToHand(drawCard(this.deck));

    }

    public void gameAfterSplitDecision() {

        splitHand = new Hand(this.player);

//        splitHand.addCardToHand(this.getPlayerHand(PlayerType.PLAYER).getHandList().get(1));

        Card cardToAddToSplitHand = this.getPlayerHand(PlayerType.PLAYER).getHandList().get(1);

        playerHand.removeCardFromHand(this.getPlayerHand(PlayerType.PLAYER).getHandList().get(1));

        this.setPlayerBet(initialBet);

        this.gameMainPart(playerHand);

        splitHand.addCardToHand(cardToAddToSplitHand);

        this.gameMainPart(splitHand);

    }



}
