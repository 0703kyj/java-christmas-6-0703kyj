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
    private int beforeDiscountPrice;
    private String giveawayMenu;
    private int totalDiscountPrice;
    private int totalDiscountPriceExpectGiveaway;

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

        beforeDiscountPrice = orderService.getPriceBeforeDiscount();
        calculateDiscounts();
        giveawayMenu = discountService.calculateGiveaway();
        totalDiscountPrice = discountService.calculateTotalDiscountPrice();
        totalDiscountPriceExpectGiveaway = discountService.calculateTotalDiscountExceptGiveaway();

        output();
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        inputView.readOrder();
    }

    private void output() {
        outputView.printBenefits(eventDate);
        outputView.printOrderMenu(orderService.printOrder());
        outputView.printPriceBeforeDiscount(beforeDiscountPrice);
        outputView.printGiveawayMenu(giveawayMenu);
        outputView.printTotalDiscount(discountService.getTotalDiscounts());
        outputView.printTotalDiscountPrice(totalDiscountPrice);
        outputView.printPriceAfterDiscount(beforeDiscountPrice,totalDiscountPriceExpectGiveaway);
        outputView.printEventBadge(totalDiscountPrice);
    }

    private void calculateDiscounts() {
        List<Discount> discounts = getDiscounts();

        for (Discount discount : discounts) {
            discountService.calculateDiscount(discount);
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
