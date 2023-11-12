package christmas.domain.discount;

public interface Discount {
    int EVENT_TRIGGER = 10000;
    int calculate();
    String getTitle();
    boolean canDiscount();
    default int getDiscountPrice(){
        return calculate();
    }
}
