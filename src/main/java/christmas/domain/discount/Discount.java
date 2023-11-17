package christmas.domain.discount;

public interface Discount {
    String TITLE_SUFFIX = ": ";

    int calculate();

    String getTitle();

    boolean canDiscount();

    default int getDiscountPrice() {
        return calculate();
    }
}
