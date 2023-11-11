package christmas.service;

import christmas.domain.Order;
import christmas.domain.discount.Discount;
import christmas.util.DecimalFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    private Order order;
    private List<Discount> discounts;

    public DiscountService(Order order) {
        this.order = order;
        this.discounts = new ArrayList<>();
    }

    public List<Discount> getTotalDiscounts() {
        return discounts;
    }

    public String calculateTotalDiscountPrice(){
        int discountPrice = 0;

        for (Discount discount : discounts) {
            discountPrice += discount.calculate();
        }
        return DecimalFormatter.format(discountPrice);
    }

    public void calculateDiscount(Discount discount) {

        if (!discount.canDiscount()) {
            return;
        }
        discounts.add(discount);
    }
}
