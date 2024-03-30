package pz1.michalczosnyka;

public enum Combination {
    POKER(9),
    QUAD(8),
    FULL(7),
    COLOR(6),
    STRAIGHT(5),
    THREE(4),
    TWO_PAIRS(3),
    PAIR(2),
    HIGHEST_CARD(1);

    private final int power;

    Combination(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

}
