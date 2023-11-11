package christmas;

import christmas.domain.Order;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        Order order = inputView.readOrder();
        System.out.println(order);
    }
}
