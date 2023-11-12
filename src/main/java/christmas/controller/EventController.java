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

    public EventController(InputView inputView, OutputView outputView, OrderService orderService,
                           DiscountService discountService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
        this.discountService = discountService;
    }

    public void run(){
        outputView.printInfo();
        input();

        outputView.printBenefits(eventDate);
        outputView.printOrderMenu(orderService.printOrder());
        outputView.printPriceBeforeDiscount(orderService.getPriceBeforeDiscount());
        outputView.printGiveawayMenu(orderService.getGiveaway());
        calculateDiscounts();
        outputView.printTotalDiscount(discountService.getTotalDiscounts());
        outputView.printTotalDiscountPrice(discountService.calculateTotalDiscountPrice());
        outputView.printPriceAfterDiscount(orderService.getPriceBeforeDiscount(),
                discountService.calculateTotalDiscountExceptGiveaway());
    }

    private void calculateDiscounts() {
        List<Discount> discounts = getDiscounts();

        for (Discount discount : discounts) {
            discountService.calculateDiscount(discount);
        }
    }

    private List<Discount> getDiscounts() {
        return List.of(
                new DdayDiscount(eventDate),
                new WeekdayDiscount(eventDate, orderService.getCountOfDesserts()),
                new WeekendDiscount(eventDate, orderService.getCountOfMainMenus()),
                new SpecialDiscount(eventDate),
                new GiveawayDiscount(orderService.getTotalPrice())
        );
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        inputView.readOrder();
    }
}
