package christmas.domain;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.exception.argument.NotValidOrderException;
import christmas.util.MenuInitializer;
import java.util.List;

public class Order {
    private static final int MAX_ORDER_COUNT = 20;
    private final List<Menu> totalMenu;
    private int totalOrderCount;

    public Order(){
        totalMenu = MenuInitializer.initMenu();
        totalOrderCount = 0;
    }

    public void orderMenu(String name, int orderCount) {
        validateOrder(name, orderCount);

        totalMenu.stream()
                .filter(menu -> menu.compareTo(name) > 0)
                .findFirst()
                .ifPresentOrElse(
                        menu -> menu.order(orderCount),
                        () -> { throw new NotValidOrderException(); }
                );
    }

    public void print() {
        for (Menu menu : totalMenu) {
            if(menu.isOrdered()){
                System.out.println(menu);
            }
        }
    }

    private void validateOrder(String name, int orderCount){
        validateDuplicate(name);
        validateOrderCount(orderCount);
    }

    private void validateDuplicate(String name) {
        totalMenu.stream()
                .filter(menu -> menu.compareTo(name) > 0)
                .findFirst()
                .ifPresentOrElse(
                        menu -> {
                            if (menu.isOrdered()) {
                                throw new NotValidOrderException();
                            }
                        },
                        () -> { throw new NotValidOrderException(); }
                );
    }

    private void validateOrderCount(int orderCount) {
        validateOverMax(orderCount);
        validateZero(orderCount);
    }

    private static void validateZero(int orderCount) {
        if (orderCount <= 0) {
            throw new NotValidOrderException();
        }
    }

    private void validateOverMax(int orderCount) {
        totalOrderCount += orderCount;

        if (totalOrderCount > MAX_ORDER_COUNT) {
            throw new NotValidOrderException();
        }
    }

    public void checkOnlyDrink() {
        boolean allDrinks = totalMenu.stream()
                .filter(menu -> menu.isOrdered())
                .allMatch(menu -> menu instanceof Drink);

        if (allDrinks) {
            throw new NotValidOrderException();
        }
    }
}
