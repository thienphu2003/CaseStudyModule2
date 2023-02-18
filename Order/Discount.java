package Order;

public enum Discount {
    Twenty(20),
    Forty(40),
    Eighty(80);

    private int value;

    private Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
