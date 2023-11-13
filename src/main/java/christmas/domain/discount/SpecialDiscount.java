package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.exception.state.NotFoundEventDateException;
import christmas.resource.discount.SpecialDateValue;
import christmas.resource.discount.SpecialDayOfWeekValue;
import christmas.util.EventTrigger;

public class SpecialDiscount implements Discount {
    private static final int DISCOUNT_PRICE = 1000;
    private static final String DISCOUNT_TITLE = "특별 할인";
    private static final String TITLE_SUFFIX = ": ";
    private EventDate eventDate;
    private int beforeDiscountPrice;

    public SpecialDiscount(EventDate eventDate, int beforeDiscountPrice) {
        this.eventDate = eventDate;
        this.beforeDiscountPrice = beforeDiscountPrice;
    }

    @Override
    public int calculate() {
        return DISCOUNT_PRICE;
    }

    @Override
    public String getTitle() {
        return DISCOUNT_TITLE + TITLE_SUFFIX;
    }

    @Override
    public boolean canDiscount() {
        try {
            return (checkDayOfWeek() || checkDate()) && EventTrigger.canTrigger(beforeDiscountPrice);
        } catch (NullPointerException exception) {
            throw new NotFoundEventDateException();
        }
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
