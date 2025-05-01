package cat.itacademy.s05.t01.n01.model;

import java.util.HashMap;
import java.util.Map;

public class Deck {

    private Map<Integer,Card> deck;

    public Deck() {
        this.deck = new HashMap<>();
    }

    public void newDeck() {

        int i=1;
        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(1,new Card(cardNumber.name(),CardSuit.HEARTS.name()));
            i++;
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(1,new Card(cardNumber.name(),CardSuit.CLUBS.name()));
            i++;
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(1,new Card(cardNumber.name(),CardSuit.DIAMONDS.name()));
            i++;
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(1,new Card(cardNumber.name(),CardSuit.SPADES.name()));
            i++;
        }

        for (Integer j : deck.keySet()) {
            System.out.println("key: " + j + " value: " + deck.get(i));
        }

    }

}
