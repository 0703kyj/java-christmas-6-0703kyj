package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
    private Order order;

    @BeforeEach
    void beforeEach(){
        order = new Order();
    }

    @Test
    @DisplayName("사용자가 주문한대로 주문개수가 증가해야함.")
    void orderTest() {
        String name = "초코케이크";
        int orderCount = 3;
        order.orderMenu(name,orderCount);

        assertThat(order.toString()).contains("3개");
    }
}
