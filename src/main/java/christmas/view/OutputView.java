package christmas.view;

import christmas.domain.EventDate;
import christmas.domain.Order;
import christmas.resource.DateValue;

public class OutputView {
    private static final String INFO_MESSAGE = "안녕하세요! 우테코 식당 " + DateValue.EVENT_MONTH + "월 이벤트 플래너입니다.";
    private static final String BENEFITS_TITLE = DateValue.EVENT_MONTH+"월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";


    public OutputView() {
    }

    public void printInfo() {
        System.out.println(INFO_MESSAGE);
    }

    public void printBenefits(EventDate eventDate, Order order) {
        System.out.println(BENEFITS_TITLE.formatted(eventDate.getDay()));
        printOrderMenu(order);
    }

    private void printOrderMenu(Order order) {
        System.out.println(ORDER_MENU_TITLE);
        System.out.println(order);
        System.out.println();
    }
}
