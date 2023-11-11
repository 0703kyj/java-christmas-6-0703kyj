package christmas.service;

import christmas.domain.Order;
import christmas.domain.discount.Discount;
import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    private Order order;
    private List<Discount> discounts;

    public DiscountService(Order order) {
        this.order = order;
        this.discounts = new ArrayList<>();
    }

    public List<Discount> getTotalDiscount() {
        return discounts;
    }

    public void calculateDiscount(Discount discount) {

        if (!discount.canDiscount()) {
            return;
        }
        discounts.add(discount);
    }
}
