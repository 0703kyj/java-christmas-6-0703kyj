package christmas.view;

import christmas.domain.EventDate;
import christmas.resource.DateValue;
import christmas.service.OrderService;
import java.text.DecimalFormat;

public class OutputView {
    private static final String INFO_MESSAGE = "안녕하세요! 우테코 식당 " + DateValue.EVENT_MONTH + "월 이벤트 플래너입니다.";
    private static final String BENEFITS_TITLE = DateValue.EVENT_MONTH+"월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String BEFORE_DISCOUNT_PRICE = "원";


    public OutputView() {
    }

    public void printInfo() {
        System.out.println(INFO_MESSAGE);
    }

    public void printBenefits(EventDate eventDate, OrderService orderService) {
        System.out.println(BENEFITS_TITLE.formatted(eventDate.getDay()));
        printOrderMenu(orderService.printOrder());
        printPriceBeforeDiscount(orderService.getPriceBeforeDiscount());
    }

    private void printOrderMenu(String order) {
        System.out.println(ORDER_MENU_TITLE);
        System.out.println(order);
        System.out.println();
    }

    private void printPriceBeforeDiscount(DecimalFormat priceBeforeDiscount) {
        System.out.println(BEFORE_DISCOUNT_TITLE);
        System.out.println(priceBeforeDiscount+BEFORE_DISCOUNT_PRICE);
        System.out.println();
    }
}
