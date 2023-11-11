package christmas.domain.discount;

import christmas.util.DecimalFormatter;

public interface Discount {
    int calculate();
    String getTitle();
    boolean canDiscount();
    default String getDiscountPrice(){
        return DecimalFormatter.format(calculate());
    }
}
