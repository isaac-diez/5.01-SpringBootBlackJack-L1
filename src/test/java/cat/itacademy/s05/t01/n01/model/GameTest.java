package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Deck newDeck = new Deck();

    @Test
    void gameInit() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);

        Game gameTest = new Game(Isaac);

        int i = 0;
        for (Card card : gameTest.getDeck().getDeckList()) {
            System.out.println("key: " + i + " card: " + gameTest.getDeck().getDeckList().get(i).getName() +
                    " of " + gameTest.getDeck().getDeckList().get(i).getSuit());
            i++;
        }
        Assertions.assertEquals(48, newDeck.getDeckSize());

        gameTest.gameInit();

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().size());

        int j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandValue());

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().size());

        j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandValue());

        Assertions.assertEquals(44,gameTest.getDeck().getDeckSize());


    }
}