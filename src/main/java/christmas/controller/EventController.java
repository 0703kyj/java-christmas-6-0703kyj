package christmas.controller;

import christmas.domain.EventDate;
import christmas.util.EventCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private InputView inputView;
    private OutputView outputView;
    private EventCalculator eventCalculator;
    private EventDate eventDate;

    public EventController(InputView inputView, OutputView outputView, EventCalculator eventCalculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventCalculator = eventCalculator;
    }

    public void run() {
        outputView.printGreeting();
        input();
        eventCalculator.calculateDiscounts(eventDate);
        output();
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        inputView.readOrder();
    }

    private void output() {
        outputView.printBenefits(eventDate);
        outputView.printOrderMenu(eventCalculator.getOrderMenu());
        outputView.printPriceBeforeDiscount(eventCalculator.getBeforeDiscountPrice());
        outputView.printGiveawayMenu(eventCalculator.getGiveawayMenu());
        outputView.printTotalDiscount(eventCalculator.getTotalDiscounts());
        outputView.printTotalDiscountPrice(eventCalculator.getTotalDiscountPrice());
        outputView.printPriceAfterDiscount(eventCalculator.getBeforeDiscountPrice(),
                eventCalculator.getTotalDiscountPriceExpectGiveaway());
        outputView.printEventBadge(eventCalculator.getBadge());
    }
}
