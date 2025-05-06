package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DeckTest {

    Deck newDeck = new Deck();


    @Test
    void getDeckSize() {
        newDeck.populateDeck();
        for (Integer j : newDeck.getDeckMap().keySet()) {
            System.out.println("key: " + j + " value: " + newDeck.getDeckMap().get(j).getName() + " of " + newDeck.getDeckMap().get(j).getSuit());
        }
        Assertions.assertEquals(48, newDeck.getDeckSize());


    }

    @Test
    void getCardNameFromDeck() {
        newDeck.populateDeck();
        Assertions.assertEquals("ACE", newDeck.getDeckMap().get(1).getName());
    }

    @Test
    void getCardSuitFromDeck() {
        newDeck.populateDeck();
        Assertions.assertEquals("HEARTS",newDeck.getDeckMap().get(1).getSuit());
    }

    @Test
    void getShuffledDeck() {
        newDeck.populateDeck();
        Deck shuffledDeck = newDeck.shuffleDeck();

        for (Integer j : shuffledDeck.getDeckMap().keySet()) {
            System.out.println("key: " + j + " value: " + shuffledDeck.getDeckMap().get(j).getName() + " of " + shuffledDeck.getDeckMap().get(j).getSuit());
        }
    }

    @Test
    void removeCardFromDeck() {
        newDeck.populateDeck();
        Deck shuffledDeck = newDeck.shuffleDeck();

        Assertions.assertEquals(48, shuffledDeck.getDeckSize());

        for (Integer j : newDeck.getDeckMap().keySet()) {
            System.out.println("key: " + j + " value: " + shuffledDeck.getDeckMap().get(j).getName() + " of " + shuffledDeck.getDeckMap().get(j).getSuit());
        }

        shuffledDeck.removeCardFromDeck();

        Assertions.assertEquals(47, shuffledDeck.getDeckSize());

        for (Integer j : shuffledDeck.getDeckMap().keySet()) {
            System.out.println("key: " + j + " value: " + shuffledDeck.getDeckMap().get(j).getName() + " of " + shuffledDeck.getDeckMap().get(j).getSuit());
        }

    }
}