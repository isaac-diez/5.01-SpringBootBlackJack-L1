package cat.itacademy.s05.t01.n01.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();
    private int result = 0;
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

        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            total += card.getValue();
            if (card.isAce()){
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        if (total > 21) {
            isBust();
        }

        return total;
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
