package christmas.resource;

public enum DateValue {
    EVENT_YEAR(2023),
    EVENT_MONTH(12);

    private int value;

    DateValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
