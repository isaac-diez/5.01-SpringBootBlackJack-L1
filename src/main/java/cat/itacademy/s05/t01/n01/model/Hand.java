package cat.itacademy.s05.t01.n01.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();
    private int value;
    private Player player;

    public Hand(Player player) {
        this.player = player;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public int getHandValue() {

        this.value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            this.value += card.getValue();
            if (card.isAce()){
                aceCount++;
            }
        }

        while (this.value > 21 && aceCount > 0) {
            this.value -= 10;
            aceCount--;
        }

        if (this.value > 21) {
            isBust();
        }

        return this.value;
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
