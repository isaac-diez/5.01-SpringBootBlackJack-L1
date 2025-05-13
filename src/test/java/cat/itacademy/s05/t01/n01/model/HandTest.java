package cat.itacademy.s05.t01.n01.model;

import cat.itacademy.s05.t01.n01.session.GameSessionContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void getHandList() {

        Player Isaac = new Player("Isaac");
        Game gameTest = new Game(Isaac.getId(), 50);
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);
        sessionContext.startGame();

        Assertions.assertEquals(2,gameTest.getPlayerHand().size());

        int i = 0;
        for (Card j : gameTest.getPlayerHand()) {
            System.out.println("key: " + i + " value: " + gameTest.getPlayerHand().get(i).getName()
                    + " of " + gameTest.getPlayerHand().get(i).getSuit());
            i++;
        }

        Assertions.assertEquals(2,gameTest.getPlayerHand().size());

        i=0;
        for (Card card : gameTest.getCroupierHand()) {
            System.out.println("key: " + i + " value: " + gameTest.getCroupierHand().get(i).getName()
                    + " of " + gameTest.getCroupierHand().get(i).getSuit());
            i++;
        }

    }

    @Test
    void getHandValue() {

        Player Isaac = new Player("Isaac");
        Game gameTest = new Game(Isaac.getId(), 50);
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);
        sessionContext.startGame();

        System.out.println("Player Hand:");
        int i = 0;
        for (Card j : gameTest.getPlayerHand()) {
            System.out.println("key: " + i + " card: " + gameTest.getPlayerHand().get(i).getName()
                    + " of " + gameTest.getPlayerHand().get(i).getSuit());
            i++;
        }

 //       System.out.println("Hand value: " + gameTest.getPlayerHand().getHandValue());

        System.out.println();

        System.out.println("Croupier Hand:");
        i = 0;
        for (Card j : gameTest.getCroupierHand()) {
            System.out.println("key: " + i + " card: " + gameTest.getCroupierHand().get(i).getName()
                    + " of " + gameTest.getCroupierHand().get(i).getSuit());
            i++;
        }

//        System.out.println("Hand value: " + gameTest.getPlayerHand().getHandValue());

    }
}