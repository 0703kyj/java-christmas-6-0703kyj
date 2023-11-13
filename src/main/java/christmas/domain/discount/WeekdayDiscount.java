package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.exception.state.NotFoundEventDateException;
import christmas.resource.discount.WeekdayValue;
import christmas.util.EventTrigger;

public class WeekdayDiscount implements Discount {
    private static final int DISCOUNT_PRICE = 2023;
    private static final String DISCOUNT_TITLE = "평일 할인";
    private final EventDate eventDate;
    private final int menuCount;
    private final int beforeDiscountPrice;

    public WeekdayDiscount(EventDate eventDate, int menuCount, int beforeDiscountPrice) {
        this.eventDate = eventDate;
        this.menuCount = menuCount;
        this.beforeDiscountPrice = beforeDiscountPrice;
    }

    @Override
    public int calculate() {
        return DISCOUNT_PRICE * menuCount;
    }

    @Override
    public String getTitle() {
        return DISCOUNT_TITLE + TITLE_SUFFIX;
    }

    @Override
    public boolean canDiscount() {
        try {
            return checkWeekday() && hasMenu() && EventTrigger.canTrigger(beforeDiscountPrice);
        } catch (NullPointerException exception) {
            throw new NotFoundEventDateException();
        }
    }

    private boolean hasMenu() {
        return menuCount > 0;
    }

    private boolean checkWeekday() {
        for (WeekdayValue discountValue : WeekdayValue.values()) {
            if (discountValue.name().equalsIgnoreCase(eventDate.getDayOfWeek())) {
                return true;
            }
        }
        return false;
    }
}
