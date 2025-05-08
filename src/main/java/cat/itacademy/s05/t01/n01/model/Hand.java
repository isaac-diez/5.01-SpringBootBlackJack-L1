package cat.itacademy.s05.t01.n01.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();
    private Player player;
    private boolean isSplitHand;

    public Hand(Player player) {
        this.player = player;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean isSplitHand() {
        return isSplitHand;
    }

    public void setSplitHand(boolean splitHand) {
        isSplitHand = splitHand;
    }

    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.isAce()) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public boolean isBust() {
        return getHandValue() > 21;
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && getHandValue() == 21;
    }

    public List<Card> getHandList() {
        return hand;
    }
}

