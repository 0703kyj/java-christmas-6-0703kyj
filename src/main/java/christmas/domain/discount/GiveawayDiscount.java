package christmas.domain.discount;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.resource.menu.DrinkValue;
import christmas.resource.menu.MenuValue;

public class GiveawayDiscount {

    private static final int GIVEAWAY_PRICE = 120000;
    private static final int GIVEAWAY_COUNT = 1;
    private static final String GIVEAWAY_CANNOT_GIVE = "없음";
    private static final MenuValue GIVEAWAY = DrinkValue.CHAMPAGNE;
    private static final Menu giveawayMenu = new Drink(GIVEAWAY.getName(), GIVEAWAY.getPrice());
    private final int totalPrice;

    public GiveawayDiscount(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String calculateDiscount(){
        if (canGiveaway()) {
            giveawayMenu.order(GIVEAWAY_COUNT);
            return giveawayMenu.toString();
        }
        return GIVEAWAY_CANNOT_GIVE;
    }

    private boolean canGiveaway() {
        return totalPrice > GIVEAWAY_PRICE;
    }
}
