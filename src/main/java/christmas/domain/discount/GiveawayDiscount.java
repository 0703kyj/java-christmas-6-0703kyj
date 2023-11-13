package christmas.domain.discount;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.resource.menu.DrinkValue;
import christmas.resource.menu.MenuValue;
import christmas.util.EventTrigger;

public class GiveawayDiscount implements Discount {

    private static final int GIVEAWAY_PRICE = 120000;
    private static final int GIVEAWAY_COUNT = 1;
    private static final String DISCOUNT_TITLE = "증정 이벤트";
    private static final MenuValue GIVEAWAY = DrinkValue.CHAMPAGNE;
    private final Menu giveawayMenu = new Drink(GIVEAWAY.getName(), GIVEAWAY.getPrice());
    private final int beforeDiscountPrice;

    public GiveawayDiscount(int beforeDiscountPrice) {
        this.beforeDiscountPrice = beforeDiscountPrice;
    }

    public String getGiveaway() {
        if (canDiscount()) {
            giveawayMenu.order(GIVEAWAY_COUNT);
            return giveawayMenu.toString();
        }
        return null;
    }

    @Override
    public int calculate() {
        return giveawayMenu.getPrice();
    }

    @Override
    public String getTitle() {
        return DISCOUNT_TITLE + TITLE_SUFFIX;
    }

    @Override
    public boolean canDiscount() {
        return isOverGiveawayPrice() && EventTrigger.canTrigger(beforeDiscountPrice);
    }

    private boolean isOverGiveawayPrice() {
        return beforeDiscountPrice >= GIVEAWAY_PRICE;
    }
}
