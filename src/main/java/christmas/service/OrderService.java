package christmas.service;

import christmas.domain.Order;
import christmas.domain.discount.GiveawayDiscount;
import christmas.util.DecimalFormatter;

public class OrderService {

    private Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public String printOrder() {
        return order.toString();
    }

    public String getPriceBeforeDiscount() {
        return DecimalFormatter.format(order.getTotalPrice());
    }

    public int getTotalPrice() {
        return order.getTotalPrice();
    }

    public String getGiveaway() {
        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(order.getTotalPrice());
        return giveawayDiscount.getGiveaway();
    }
}
