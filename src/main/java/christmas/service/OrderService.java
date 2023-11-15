package christmas.service;

import christmas.domain.Order;
import christmas.domain.menu.Menu;
import christmas.exception.state.NotFoundOrderException;
import christmas.resource.BadgeValue;
import java.util.List;

public class OrderService {

    private Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public String printOrder() {
        checkCustomerOrdered();
        return order.toString();
    }

    public int getPriceBeforeDiscount() {
        checkCustomerOrdered();
        return order.getTotalPrice();
    }

    public int getCountOfDesserts() {
        int count = 0;

        checkCustomerOrdered();
        List<Menu> desserts = order.getDesserts();
        for (Menu dessert : desserts) {
            count += dessert.getOrderCount();
        }
        return count;
    }

    public int getCountOfMainMenus() {
        int count = 0;

        checkCustomerOrdered();
        List<Menu> mainMenus = order.getMainMenus();
        for (Menu mainMenu : mainMenus) {
            count += mainMenu.getOrderCount();
        }
        return count;
    }

    public String getBadge(int totalDiscountPrice) {
        return BadgeValue.getBadge(-1 * totalDiscountPrice);
    }

    private void checkCustomerOrdered() {
        if (!order.isCustomerOrdered()) {
            throw new NotFoundOrderException();
        }
    }
}
