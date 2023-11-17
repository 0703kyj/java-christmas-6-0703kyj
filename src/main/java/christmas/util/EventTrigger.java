package christmas.util;

public class EventTrigger {
    private static final int EVENT_TRIGGER = 10000;

    public static boolean canTrigger(int beforeDiscountPrice) {
        return beforeDiscountPrice >= EVENT_TRIGGER;
    }
}
