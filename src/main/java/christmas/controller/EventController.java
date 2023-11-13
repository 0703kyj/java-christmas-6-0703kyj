package christmas.controller;

import christmas.domain.EventDate;
import christmas.domain.discount.DdayDiscount;
import christmas.domain.discount.Discount;
import christmas.domain.discount.GiveawayDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class EventController {
    private InputView inputView;
    private OutputView outputView;
    private OrderService orderService;
    private DiscountService discountService;
    private EventDate eventDate;
    private String orderMenu;
    private int beforeDiscountPrice;
    private String giveawayMenu;
    private List<Discount> totalDiscounts;
    private int totalDiscountPrice;
    private int totalDiscountPriceExpectGiveaway;
    private String badge;

    public EventController(InputView inputView, OutputView outputView, OrderService orderService,
                           DiscountService discountService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
        this.discountService = discountService;
        this.beforeDiscountPrice = 0;
        this.totalDiscountPrice = 0;
    }

    public void run() {
        outputView.printInfo();
        input();
        calculateDiscounts();
        output();
    }

    private void calculateDiscounts() {
        orderMenu = orderService.printOrder();
        beforeDiscountPrice = orderService.getPriceBeforeDiscount();
        setDiscounts();
        giveawayMenu = discountService.calculateGiveaway();
        totalDiscounts = discountService.getTotalDiscounts();
        totalDiscountPrice = discountService.calculateTotalDiscountPrice();
        totalDiscountPriceExpectGiveaway = discountService.calculateTotalDiscountExceptGiveaway();
        badge = orderService.getBadge(totalDiscountPrice);
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        inputView.readOrder();
    }

    private void output() {
        outputView.printBenefits(eventDate);
        outputView.printOrderMenu(orderMenu);
        outputView.printPriceBeforeDiscount(beforeDiscountPrice);
        outputView.printGiveawayMenu(giveawayMenu);
        outputView.printTotalDiscount(totalDiscounts);
        outputView.printTotalDiscountPrice(totalDiscountPrice);
        outputView.printPriceAfterDiscount(beforeDiscountPrice,totalDiscountPriceExpectGiveaway);
        outputView.printEventBadge(badge);
    }

    private void setDiscounts() {
        List<Discount> discounts = getDiscounts();

        for (Discount discount : discounts) {
            discountService.setDiscount(discount);
        }
    }

    private List<Discount> getDiscounts() {
        return List.of(
                new DdayDiscount(eventDate,beforeDiscountPrice),
                new WeekdayDiscount(eventDate, orderService.getCountOfDesserts(),beforeDiscountPrice),
                new WeekendDiscount(eventDate, orderService.getCountOfMainMenus(),beforeDiscountPrice),
                new SpecialDiscount(eventDate,beforeDiscountPrice),
                new GiveawayDiscount(orderService.getTotalPrice())
        );
    }
}
