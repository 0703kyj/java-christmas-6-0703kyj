package christmas.resource.discount;

public enum SpecialDateValue {
    CHRISTMAS(25);

    int day;

    SpecialDateValue(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
