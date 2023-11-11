package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.resource.discount.WeekendValue;

public class WeekendDiscount implements Discount{
    private static final int DISCOUNT_PRICE = 2023;
    private static final String DISCOUNT_TITLE = "주말 할인";
    private EventDate eventDate;
    private int menuCount;

    public WeekendDiscount(EventDate eventDate, int menuCount) {
        this.eventDate = eventDate;
        this.menuCount = menuCount;
    }

    @Override
    public int calculate() {
        return DISCOUNT_PRICE*menuCount;
    }

    @Override
    public String getTitle() {
        return DISCOUNT_TITLE;
    }

    @Override
    public boolean canDiscount() {
        if (checkWeekend() && hasMenu()) {
            return true;
        }
        return false;
    }

    private boolean hasMenu() {
        return menuCount > 0;
    }

    private boolean checkWeekend() {
        for (WeekendValue discountValue : WeekendValue.values()) {
            if (discountValue.name().equalsIgnoreCase(eventDate.getDayOfWeek())) {
                return true;
            }
        }
        return false;
    }
}
