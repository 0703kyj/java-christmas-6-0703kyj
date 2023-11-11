package christmas.controller;

import christmas.domain.EventDate;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        outputView.printGiveawayMenu(discountService.getGiveaway());
        calculateDiscounts();
        outputView.printTotalDiscount(discountService.getTotalDiscount());
    }

    private void calculateDiscounts() {
        discountService.calculateDdayDiscount(eventDate);
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        inputView.readOrder();
    }
}
