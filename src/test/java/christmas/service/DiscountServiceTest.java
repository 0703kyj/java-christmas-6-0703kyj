package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.EventDate;
import christmas.domain.Order;
import christmas.domain.discount.DdayDiscount;
import christmas.domain.discount.Discount;
import christmas.domain.discount.GiveawayDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.util.TypeChanger;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountServiceTest {

    private Order order;
    private OrderService orderService;
    private DiscountService discountService;
    private EventCalculator eventCalculator;

    @BeforeEach
    void beforeEach() {
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

    @ParameterizedTest
    @CsvSource({
            "10000,1,3",
            "10000,0,26",
            "9999,0,3"
    })
    @DisplayName("디데이 할인이 유효한 경우만 추가되어야 합니다.")
    void setDdayDiscountTest(int beforeDiscountPrice, int count, int day) {
        EventDate eventDate = new EventDate(day);
        DdayDiscount ddayDiscount = new DdayDiscount(eventDate, beforeDiscountPrice);

        discountService.setDiscount(ddayDiscount);

        List<Discount> totalDiscounts = discountService.getTotalDiscounts();
        assertThat(totalDiscounts.size()).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource({
            "10000,2,0,2",
            "10000,2,1,3",
            "10000,0,0,3",
            "9999,1,0,3"
    })
    @DisplayName("평일 할인이 유효한 경우만 추가되어야 합니다.")
    void setWeekdayDiscountTest(int beforeDiscountPrice,int desserts, int count, int day) {
        EventDate eventDate = new EventDate(day);
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(
                eventDate, desserts, beforeDiscountPrice);

        discountService.setDiscount(weekdayDiscount);

        List<Discount> totalDiscounts = discountService.getTotalDiscounts();
        assertThat(totalDiscounts.size()).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource({
            "10000,2,1,2",
            "10000,0,0,2",
            "10000,2,0,3",
            "9999,2,0,3"
    })
    @DisplayName("주말 할인이 유효한 경우만 추가되어야 합니다.")
    void setWeekendDiscountTest(int beforeDiscountPrice, int mainMenus, int count, int day) {
        EventDate eventDate = new EventDate(day);
        WeekendDiscount weekendDiscount = new WeekendDiscount(
                eventDate, mainMenus, beforeDiscountPrice);

        discountService.setDiscount(weekendDiscount);

        List<Discount> totalDiscounts = discountService.getTotalDiscounts();
        assertThat(totalDiscounts.size()).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource({
            "10000,0,2",
            "10000,1,3",
            "10000,1,25",
            "9999,0,3"
    })
    @DisplayName("특별 할인이 유효한 경우만 추가되어야 합니다.")
    void setSpecialDiscountTest(int beforeDiscountPrice, int count, int day) {
        EventDate eventDate = new EventDate(day);
        SpecialDiscount specialDiscount = new SpecialDiscount(eventDate, beforeDiscountPrice);

        discountService.setDiscount(specialDiscount);

        List<Discount> totalDiscounts = discountService.getTotalDiscounts();
        assertThat(totalDiscounts.size()).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource({
            "120000,1",
            "119999,0"
    })
    @DisplayName("증정 메뉴 제공이 유효한 경우만 추가되어야 합니다.")
    void setGiveawayDiscountTest(int beforeDiscountPrice, int count) {
        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(beforeDiscountPrice);

        discountService.setDiscount(giveawayDiscount);

        List<Discount> totalDiscounts = discountService.getTotalDiscounts();
        assertThat(totalDiscounts.size()).isEqualTo(count);
    }

    @Test
    @DisplayName("증정 메뉴는 할인 전 금액 120000원 이상에서 제공합니다.")
    void canGiveGiveawayTest() {
        int beforeDiscountPrice = 120000;
        String giveaway = "샴페인 1개\n";
        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(beforeDiscountPrice);

        discountService.setDiscount(giveawayDiscount);

        assertThat(discountService.calculateGiveaway()).isEqualTo(giveaway);
    }

    @Test
    @DisplayName("증정 메뉴는 할인 전 금액 120000원 미만이면 제공되지 않습니다.")
    void cannotGiveGiveawayTest() {
        int beforeDiscountPrice = 119000;
        GiveawayDiscount giveawayDiscount = new GiveawayDiscount(beforeDiscountPrice);

        discountService.setDiscount(giveawayDiscount);

        Assertions.assertNull(discountService.calculateGiveaway());
    }
}
