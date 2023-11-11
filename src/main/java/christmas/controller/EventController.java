package christmas.controller;

import christmas.domain.EventDate;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private InputView inputView;
    private OutputView outputView;
    private EventDate eventDate;
    private Order order;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        outputView.printInfo();
        input();

        outputView.printBenefits(eventDate,order);
    }

    private void input() {
        eventDate = inputView.readVisitDate();
        order = inputView.readOrder();
    }
}
