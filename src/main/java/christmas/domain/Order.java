package christmas.domain;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;
import christmas.exception.argument.NotValidOrderException;
import christmas.util.MenuInitializer;
import java.util.List;
import java.util.Optional;

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

        findMenu(name)
                .ifPresentOrElse(
                        menu -> menu.order(orderCount),
                        () -> { throw new NotValidOrderException(); }
                );
    }

    public void checkOnlyDrink() {
        if (isAllDrinks()) {
            throw new NotValidOrderException();
        }
    }

    private boolean isAllDrinks() {
        return totalMenu.stream()
                .filter(menu -> menu.isOrdered())
                .allMatch(menu -> menu instanceof Drink);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Menu menu : totalMenu) {
            if(menu.isOrdered()){
                result.append(menu);
            }
        }
        return result.toString();
    }

    private void checkOrdered(Menu menu) {
        if (menu.isOrdered()) {
            throw new NotValidOrderException();
        }
    }

    private Optional<Menu> findMenu(String name) {
        return totalMenu.stream()
                .filter(menu -> menu.compareTo(name) > 0)
                .findFirst();
    }

    private void validateOrder(String name, int orderCount){
        validateDuplicate(name);
        validateOrderCount(orderCount);
    }

    private void validateDuplicate(String name) {
        findMenu(name)
                .ifPresentOrElse(
                        menu -> checkOrdered(menu),
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
}
