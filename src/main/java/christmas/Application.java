package christmas;

import christmas.config.EventConfig;
import christmas.controller.EventController;

public class Application {

    public static void main(String[] args) {
        EventController eventController = EventConfig.getEventController();
        eventController.run();
    }
}
