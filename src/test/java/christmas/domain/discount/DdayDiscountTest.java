package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DdayDiscountTest {

    private EventDate eventDate;
    private int beforeDiscountPrice;

    @BeforeEach
    void beforeEach() {
        eventDate = new EventDate(3);
        beforeDiscountPrice = 10000;
    }

    @ParameterizedTest
    @CsvSource({"1,1000", "4,1300", "25,3400", "26,0"})
    @DisplayName("디데이 할인은 1일부터 25일까지 하루가 지날수록 100원씩 증가합니다.")
    void calculate(int day, int result) {
        eventDate = new EventDate(day);
        Discount discount = new DdayDiscount(eventDate, beforeDiscountPrice);
        int discountPrice = discount.calculate();

        assertThat(discountPrice).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {25, 26})
    @DisplayName("디데이 할인은 1~25일에만 적용됩니다.")
    void limitDdayTest(int day) {
        eventDate = new EventDate(day);
        Discount discount = new DdayDiscount(eventDate, beforeDiscountPrice);

        if (day <= 25) {
            assertTrue(discount.canDiscount());
            return;
        }
        assertFalse(discount.canDiscount());
    }

    @Test
    @DisplayName("할인 전 금액이 10000원 이상이여야 혜택이 적용됩니다.")
    void eventTriggerTest() {
        beforeDiscountPrice = 9999;
        Discount discount = new DdayDiscount(eventDate, beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }
}
