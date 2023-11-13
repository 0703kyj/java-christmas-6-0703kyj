package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderServiceTest {
    private static final String STAR ="별";
    private static final String TREE ="트리";
    private static final String SANTA ="산타";

    private Order order;
    private OrderService orderService;

    @BeforeEach
    void beforeEach(){
        order = new Order();
        orderService = new OrderService(order);
    }

    @ParameterizedTest
    @ValueSource(ints={-1000,-2000,-3000,-4000,-4999})
    @DisplayName("5000원 이상은 별 뱃지 부여한다.")
    void noBadgeTest(int totalPrice) {
        assertNull(orderService.getBadge(totalPrice));
    }

    @ParameterizedTest
    @ValueSource(ints={-5000,-6000,-7000,-8000,-9999})
    @DisplayName("5000원 이상은 별 뱃지 부여한다.")
    void starBadgeTest(int totalPrice) {
        assertThat(orderService.getBadge(totalPrice)).isEqualTo(STAR);
    }

    @ParameterizedTest
    @ValueSource(ints={-10000,-12000,-14000,-16000,-19999})
    @DisplayName("10000원 이상은 트리 뱃지 부여한다.")
    void treeBadgeTest(int totalPrice) {
        assertThat(orderService.getBadge(totalPrice)).isEqualTo(TREE);
    }

    @ParameterizedTest
    @ValueSource(ints={-20000,-30000,-40000,-50000,-120000})
    @DisplayName("20000원 이상은 산타 뱃지 부여한다.")
    void santoBadgeTest(int totalPrice) {
        assertThat(orderService.getBadge(totalPrice)).isEqualTo(SANTA);
    }
}
