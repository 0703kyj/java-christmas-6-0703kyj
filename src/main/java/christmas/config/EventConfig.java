package christmas.config;

import christmas.controller.EventController;
import christmas.domain.Order;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventConfig {
    private static Order order;

    public static EventController getEventController() {
        return new EventController(getInputView(),getOutputView(),getOrderService());
    }

    private static OrderService getOrderService() {
        return new OrderService(getOrder());
    }

    private static Order getOrder() {
        if(order != null){
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
