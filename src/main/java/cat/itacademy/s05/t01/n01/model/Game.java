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

public class Game implements Iterator<Integer> {

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

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return 0;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        Iterator.super.forEachRemaining(action);
    }

//    public Card drawCard(){
//        if (!hasNext()) {
//            throw new NoSuchElementException();
//        }
//        int cardId = this.deck.removeCardFromDeck(0);
//    }



}
