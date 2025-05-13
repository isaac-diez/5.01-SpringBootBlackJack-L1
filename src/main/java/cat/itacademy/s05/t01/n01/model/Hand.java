package cat.itacademy.s05.t01.n01.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand = new ArrayList<>();
    private Player player;
    private int bet;

    public Hand() {}

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

    public List<Card> getHandList() {
        return hand;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}

