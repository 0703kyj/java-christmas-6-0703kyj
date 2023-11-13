package christmas.domain.discount;

public interface Discount {
    int calculate();
    String getTitle();
    boolean canDiscount();
    default int getDiscountPrice(){
        return calculate();
    }
}
