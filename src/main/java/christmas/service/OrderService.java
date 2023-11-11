package christmas.service;

import christmas.domain.Order;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.resource.menu.DrinkValue;
import christmas.resource.menu.MenuValue;
import christmas.util.DecimalFormatter;

public class OrderService {

    private static final int GIVEAWAY_PRICE = 120000;
    private static final int GIVEAWAY_COUNT = 1;
    private static final String GIVEAWAY_CANNOT_GIVE = "없음";
    private static final MenuValue GIVEAWAY = DrinkValue.CHAMPAGNE;
    private static final Menu giveawayMenu = new Drink(GIVEAWAY.getName(), GIVEAWAY.getPrice());
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

    public String getGiveaway() {
        if(order.getTotalPrice()>GIVEAWAY_PRICE){
            giveawayMenu.order(GIVEAWAY_COUNT);
            return giveawayMenu.toString();
        }
        return GIVEAWAY_CANNOT_GIVE;
    }
}
