package christmas.view;

import christmas.domain.EventDate;
import christmas.domain.discount.Discount;
import christmas.resource.DateValue;
import christmas.util.DecimalFormatter;
import java.util.List;

public class OutputView {
    private static final String INFO_MESSAGE = "안녕하세요! 우테코 식당 " + DateValue.EVENT_MONTH + "월 이벤트 플래너입니다.";
    private static final String BENEFITS_TITLE = DateValue.EVENT_MONTH + "월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String GIVEAWAY_MENU_TITLE = "<증정 메뉴>";
    private static final String TOTAL_DISCOUNT_TITLE = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_PRICE_TITLE = "<총혜택 금액>";
    private static final String AFTER_DISCOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String PRICE_FORMAT = "%s원";
    private static final String DISCOUNT_FORMAT = "-%s원";
    private static final String NOTHING = "없음";

    public OutputView() {
    }

    public void printInfo() {
        System.out.println(INFO_MESSAGE);
    }

    public void printBenefits(EventDate eventDate) {
        System.out.println(BENEFITS_TITLE.formatted(eventDate.getDay()));
    }

    public void printOrderMenu(String order) {
        System.out.println(ORDER_MENU_TITLE);
        System.out.println(order);
    }

    public void printPriceBeforeDiscount(int priceBeforeDiscount) {
        System.out.println(BEFORE_DISCOUNT_TITLE);
        System.out.println(PRICE_FORMAT.formatted(DecimalFormatter.format(priceBeforeDiscount)));
        System.out.println();
    }

    public void printTotalDiscount(List<Discount> totalDiscount) {
        System.out.println(TOTAL_DISCOUNT_TITLE);

        if (checkCanDiscount(totalDiscount.isEmpty())) {
            return;
        }
        printDiscounts(totalDiscount);
        System.out.println();
    }

    public void printGiveawayMenu(String giveawayMenu) {
        System.out.println(GIVEAWAY_MENU_TITLE);
        if (checkCanDiscount(giveawayMenu.isBlank())) {
            return;
        }
        System.out.println(giveawayMenu);
    }

    public void printTotalDiscountPrice(int totalDiscountPrice) {
        System.out.println(TOTAL_DISCOUNT_PRICE_TITLE);
        System.out.println(DISCOUNT_FORMAT.formatted(DecimalFormatter.format(totalDiscountPrice)));
        System.out.println();
    }

    public void printPriceAfterDiscount(int priceBeforeDiscount, int priceAfterDiscount) {
        int differencePrice = max(priceBeforeDiscount - priceAfterDiscount, 0);

        System.out.println(AFTER_DISCOUNT_TITLE);
        System.out.println(PRICE_FORMAT.formatted(DecimalFormatter.format(differencePrice)));
        System.out.println();
    }

    private int max(int firstNumber, int secondNumber){
        if (firstNumber > secondNumber) {
            return firstNumber;
        }
        return secondNumber;
    }

    private boolean checkCanDiscount(boolean canDiscount) {
        if (canDiscount) {
            System.out.println(NOTHING);
            return true;
        }
        return false;
    }

    private void printDiscounts(List<Discount> totalDiscount) {
        for (Discount discount : totalDiscount) {
            String discountTitle = discount.getTitle();
            String discountPrice = discount.getDiscountPrice();
            System.out.println(discountTitle + DISCOUNT_FORMAT.formatted(discountPrice));
        }
    }
}
