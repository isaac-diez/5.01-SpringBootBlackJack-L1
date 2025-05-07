package cat.itacademy.s05.t01.n01.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();
    private int value = this.getHandValue();
    private Player player;
    private boolean isSplitHand;

    public Hand(Player player) {
        this.player = player;
    }

    public void addCardToHand(Card card) {
        this.getHand().add(card);
    }

    public void removeCardFromHand(Card card) {
        this.getHand().remove(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean isSplitHand() {
        return true;
    }

    public int getHandValue() {

        value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.isAce()){
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        if (value > 21) {
            isBust();
        }

        return value;
    }

    public boolean isBust() {
        return true;
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && getHandValue() == 21;
    }

    public List<Card> getHandList() {
        return hand;
    }
}
