package cat.itacademy.s05.t01.n01.model;

import java.util.*;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public Deck() {
        this.populateDeck();
    }

    public void populateDeck() {

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.add(new Card((String) cardNumber.name(),(String) CardSuit.HEARTS.name()));
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.add(new Card(cardNumber.name(),CardSuit.CLUBS.name()));
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.add(new Card(cardNumber.name(),CardSuit.DIAMONDS.name()));
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.add(new Card(cardNumber.name(),CardSuit.SPADES.name()));
        }

    }

    public int getDeckSize(){
        return this.deck.size();
    }

    public Deck shuffleDeck() {

        Deck shuffledDeck = new Deck();
        Collections.shuffle(shuffledDeck.getDeckList());
        return shuffledDeck;

    }

    public void addCardInDeck(Card card) {
        this.deck.add(card);
    }

    public List<Card> getDeckList() {
        return this.deck;
    }

    public Card removeCardFromDeck(){
        return this.deck.removeFirst();
    }

    public Card getFirstCard() {
        return this.deck.get(1);
    }
}
