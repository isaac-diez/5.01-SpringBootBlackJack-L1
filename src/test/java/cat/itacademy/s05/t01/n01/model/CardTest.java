package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getName() {
        Card newCard = new Card(CardNumber.ACE.name(), CardSuit.HEARTS.name());
        Assertions.assertEquals("ACE",newCard.getName());
    }

    @Test
    void getSuit() {
        Card newCard = new Card(CardNumber.ACE.name(), CardSuit.HEARTS.name());
        Assertions.assertEquals("HEARTS",newCard.getSuit());
    }
}