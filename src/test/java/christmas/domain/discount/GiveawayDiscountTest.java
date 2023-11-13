package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.resource.menu.DrinkValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GiveawayDiscountTest {
    private int beforeDiscountPrice;

    @BeforeEach
    void beforeEach() {
        beforeDiscountPrice = 120000;
    }

    @ParameterizedTest
    @ValueSource(ints = {119000, 120000})
    @DisplayName("할인 전 금액이 120000원 이상이면 상품이 증정됩니다.")
    void getGiveaway(int price) {
        beforeDiscountPrice = price;
        GiveawayDiscount discount = new GiveawayDiscount(beforeDiscountPrice);
        Menu menu = new Drink(DrinkValue.CHAMPAGNE.getName(), DrinkValue.CHAMPAGNE.getPrice());
        menu.order(1);

        String giveaway = discount.getGiveaway();
        if (price >= 120000) {
            assertThat(giveaway).isEqualTo(menu.toString());
            return;
        }
        assertThat(giveaway).isEqualTo(null);
    }

    @Test
    @DisplayName("증정 상품의 가격이 반환되어야 합니다.")
    void calculate() {
        beforeDiscountPrice = 120000;
        GiveawayDiscount discount = new GiveawayDiscount(beforeDiscountPrice);
        Menu menu = new Drink(DrinkValue.CHAMPAGNE.getName(), DrinkValue.CHAMPAGNE.getPrice());
        menu.order(1);

        discount.getGiveaway();

        assertThat(discount.calculate()).isEqualTo(menu.getPrice());
    }

    @Test
    @DisplayName("할인 전 금액이 10000원 이상이여야 혜택이 적용됩니다.")
    void eventTriggerTest() {
        beforeDiscountPrice = 9999;
        Discount discount = new GiveawayDiscount(beforeDiscountPrice);

        assertFalse(discount.canDiscount());
    }
}
