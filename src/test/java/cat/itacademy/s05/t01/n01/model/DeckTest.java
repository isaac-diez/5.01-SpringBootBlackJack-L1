package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DeckTest {

    Deck newDeck = new Deck();


    @Test
    void getDeckSize() {
        Assertions.assertEquals(48, newDeck.getDeckSize());
    }

    @Test
    void getCardNameFromDeck() {
        Assertions.assertEquals("ACE", newDeck.getDeckMap().get(1).getName());
    }

    @Test
    void getCardSuitFromDeck() {
        Assertions.assertEquals("HEARTS",newDeck.getDeckMap().get(1).getSuit());
    }
}