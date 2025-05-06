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
import java.util.function.Consumer;

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
    private int bet;
    private List<Integer> betPool;
    private Hand playerHand;
    private Hand croupierHand;

    public Game(Player player) {
        this.player = player;
        this.croupier = new Player("Croupier", PlayerType.CROUPIER);
        this.deck = this.deck.shuffleDeck();
        this.playerHand = new Hand(this.player);
        this.croupierHand = new Hand(this.croupier);
        this.betPool = new ArrayList<>();
    }

    public void gameInit() {

        //Draw cards for player
        this.playerHand.addCardtoHand(drawCard(this.deck));
        this.playerHand.addCardtoHand(drawCard(this.deck));

        //Draw cards for croupier
        this.croupierHand.addCardtoHand(drawCard(this.deck));
        this.croupierHand.addCardtoHand(drawCard(this.deck));

    }

    public Integer getPlayerBet() {
        return bet;
    }

    public void addBetToBetPool(int bet) {

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
//
//    public Hand getCroupierHand(Player croupier) {
//
//        return this.playerHand;
//    }


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

    public void setBet(int bet) {
        this.bet = bet;
    }


}
