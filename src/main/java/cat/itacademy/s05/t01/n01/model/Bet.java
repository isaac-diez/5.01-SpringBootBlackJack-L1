package cat.itacademy.s05.t01.n01.model;

public class Bet {

    private int betValue;

    public Bet(int betValue) {
        this.betValue = betValue;
    }

    public int getBetValue() {
        return betValue;
    }

    public void setBetValue(int betValue) {
        this.betValue = betValue;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betValue=" + betValue +
                '}';
    }
}
