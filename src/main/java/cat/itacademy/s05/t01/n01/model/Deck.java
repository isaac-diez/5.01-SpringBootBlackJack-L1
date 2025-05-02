package cat.itacademy.s05.t01.n01.model;

import java.util.*;

public class Deck {

    private Map<Integer,Card> deck;

    public Deck() {
        this.deck = new HashMap<>();
        int i=1;
        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(i,new Card((String) cardNumber.name(),(String) CardSuit.HEARTS.name()));
            i++;
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(i,new Card(cardNumber.name(),CardSuit.CLUBS.name()));
            i++;
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(i,new Card(cardNumber.name(),CardSuit.DIAMONDS.name()));
            i++;
        }

        for (CardNumber cardNumber : CardNumber.values()) {
            this.deck.put(i,new Card(cardNumber.name(),CardSuit.SPADES.name()));
            i++;
        }

//        for (Integer j : deck.keySet()) {
//            System.out.println("key: " + j + " value: " + deck.get(j).getName() + " of " + deck.get(j).getSuit());
//        }
    }

    public int getDeckSize(){
        return this.deck.size();
    }

    public void shuffleDeck() {
        List<Integer> listOfCards = new ArrayList<>();

        int i;
        for (i = 1; i<49; i++) {
            listOfCards.add(i);
        }

        Collections.shuffle(listOfCards);
    }

    public void putCardInDeck(int key, Card card) {
        this.deck.put(key, card);
    }

    public Map<Integer, Card> getDeckMap() {
        return this.deck;
    }

    public void removeCardFromDeck(Integer key){

    }

}
