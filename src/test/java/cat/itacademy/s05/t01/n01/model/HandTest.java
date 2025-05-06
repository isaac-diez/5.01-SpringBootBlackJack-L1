package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void getHandList() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);
        Game gameTest = new Game(Isaac);
        gameTest.gameInit();

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().size());

        int i = 0;
        for (Card j : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
            System.out.println("key: " + i + " value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(i).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(i).getSuit());
            i++;
        }

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().size());

        i=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList()) {
            System.out.println("key: " + i + " value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(i).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(i).getSuit());
            i++;
        }

    }

    @Test
    void getHandValue() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);
        Game gameTest = new Game(Isaac);
        gameTest.gameInit();

        System.out.println("Player Hand:");
        int i = 0;
        for (Card j : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
            System.out.println("key: " + i + " card: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(i).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(i).getSuit());
            i++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandValue());

        System.out.println();

        System.out.println("Croupier Hand:");
        i = 0;
        for (Card j : gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList()) {
            System.out.println("key: " + i + " card: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(i).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(i).getSuit());
            i++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandValue());

    }
}