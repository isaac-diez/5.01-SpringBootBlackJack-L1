package cat.itacademy.s05.t01.n01.session;

import cat.itacademy.s05.t01.n01.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class GameSessionContextTest {

    private Player Isaac;
    private Game gameTest;
    private GameSessionContext sessionContext;

    @BeforeEach
    void setUp() {
        Isaac = new Player("Isaac");
        Isaac.setId(1);
        gameTest = new Game(Isaac.getId(), 50);

    }

    @Test
    void startGame() {
    }

    @Test
    void populateDeck() {
        Player Isaac = new Player("Isaac");
        Isaac.setId(1);

        Game gameTest = new Game(Isaac.getId(), 50);
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);

        sessionContext.populateDeck();

        Assertions.assertEquals(48,sessionContext.getDeck().getDeckList().size());

    }

    @Test
    void shuffleDeck() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);
        sessionContext.shuffleDeck();

        int i = 0;
        for (Card card : sessionContext.getDeck().getDeckList()) {
            System.out.println("key: " + i + " card: " + sessionContext.getDeck().getDeckList().get(i).getName() +
                    " of " + sessionContext.getDeck().getDeckList().get(i).getSuit());
            i++;
        }

    }

    @Test
    void removeCardFromDeck() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);

        sessionContext.shuffleDeck();

        sessionContext.removeCardFromDeck();

        Assertions.assertEquals(47,sessionContext.getDeck().getDeckList().size());

    }

    @Test
    void getHandValue() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);
        sessionContext.gameInit();

        Assertions.assertEquals(2,sessionContext.getPlayerHand().getHandList().size());
        Assertions.assertEquals(2,sessionContext.getCroupierHand().getHandList().size());

        System.out.println("Player hand value: " + sessionContext.getHandValue(sessionContext.getPlayerHand()));
        System.out.println("Player hand value: " + sessionContext.getHandValue(sessionContext.getCroupierHand()));

    }

    @Test
    void isAce() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);

        Card card1 = new Card(CardName.ACE,CardSuit.CLUBS);
        Card card2 = new Card(CardName.EIGHT,CardSuit.SPADES);

        Assertions.assertTrue(sessionContext.isAce(card1));
        Assertions.assertFalse(sessionContext.isAce(card2));
    }

    @Test
    void isBust() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);

        sessionContext.gameInit();

        Card card1 = new Card(CardName.JACK,CardSuit.CLUBS);
        Card card2 = new Card(CardName.KING,CardSuit.SPADES);

        sessionContext.getPlayerHand().getHand().add(card1);
        sessionContext.getPlayerHand().getHand().add(card1);

        Assertions.assertTrue(sessionContext.isBust(sessionContext.getPlayerHand()));
    }



    @Test
    void playHand() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);

        sessionContext.startGame();

        System.out.println("Player hand:");
        int i = 0;
        for (Card card : sessionContext.getPlayerHand().getHand()) {
            System.out.println("key: " + i + " card: " + sessionContext.getPlayerHand().getHand().get(i).getName() +
                    " of " + sessionContext.getPlayerHand().getHand().get(i).getSuit());
            i++;
        }
        System.out.println("Player hand value " + sessionContext.getHandValue(sessionContext.getPlayerHand()));

        if (sessionContext.getSplitHand() != null) {
            i = 0;
            for (Card card : sessionContext.getDeck().getDeckList()) {
                System.out.println("key: " + i + " card: " + sessionContext.getSplitHand().getHand().get(i).getName() +
                        " of " + sessionContext.getSplitHand().getHand().get(i).getSuit());
                i++;
            }
            System.out.println("Player hand value " + sessionContext.getHandValue(sessionContext.getPlayerHand()));
            System.out.println("Player split hand value " + sessionContext.getHandValue(sessionContext.getSplitHand()));

        }


        System.out.println("Croupier Hand:");
        IntStream.range(0, sessionContext.getCroupierHand().getHand().size())
                .forEach(index -> {
                    Card card = sessionContext.getCroupierHand().getHand().get(index);
                    System.out.println("key: " + index + " card: " + card.getName() + " of " + card.getSuit());
                });

        System.out.println("Croupier hand value " + sessionContext.getHandValue(sessionContext.getCroupierHand()));


        System.out.println("Ganador de Mano: " + gameTest.getWinner());
        System.out.println("Ganancias: " + Isaac.getGains());

    }

    @Test
    void splitGame() {
        setUp();
        GameSessionContext sessionContext = new GameSessionContext(gameTest,Isaac);

        Card card1 = new Card(CardName.JACK,CardSuit.CLUBS);
        Card card2 = new Card(CardName.JACK,CardSuit.SPADES);

        sessionContext.getPlayerHand().getHand().add(card1);
        sessionContext.getPlayerHand().getHand().add(card1);

        sessionContext.shuffleDeck();

        sessionContext.getDeck().getDeckList().remove(card1);
        sessionContext.getDeck().getDeckList().remove(card2);

        sessionContext.playHand(sessionContext.getPlayerHand());


        System.out.println("Player hand:");
        int i = 0;
        for (Card card : sessionContext.getPlayerHand().getHand()) {
            System.out.println("key: " + i + " card: " + sessionContext.getPlayerHand().getHand().get(i).getName() +
                    " of " + sessionContext.getPlayerHand().getHand().get(i).getSuit());
            i++;
        }
        System.out.println("Player hand value " + sessionContext.getHandValue(sessionContext.getPlayerHand()));

        if (sessionContext.getSplitHand() != null) {
            i = 0;
            for (Card card : sessionContext.getSplitHand().getHand()) {
                System.out.println("key: " + i + " card: " + sessionContext.getSplitHand().getHand().get(i).getName() +
                        " of " + sessionContext.getSplitHand().getHand().get(i).getSuit());
                i++;
            }
            System.out.println("Player split hand value " + sessionContext.getHandValue(sessionContext.getSplitHand()));

        }

        boolean playerBust = sessionContext.isBust(sessionContext.getPlayerHand());
        boolean splitBust = sessionContext.getSplitHand() != null && sessionContext.isBust(sessionContext.getSplitHand());

        boolean allBust = playerBust && (sessionContext.getSplitHand() == null || splitBust);

        if (allBust) {
            sessionContext.evaluateLoser(sessionContext.getPlayerHand());
            if (sessionContext.getSplitHand() != null) sessionContext.evaluateLoser(sessionContext.getSplitHand());
        } else {
            sessionContext.concludeGame();
        }

        System.out.println("Croupier Hand:");
        IntStream.range(0, sessionContext.getCroupierHand().getHand().size())
                .forEach(index -> {
                    Card card = sessionContext.getCroupierHand().getHand().get(index);
                    System.out.println("key: " + index + " card: " + card.getName() + " of " + card.getSuit());
                });

        System.out.println("Croupier hand value " + sessionContext.getHandValue(sessionContext.getCroupierHand()));


        System.out.println("Ganador de Mano: " + gameTest.getWinner());
        System.out.println("Ganancias: " + Isaac.getGains());
    }

    @Test
    void getCroupierHand() {
    }

    @Test
    void getSplitHand() {
    }

    @Test
    void getGame() {
    }
}