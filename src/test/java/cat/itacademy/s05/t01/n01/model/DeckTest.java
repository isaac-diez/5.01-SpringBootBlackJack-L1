package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DeckTest {

    Deck newDeck = new Deck();


    @Test
    void getDeckSize() {
        int i = 0;
        for (Card card : newDeck.getDeckList()) {
            System.out.println("key: " + i + " card: " + newDeck.getDeckList().get(i).getName() + " of " + newDeck.getDeckList().get(i).getSuit());
            i++;
        }
        Assertions.assertEquals(48, newDeck.getDeckSize());


    }

    @Test
    void getCardNameFromDeck() {
        Assertions.assertEquals("ACE", newDeck.getDeckList().get(0).getName());
    }

    @Test
    void getCardSuitFromDeck() {
        Assertions.assertEquals("HEARTS",newDeck.getDeckList().get(0).getSuit());
    }

    @Test
    void getShuffledDeck() {
        Deck shuffledDeck = newDeck.shuffleDeck();

        int i = 0;
        for (Card j : newDeck.getDeckList()) {
            System.out.println("key: " + i + " card: " + shuffledDeck.getDeckList().get(i).getName() + " of " + shuffledDeck.getDeckList().get(i).getSuit());
        i++;
        }
    }

    @Test
    void removeCardFromDeck() {
        Deck shuffledDeck = newDeck.shuffleDeck();

        Assertions.assertEquals(48, shuffledDeck.getDeckSize());

        int i = 0;
        for (Card j : newDeck.getDeckList()) {
            System.out.println("key: " + i + " value: " + shuffledDeck.getDeckList().get(i).getName() + " of " + shuffledDeck.getDeckList().get(i).getSuit());
            i++;
        }
        System.out.println("");

        shuffledDeck.removeCardFromDeck();

        Assertions.assertEquals(47, shuffledDeck.getDeckSize());

        i = 0;
        for (Card j : shuffledDeck.getDeckList()) {
            System.out.println("key: " + i + " value: " + shuffledDeck.getDeckList().get(i).getName() + " of " + shuffledDeck.getDeckList().get(i).getSuit());
            i++;
        }

    }
}