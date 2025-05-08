package cat.itacademy.s05.t01.n01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Table(name="games")

public class Game {

    //get player & croupier
    //place player bet
    //draw player cards, calculate result, if player not-bust, take decision
    //draw croupier cards, calculate result
    //calculate game winner

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    private Player player;
    private Player croupier;
    private Deck deck = new Deck();
    private int initialBet;
    private List<Integer> betTotal;
    private Hand playerHand;
    private Hand croupierHand;
    private Hand splitHand;
    private PlayerDecision playerDecision;

    public Game(Player player, int initialBet) {
        this.player = player;
        this.croupier = new Player("Croupier", PlayerType.CROUPIER);
        this.deck = this.deck.shuffleDeck();
        this.playerHand = new Hand(this.player);
        this.croupierHand = new Hand(this.croupier);
        this.initialBet = initialBet;
        this.betTotal = new ArrayList<>();
    }

    public void playGame() {
        this.gameInit();                        // reparte cartas
        this.gameMainPart(playerHand);         // juega mano principal

        if (this.splitHand != null) {
            this.gameMainPart(splitHand);      // si hay mano split, la juega
        }

        this.gameAfterStandDecision();         // luego juega el croupier y se evalúan ganadores
    }


    public void gameInit() {

        this.setPlayerBet(this.initialBet);

        //Draw cards for player
        this.playerHand.addCardToHand(drawCard(this.deck));
        this.playerHand.addCardToHand(drawCard(this.deck));

        //Draw cards for croupier
        this.croupierHand.addCardToHand(drawCard(this.deck));
        this.croupierHand.addCardToHand(drawCard(this.deck));

    }

    public void gameMainPart(Hand handToPlay) {
        if (getPlayerTotalBet() == 0) {
            throw new RuntimeException("Initial bet is not set.");
        }

        // Split solo si se está jugando la mano original (no splitHand)
        if (handToPlay == playerHand &&
                handToPlay.getHandList().size() == 2 &&
                handToPlay.getHandList().get(0).getValue() == handToPlay.getHandList().get(1).getValue()) {
            gameAfterSplitDecision();
            return;
        }

        // Double down si valor es 9, 10, 11
        if (handToPlay.getHandValue() >= 9 && handToPlay.getHandValue() <= 11) {
            gameAfterDoubleDownDecision();
            return;
        }

        // Hit hasta que llegue a 17 o se pase
        while (handToPlay.getHandValue() < 17 && !handToPlay.isBust()) {
            gameAfterHitDecision(handToPlay);
        }

        // Si se planta, no hacemos nada más aquí
        gameAfterStandDecision();
    }



    public void setPlayerBet(int individualBet) {
        this.addBetToTotalBet(individualBet);

    }

    public boolean betIsSet(boolean set) {
        if (set) {
            return true;
        } else {
            return false;
        }
    }

    public void addBetToTotalBet(int individualBet) {
        betTotal.add(individualBet);
    }

    public Integer getPlayerTotalBet() {

        int fullBet = 0;
        for (int amountBet : betTotal) {
            fullBet += amountBet;
        }
        return fullBet;
    }

    public Card drawCard(Deck shuffledDeck){

        Iterator<Card> iteratorShuffledDeck = shuffledDeck.getDeckList().iterator();

        if (!iteratorShuffledDeck.hasNext()) {
            throw new NoSuchElementException();
        }
        return shuffledDeck.removeCardFromDeck();
    }

    public Hand getPlayerHand(PlayerType playerType) {

        if (playerType == PlayerType.PLAYER) {
            return this.playerHand;

        } else {
            return this.croupierHand;
        }
    }

    public Hand getPlayerSplitHand() {

            return this.splitHand;

    }

    public boolean isBust(boolean isBust) {
        if (isBust) {
            return true;
        } else {return false; }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getCroupier() {
        return croupier;
    }

    public Deck getDeck() {
        return deck;
    }

    public String handWinner(Hand hand) {
        int playerScore = hand.getHandValue();
        int croupierScore = croupierHand.getHandValue();

        if (playerScore > 21) return croupier.getName();
        if (croupierScore > 21) return player.getName();
        if (playerScore > croupierScore) return player.getName();
        if (playerScore < croupierScore) return croupier.getName();
        return "Empate";
    }

    public void gameAfterStandDecision() {
        // El croupier roba hasta alcanzar al menos 17 puntos
        while (croupierHand.getHandValue() < 17 && !croupierHand.isBust()) {
            croupierHand.addCardToHand(drawCard(this.deck));
        }

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Puntuación Croupier: " + croupierHand.getHandValue());

        // Evaluar mano principal del jugador
        System.out.println("Puntuación Jugador (principal): " + playerHand.getHandValue());
        System.out.println("Ganador mano principal: " + handWinner(playerHand));

        // Evaluar mano split si existe
        if (splitHand != null && !splitHand.getHand().isEmpty()) {
            System.out.println("Puntuación Jugador (split): " + splitHand.getHandValue());
            System.out.println("Ganador mano split: " + handWinner(splitHand));
        }
    }



    public void gameAfterHitDecision(Hand handToPlay) {
        handToPlay.addCardToHand(drawCard(this.deck));
    }


    public void gameAfterDoubleDownDecision() {

        this.setPlayerBet(initialBet);
        this.playerHand.addCardToHand(drawCard(this.deck));

    }

    public void gameAfterSplitDecision() {
        // Crear mano dividida
        splitHand = new Hand(this.player);

        // Sacar segunda carta de la mano original
        Card secondCard = playerHand.getHandList().get(1);
        playerHand.removeCardFromHand(secondCard);

        // Añadir esa carta a la mano dividida
        splitHand.addCardToHand(secondCard);

        // Añadir una carta a cada mano para completar el split
        playerHand.addCardToHand(drawCard(this.deck));
        splitHand.addCardToHand(drawCard(this.deck));

        // Apostar por la segunda mano
        this.setPlayerBet(initialBet);

        // Jugar ambas manos
        this.gameMainPart(playerHand);
        this.gameMainPart(splitHand);
    }




}
