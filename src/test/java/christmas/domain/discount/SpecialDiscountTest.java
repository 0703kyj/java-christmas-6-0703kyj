package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.EventDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountTest {
    private EventDate eventDate;
    private int beforeDiscountPrice;

    @BeforeEach
    void beforeEach() {
        eventDate = new EventDate(3);
        beforeDiscountPrice = 10000;
    }

    @Test
    void calculate() {
        Discount discount = new SpecialDiscount(eventDate, beforeDiscountPrice);
        Assertions.assertThat(discount.calculate()).isEqualTo(1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31})
    @DisplayName("특별할인은 일요일에 적용됩니다.")
    void checkSundayTest(int day) {
        eventDate = new EventDate(day);
        Discount discount = new SpecialDiscount(eventDate, beforeDiscountPrice);

        assertTrue(discount.canDiscount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7})
    @DisplayName("특별할인은 일요일이 아니면 적용되지 않습니다.")
    void checkNotSundayTest(int day) {
        eventDate = new EventDate(day);
        Discount discount = new SpecialDiscount(eventDate, beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }

    @Test
    @DisplayName("특별할인은 크리스마스에 적용됩니다.")
    void checkXmasTest() {
        eventDate = new EventDate(25);
        Discount discount = new SpecialDiscount(eventDate, beforeDiscountPrice);

        assertTrue(discount.canDiscount());
    }

    @Test
    @DisplayName("할인 전 금액이 10000원 이상이여야 혜택이 적용됩니다.")
    void eventTriggerTest() {
        beforeDiscountPrice = 9999;
        Discount discount = new SpecialDiscount(eventDate, beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }
}
