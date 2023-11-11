package christmas.config;

import christmas.controller.EventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventConfig {

    public static EventController getEventController() {
        return new EventController(getInputView(),getOutputView());
    }

    private static InputView getInputView() {
        return new InputView();
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }
}
