package christmas.service;

import christmas.domain.EventDate;
import christmas.domain.Order;
import christmas.domain.discount.DdayDiscount;
import christmas.domain.discount.Discount;
import christmas.domain.discount.GiveawayDiscount;
import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    private Order order;
    private List<Discount> discounts;

    public DiscountService(Order order) {
        this.order = order;
        this.discounts = new ArrayList<>();
    }

    public String getGiveaway() {
        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(order.getTotalPrice());
        return giveawayDiscount.calculateDiscount();
    }

    public List<Discount> getTotalDiscount() {
        return discounts;
    }

    public void calculateDdayDiscount(EventDate eventDate) {
        Discount discount = new DdayDiscount(eventDate);
        int discountPrice = discount.calculate();

        if (discountPrice == 0) {
            return;
        }
        discounts.add(discount);
    }
}
