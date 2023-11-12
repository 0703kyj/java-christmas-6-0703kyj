package christmas.domain.discount;

import christmas.util.DecimalFormatter;

public interface Discount {
    int EVENT_TRIGGER = 10000;
    int calculate();
    String getTitle();
    boolean canDiscount();
    default String getDiscountPrice(){
        return DecimalFormatter.format(calculate());
    }
}
