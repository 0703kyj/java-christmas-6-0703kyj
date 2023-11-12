package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.resource.discount.WeekendValue;

public class WeekendDiscount implements Discount {
    private static final int DISCOUNT_PRICE = 2023;
    private static final String DISCOUNT_TITLE = "주말 할인";
    private static final String TITLE_SUFFIX = ": ";
    private EventDate eventDate;
    private int menuCount;
    private int beforeDiscountPrice;

    public WeekendDiscount(EventDate eventDate, int menuCount, int beforeDiscountPrice) {
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
        return checkWeekend() && hasMenu() && canTriggerEvent();
    }

    private boolean canTriggerEvent() {
        return beforeDiscountPrice >= EVENT_TRIGGER;
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