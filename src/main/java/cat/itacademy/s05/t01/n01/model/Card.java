package cat.itacademy.s05.t01.n01.model;

import java.util.Objects;

public class Card {

    private String name;
    private String suit;
    private int value;

    public Card(String name, String suit) {
        this.name = name;
        this.suit = suit;
        this.value = CardValue.valueOf(name).getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return this.name.equals("ACE");
    }
}
