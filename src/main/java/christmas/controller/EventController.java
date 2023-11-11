package christmas.controller;

import christmas.domain.EventDate;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private InputView inputView;
    private OutputView outputView;
    private OrderService orderService;
    private EventDate eventDate;

    public EventController(InputView inputView, OutputView outputView, OrderService orderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
    }

    public void run(){
        outputView.printInfo();
        input();

        outputView.printBenefits(eventDate);
        outputView.printOrderMenu(orderService.printOrder());
        outputView.printPriceBeforeDiscount(orderService.getPriceBeforeDiscount());
        outputView.printGiveawayMenu(orderService.getGiveaway());
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        inputView.readOrder();
    }
}
