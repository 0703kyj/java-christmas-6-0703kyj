package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.resource.discount.SpecialDateValue;
import christmas.resource.discount.SpecialDayOfWeekValue;

public class SpecialDiscount implements Discount {
    private static final int DISCOUNT_PRICE = 1000;
    private static final String DISCOUNT_TITLE = "특별 할인";
    private EventDate eventDate;

    public SpecialDiscount(EventDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public int calculate() {
        return DISCOUNT_PRICE;
    }

    @Override
    public String getTitle() {
        return DISCOUNT_TITLE;
    }

    @Override
    public boolean canDiscount() {
        if (checkDayOfWeek() || checkDate()) {
            return true;
        }
        return false;
    }

    private boolean checkDate() {
        for (SpecialDateValue discountValue : SpecialDateValue.values()) {
            if (isSameDay(discountValue)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameDay(SpecialDateValue discountValue) {
        return discountValue.getDay() == eventDate.getDay();
    }

    private boolean checkDayOfWeek() {
        for (SpecialDayOfWeekValue discountValue : SpecialDayOfWeekValue.values()) {
            if (discountValue.name().equalsIgnoreCase(eventDate.getDayOfWeek())) {
                return true;
            }
        }
        return false;
    }
}
