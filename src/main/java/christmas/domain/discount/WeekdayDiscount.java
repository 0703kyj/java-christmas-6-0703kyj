package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.resource.discount.WeekdayValue;

public class WeekdayDiscount implements Discount {
    private static final int DISCOUNT_PRICE = 2023;
    private static final String DISCOUNT_TITLE = "평일 할인";
    private static final String TITLE_SUFFIX = ": ";
    private EventDate eventDate;
    private int menuCount;

    public WeekdayDiscount(EventDate eventDate, int menuCount) {
        this.eventDate = eventDate;
        this.menuCount = menuCount;
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
        if (checkWeekday() && hasMenu()) {
            return true;
        }
        return false;
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
