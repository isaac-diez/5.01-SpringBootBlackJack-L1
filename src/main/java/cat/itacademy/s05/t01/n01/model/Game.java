package cat.itacademy.s05.t01.n01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.Iterator;
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
    private Deck deck;
    private int bet;

    public void game(Player player) {
        this.player = player;
        this.croupier = new Player("Croupier", PlayerType.CROUPIER);
        this.deck = new Deck();
    }

    public void gamePreparation() {
        this.deck.shuffleDeck();
    }

    public Integer getPlayerBet(int bet) {
        return bet;
    }

    public Card drawCard(Deck shuffledDeck){

        Iterator<Card> iteratorShuffledDeck = shuffledDeck.getDeckMap().values().iterator();

        if (!iteratorShuffledDeck.hasNext()) {
            throw new NoSuchElementException();
        }
        return shuffledDeck.removeCardFromDeck(1);
    }



}
