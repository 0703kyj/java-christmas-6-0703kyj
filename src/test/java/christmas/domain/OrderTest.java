package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.argument.NotValidOrderException;
import christmas.util.TypeChanger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    private Order order;

    @BeforeEach
    void beforeEach() {
        order = new Order();
    }

    @Test
    @DisplayName("고객이 메뉴판에 없는 메뉴를 입력하면 안된다.")
    void notExistMenuTest() {
        String name = "김치";
        int orderCount = 1;

        assertThatThrownBy(() -> order.orderMenu(name, orderCount))
                .isInstanceOf(NotValidOrderException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("메뉴의 개수는 1 이상의 숫자만 입력되도록 해야 한다.")
    void minOrderCountTest(int orderCount) {
        String name = "양송이수프";

        assertThatThrownBy(() -> order.orderMenu(name, orderCount))
                .isInstanceOf(NotValidOrderException.class);
    }

    @Test
    @DisplayName("메뉴의 개수는 숫자이외의 값이 입력되면 안된다.")
    void notNumberOrderCountTest() {
        String input = "양송이스프-일";

        assertThatThrownBy(() -> TypeChanger.toOrder(input, order))
                .isInstanceOf(NotValidOrderException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이스프-1~제로콜라-3", "양송이스프:1,제로콜라:3"})
    @DisplayName("사용자 입력이 메뉴 형식관 같아야 한다.")
    void notValidFormatTest(String input) {
        assertThatThrownBy(() -> TypeChanger.toOrder(input, order))
                .isInstanceOf(NotValidOrderException.class);
    }

    @Test
    @DisplayName("중복 메뉴를 입력하면 안된다.")
    void duplicateMenuTest() {
        String input = "양송이스프-1,양송이스프-3";

        assertThatThrownBy(() -> TypeChanger.toOrder(input, order))
                .isInstanceOf(NotValidOrderException.class);
    }

    @ParameterizedTest
    @CsvSource({"'제로콜라',3", "'샴페인',3", "'레드와인',4"})
    @DisplayName("음료만 주문하면 안된다.")
    void onlyOrderDrinkTest(String name, int orderCount) {
        order.orderMenu(name, orderCount);

        assertThatThrownBy(() -> order.checkOnlyDrink())
                .isInstanceOf(NotValidOrderException.class);
    }

    @Test
    @DisplayName("메뉴 개수가 20가지 초과이면 안된다.")
    void overMaxOrderCountTest() {
        String input = "양송이스프-1,시저샐러드-3,제로콜라-17";

        assertThatThrownBy(() -> TypeChanger.toOrder(input, order))
                .isInstanceOf(NotValidOrderException.class);
    }
}
