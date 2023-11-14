package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.EventDate;
import christmas.domain.Order;
import christmas.util.TypeChanger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountServiceTest {

    private Order order;
    private OrderService orderService;
    private DiscountService discountService;
    private EventCalculator eventCalculator;

    @BeforeEach
    void beforeEach() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        order = new Order();
        orderService = new OrderService(order);
        discountService = new DiscountService(order);
        eventCalculator = new EventCalculator(orderService, discountService);
    }

    @ParameterizedTest
    @CsvSource({
            "'티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1',-31246,3",
            "'티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1',-29046,26",
            "'타파스-1,제로콜라-1',0,3"
    })
    @DisplayName("총 혜택 금액이 정확하게 계산되어야 합니다.")
    void calculateTotalDiscountPriceTest(String input, int price, int day) {
        //given
        EventDate eventDate = new EventDate(day);
        TypeChanger.toOrder(input, order);
        eventCalculator.calculateDiscounts(eventDate);
        //when
        int totalDiscountPrice = discountService.calculateTotalDiscountPrice();
        //then
        assertThat(totalDiscountPrice).isEqualTo(price);
    }

    @ParameterizedTest
    @CsvSource({
            "'티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1',-6246,3",
            "'티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1',-4046,26",
            "'타파스-1,제로콜라-1',0,3"
    })
    @DisplayName("증정 메뉴 가격을 제외한 할인 가격이 정확하게 계산되어야 합니다.")
    void calculateTotalDiscountExceptGiveawayTest(String input, int price, int day) {
        //given
        EventDate eventDate = new EventDate(day);
        TypeChanger.toOrder(input, order);
        eventCalculator.calculateDiscounts(eventDate);
        //when
        int totalDiscountPriceExceptGiveaway = discountService.calculateTotalDiscountExceptGiveaway();
        //then
        assertThat(totalDiscountPriceExceptGiveaway).isEqualTo(price);
    }
}
