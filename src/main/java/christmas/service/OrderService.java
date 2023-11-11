package christmas.service;

import christmas.domain.Order;
import java.text.DecimalFormat;

public class OrderService {
    private Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public String printOrder() {
        return order.toString();
    }

    public DecimalFormat getPriceBeforeDiscount() {

        return null;
    }
}
