package cat.itacademy.s05.t01.n01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Deck newDeck = new Deck();

    @Test
    void gameInit() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);

        Game gameTest = new Game(Isaac, 50);

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

    @Test
    void getPlayerBet() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);

        Game gameTest = new Game(Isaac, 50);

        gameTest.gameInit();


        System.out.println("Bets: " + gameTest.getPlayerTotalBet());


        Assertions.assertEquals(50,gameTest.getPlayerTotalBet());

//        gameTest.getPlayerHand(PlayerType.PLAYER).addCardtoHand(gameTest.drawCard(gameTest.getDeck()));
    }

    @Test
    void getPlayerTotalBet() {
    }

    @Test
    void gameMainPart() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);

        Game gameTest = new Game(Isaac, 50);

        int i = 0;
        for (Card card : gameTest.getDeck().getDeckList()) {
            System.out.println("key: " + i + " card: " + gameTest.getDeck().getDeckList().get(i).getName() +
                    " of " + gameTest.getDeck().getDeckList().get(i).getSuit());
            i++;
        }
        System.out.println();

        Assertions.assertEquals(48, newDeck.getDeckSize());

        gameTest.gameInit();

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().size());

        System.out.println("Player Hand");
        int j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandValue());

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().size());

        System.out.println();
        System.out.println("Croupier Hand");
        j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandValue());

        Assertions.assertEquals(44,gameTest.getDeck().getDeckSize());

        gameTest.gameMainPart(gameTest.getPlayerHand(PlayerType.PLAYER));

        if (gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().size() > 2) {

            j=0;
            for (Card card : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
                System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getName()
                        + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getSuit());
                j++;
            }

        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandValue());

        System.out.println("Hand Winner: " + gameTest.handWinner(gameTest.getPlayerHand(PlayerType.PLAYER)));

    }

    @Test
    void gameAfterSplitDecision() {

        Player Isaac = new Player("Isaac", PlayerType.PLAYER);

        Game gameTest = new Game(Isaac, 50);

        gameTest.setPlayerBet(50);

        Card card1 = new Card(CardName.EIGHT, CardSuit.CLUBS);
        Card card2 = new Card(CardName.EIGHT,CardSuit.SPADES);

        gameTest.getPlayerHand(PlayerType.PLAYER).addCardToHand(card1);
        gameTest.getPlayerHand(PlayerType.PLAYER).addCardToHand(card2);

        Card card3 = new Card(CardName.THREE, CardSuit.HEARTS);
        Card card4 = new Card(CardName.KING,CardSuit.DIAMONDS);

        gameTest.getPlayerHand(PlayerType.CROUPIER).addCardToHand(card3);
        gameTest.getPlayerHand(PlayerType.CROUPIER).addCardToHand(card4);

        gameTest.getDeck().getDeckList().remove(card1);
        gameTest.getDeck().getDeckList().remove(card2);
        gameTest.getDeck().getDeckList().remove(card3);
        gameTest.getDeck().getDeckList().remove(card4);

        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.PLAYER).getHand().size());
        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.CROUPIER).getHand().size());


        gameTest.gameMainPart(gameTest.getPlayerHand(PlayerType.PLAYER));

        System.out.println("Player Hand1");
        int j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value 1: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandValue());

        j=0;
        for (Card card : gameTest.getPlayerSplitHand().getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerSplitHand().getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerSplitHand().getHandList().get(j).getSuit());
            j++;
        }
        System.out.println("Split Hand value: " + gameTest.getPlayerSplitHand().getHandValue());


        System.out.println("Croupier Hand");
        j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getSuit());
            j++;
        }

        gameTest.gameAfterStandDecision();

//        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandValue());
//
//
//
//        System.out.println("Hand Winner: " + gameTest.handWinner(gameTest.getPlayerHand(PlayerType.PLAYER)));



    }

    @Test
    void playGame() {
        Player Isaac = new Player("Isaac", PlayerType.PLAYER);

        Game gameTest = new Game(Isaac, 50);

//        int i = 0;
//        for (Card card : gameTest.getDeck().getDeckList()) {
//            System.out.println("key: " + i + " card: " + gameTest.getDeck().getDeckList().get(i).getName() +
//                    " of " + gameTest.getDeck().getDeckList().get(i).getSuit());
//            i++;
//        }
//        System.out.println();
//
//        Assertions.assertEquals(48, newDeck.getDeckSize());

        gameTest.playGame();

//        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().size());

        System.out.println("Player Hand");
        int j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.PLAYER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.PLAYER).getHandValue());

//        Assertions.assertEquals(2,gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().size());

        System.out.println();
        System.out.println("Croupier Hand");
        j=0;
        for (Card card : gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList()) {
            System.out.println("key: " + j + " value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getName()
                    + " of " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandList().get(j).getSuit());
            j++;
        }

        System.out.println("Hand value: " + gameTest.getPlayerHand(PlayerType.CROUPIER).getHandValue());

//        Assertions.assertEquals(44,gameTest.getDeck().getDeckSize());


        System.out.println("Hand Winner: " + gameTest.handWinner(gameTest.getPlayerHand(PlayerType.PLAYER)));

    }
}