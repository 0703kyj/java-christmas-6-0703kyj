package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.EventDate;
import christmas.domain.Order;
import christmas.resource.DateValue;
import christmas.util.TypeChanger;
import java.util.function.Supplier;

public class InputView {
    private static final String INPUT_VISIT_DATE = DateValue.EVENT_MONTH + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public EventDate readVisitDate() {
        EventDate visitDate = repeat(() -> new EventDate(inputVisitDate()));

        return visitDate;
    }

    public Order readOrder() {
        Order order = repeat(this::inputOrder);

        return order;
    }

    private int inputVisitDate() {
        System.out.println(INPUT_VISIT_DATE);
        return TypeChanger.toIntDate(Console.readLine());
    }

    private Order inputOrder() {
        System.out.println(INPUT_MENU);
        return TypeChanger.toOrder(Console.readLine());
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                T result = supplier.get();
                return result;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception);
            }
        }
    }
}
