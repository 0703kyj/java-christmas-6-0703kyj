package christmas.service;

import christmas.domain.Order;
import christmas.domain.discount.GiveawayDiscount;
import christmas.domain.menu.Menu;
import christmas.util.DecimalFormatter;
import java.util.List;

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

    public int getCountOfDesserts(){
        int count = 0;
        List<Menu> desserts = order.getDesserts();
        for (Menu dessert : desserts) {
            count += dessert.getOrderCount();
        }
        return count;
    }

    public int getCountOfMainMenus(){
        int count = 0;
        List<Menu> mainMenus = order.getMainMenus();
        for (Menu mainMenu : mainMenus) {
            count += mainMenu.getOrderCount();
        }
        return count;
    }
}
