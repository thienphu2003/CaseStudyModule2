package Worker;

public enum Type {
    FULLTIME(320000),
    PARTTIME(160000);
    private int value;

    private Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
