package christmas.config;

import christmas.controller.EventController;
import christmas.domain.Order;
import christmas.service.DiscountService;
import christmas.service.EventCalculator;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventConfig {
    private static Order order;

    public static EventController getEventController() {
        return new EventController(getInputView(), getOutputView(), getEventCalculator());
    }

    private static EventCalculator getEventCalculator() {
        return new EventCalculator(getOrderService(), getDiscountService());
    }

    private static OrderService getOrderService() {
        return new OrderService(getOrder());
    }

    private static DiscountService getDiscountService() {
        return new DiscountService(getOrder());
    }

    private static Order getOrder() {
        if (order != null) {
            return order;
        }
        return new Order();
    }

    private static InputView getInputView() {
        return new InputView(getOrder());
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }
}
