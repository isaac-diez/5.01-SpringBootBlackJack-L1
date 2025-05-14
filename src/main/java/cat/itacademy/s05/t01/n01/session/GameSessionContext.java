package cat.itacademy.s05.t01.n01.session;

import cat.itacademy.s05.t01.n01.model.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

    public class GameSessionContext {

        private final Game game;
        private final Player player;
        private final Player croupier;
        private final Deck deck;
        private final Hand playerHand;
        private final Hand croupierHand;
        private Hand splitHand;

        public GameSessionContext(Game game, Player player) {
            this.game = game;
            this.player = player;
            this.croupier = new Player();
            this.deck = new Deck();
            this.playerHand = new Hand(player);
            this.croupierHand = new Hand(croupier);
        }

        public void startGame() {
                gameInit();
                playHand(playerHand);

                if (splitHand != null) {
                    playHand(splitHand);
                }

                boolean playerBust = isBust(playerHand);
                boolean splitBust = splitHand != null && isBust(splitHand);

                boolean allBust = playerBust && (splitHand == null || splitBust);

                if (allBust) {
                    evaluateLoser(playerHand);
                    if (splitHand != null) evaluateLoser(splitHand);
                } else {
                    concludeGame();
                }
                afterGame();
        }

        public void afterGame() {

            this.game.setPlayerHand(getPlayerHand().getHandList());
            this.game.setCroupierHand(getCroupierHand().getHandList());
            if (getSplitHand() != null) {
                this.game.setSplitHand(getSplitHand().getHandList());
            }
            this.game.setPlayerFinalScore(getHandValue(getPlayerHand()));
            this.game.setCroupierFinalScore(getHandValue(getCroupierHand()));

        }


        public void populateDeck() {

            for (CardName cardName : CardName.values()) {
                this.deck.getDeckList().add(new Card(cardName, CardSuit.HEARTS));
            }

            for (CardName cardName : CardName.values()) {
                this.deck.getDeckList().add(new Card(cardName,CardSuit.CLUBS));
            }

            for (CardName cardName : CardName.values()) {
                this.deck.getDeckList().add(new Card(cardName,CardSuit.DIAMONDS));
            }

            for (CardName cardName : CardName.values()) {
                this.deck.getDeckList().add(new Card(cardName,CardSuit.SPADES));
            }

        }

        public Deck getDeck(){
            return this.deck;
        }

        public Deck shuffleDeck() {

            populateDeck();
            Collections.shuffle(this.deck.getDeckList());
            return this.deck;

        }
        private void gameInit() {

            shuffleDeck();
            playerHand.setBet(game.getBet());
            drawInitialCards(playerHand);
            drawInitialCards(croupierHand);
        }

        private void drawInitialCards(Hand hand) {

            hand.addCardToHand(drawCard());
            hand.addCardToHand(drawCard());
        }

        private Card drawCard() {
            Iterator<Card> iterator = deck.getDeckList().iterator();
            if (!iterator.hasNext()) throw new NoSuchElementException("Deck is empty");
            return removeCardFromDeck();
        }
        public Card removeCardFromDeck(){
            return this.deck.getDeckList().remove(0);
        }

        public int getHandValue(Hand hand) {
            int value = 0;
            int aceCount = 0;

            for (Card card : hand.getHandList()) {
                value += card.getValue();
                if (isAce(card)) {
                    aceCount++;
                }
            }

            while (value > 21 && aceCount > 0) {
                value -= 10;
                aceCount--;
            }

            return value;
        }

        public boolean isAce(Card card) {
            return card.getName().equals("ACE");
        }

        public boolean isBust(Hand hand) {
            return getHandValue(hand) > 21;
        }

        public boolean isBlackjack(Hand hand) {
            return hand.getHandList().size() == 2 && getHandValue(hand) == 21;
        }

        private void playHand(Hand hand) {
            if (hand == playerHand && canSplit(hand)) {
                this.game.setPlay("Split");
                performSplit();
                return;
            }

            if (hand != splitHand && getHandValue(hand) >= 9 && getHandValue(hand) <= 11) {
                this.game.setPlay("DoubleDown");
                performDoubleDown(hand);
                return;
            }

            while (getHandValue(hand) < 17 && !isBust(hand)) {
                this.game.setPlay("Hit");
                hand.addCardToHand(drawCard());
            }
        }


        private boolean canSplit(Hand hand) {
            return hand.getHandList().size() == 2 &&
                    hand.getHandList().get(0).getValue() == hand.getHandList().get(1).getValue();
        }

        private void performSplit() {
            splitHand = new Hand(player);
            splitHand.setBet(game.getBet());

            Card secondCard = playerHand.getHandList().get(1);
            playerHand.removeCardFromHand(secondCard);
            splitHand.addCardToHand(secondCard);

            playerHand.addCardToHand(drawCard());
            splitHand.addCardToHand(drawCard());

            playHand(playerHand);
            playHand(splitHand);
        }

        private void performDoubleDown(Hand hand) {
            game.setBet(game.getBet() * 2);
            hand.addCardToHand(drawCard());
        }
        private void concludeGame() {
            while (getHandValue(croupierHand) < 17 && !isBust(croupierHand)) {
                croupierHand.addCardToHand(drawCard());
            }

            evaluateWinner(playerHand);

            if (splitHand != null && !splitHand.getHand().isEmpty()) {
                evaluateWinner(splitHand);
            }
        }

        private void evaluateWinner(Hand hand) {
            int playerScore = getHandValue(hand);
            int croupierScore = getHandValue(croupierHand);
            int bet = hand.getBet();

            if (playerScore > 21) {
                player.setGains(-game.getBet());
                this.game.setWinner(croupier.getName());
            } else if (croupierScore > 21 || playerScore > croupierScore) {
                player.setGains(game.getBet());
                this.game.setWinner(player.getName());
            } else if (playerScore < croupierScore) {
                player.setGains(-game.getBet());
                this.game.setWinner(croupier.getName());
            }
        }

        private void evaluateLoser(Hand hand) {
            if (isBust(hand)) {
                player.setGains(player.getGains() - hand.getBet());
                game.setWinner("Croupier");
            }
        }

        public Hand getPlayerHand() {
            return playerHand;
        }

        public Hand getCroupierHand() {
            return croupierHand;
        }

        public Hand getSplitHand() {
            return splitHand;
        }

        public Game getGame() {
            return game;
        }
    }

