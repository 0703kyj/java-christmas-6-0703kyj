package christmas.domain.discount;

import christmas.domain.EventDate;
import christmas.exception.state.NotFoundEventDateException;

public class DdayDiscount implements Discount {
    private static final int START_DATE = 1;
    private static final int END_DATE = 25;
    private static final int DISCOUNT_PRICE = 100;
    private static final int DEFAULT_DISCOUNT_PRICE = 1000;
    private static final int DEFAULT_DISCOUNT_COUNT = 0;
    private static final String DISCOUNT_TITLE = "크리스마스 디데이 할인";
    private static final String TITLE_SUFFIX = ": ";
    private static final EventDate startEventDate = new EventDate(START_DATE);
    private static final EventDate endEventDate = new EventDate(END_DATE);
    private EventDate eventDate;
    private int beforeDiscountPrice;

    public DdayDiscount(EventDate eventDate, int beforeDiscountPrice) {
        this.eventDate = eventDate;
        this.beforeDiscountPrice = beforeDiscountPrice;
    }

    @Override
    public int calculate() {
        if (isNotDdayEvent()) {
            return getDiscount(DEFAULT_DISCOUNT_COUNT);
        }
        return DEFAULT_DISCOUNT_PRICE + getDiscount(calculateCount());
    }

    @Override
    public String getTitle() {
        return DISCOUNT_TITLE + TITLE_SUFFIX;
    }

    @Override
    public boolean canDiscount() {
        return (calculate() > 0)&&canTriggerEvent();
    }

    private boolean canTriggerEvent() {
        return beforeDiscountPrice >= EVENT_TRIGGER;
    }

    private int getDiscount(int discountCount) {
        return DISCOUNT_PRICE * discountCount;
    }

    private int calculateCount() {
        try{
            return (eventDate.getDifference(startEventDate));
        }catch(NullPointerException exception){
            throw new NotFoundEventDateException();
        }
    }

    private boolean isNotDdayEvent() {
        try{
            return isEndDdayEvent() || !isStartDdayEvent();
        }catch(NullPointerException exception){
            throw new NotFoundEventDateException();
        }
    }

    private boolean isStartDdayEvent() {
        return eventDate.getDay() >= startEventDate.getDay();
    }

    private boolean isEndDdayEvent() {
        return eventDate.getDay() > endEventDate.getDay();
    }
}
