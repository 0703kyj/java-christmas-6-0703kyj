package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.EventDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayDiscountTest {
    private EventDate eventDate;
    private int beforeDiscountPrice;
    private int menuCount;

    @BeforeEach
    void beforeEach() {
        eventDate = new EventDate(3);
        beforeDiscountPrice = 10000;
        menuCount = 2;
    }

    @Test
    void calculate() {
        Discount discount = new WeekdayDiscount(eventDate, menuCount, beforeDiscountPrice);

        Assertions.assertThat(discount.calculate()).isEqualTo(2023*2);
    }

    @ParameterizedTest
    @ValueSource(ints = {3,4,5,6,7})
    @DisplayName("평일할인은 일,월,화,수,목에 적용됩니다.")
    void checkSundayTest(int day) {
        eventDate = new EventDate(day);
        Discount discount = new WeekdayDiscount(eventDate, menuCount, beforeDiscountPrice);

        assertTrue(discount.canDiscount());
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9})
    @DisplayName("평일할인은 금,토에는 적용되지 않습니다.")
    void checkNotSundayTest(int day) {
        eventDate = new EventDate(day);
        Discount discount = new WeekdayDiscount(eventDate, menuCount, beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    @DisplayName("평일할인은 주문개수가 0개 이하면 적용되지 않습니다.")
    void checkXmasTest(int count) {
        menuCount = count;
        Discount discount = new WeekdayDiscount(eventDate, menuCount, beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }

    @Test
    @DisplayName("할인 전 금액이 10000원 이상이여야 혜택이 적용됩니다.")
    void eventTriggerTest() {
        beforeDiscountPrice = 9999;
        Discount discount = new WeekdayDiscount(eventDate, menuCount, beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }
}
