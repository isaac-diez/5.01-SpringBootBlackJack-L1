package cat.itacademy.s05.t01.n01.model;

public class Card {

    private String name;
    private String suit;
    private int value;

    public Card() {}

    public Card(CardName cardName, CardSuit cardSuit) {
        this.name = cardName.name();
        this.suit = cardSuit.name();
        this.value = CardValue.valueOf(name).getValue();
    }

    public String getName() { return name; }

    public String getSuit() {
        return suit;
    }

    public int getValue() { return value; }

}
