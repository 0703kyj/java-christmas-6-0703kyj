package christmas.domain;

import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MainMenu;
import christmas.domain.menu.Menu;
import christmas.exception.argument.NotValidOrderException;
import christmas.exception.argument.OrderOnlyDrinkException;
import christmas.exception.argument.OverOrderCountException;
import christmas.util.MenuInitializer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Order {
    private static final int MAX_ORDER_COUNT = 20;

    private List<Menu> totalMenu;
    private static int totalOrderCount;

    public Order() {
        init();
    }

    public void init() {
        totalMenu = MenuInitializer.initMenu();
        totalOrderCount = 0;
    }

    public void orderMenu(String name, int orderCount) {
        validateOrder(name, orderCount);
        addOrderCount(orderCount);

        findMenu(name)
                .ifPresentOrElse(
                        menu -> menu.order(orderCount),
                        () -> {
                            throw new NotValidOrderException();
                        }
                );
    }

    public List<Menu> getMainMenus() {
        return Collections.unmodifiableList(totalMenu.stream()
                .filter(menu -> menu instanceof MainMenu)
                .collect(Collectors.toList()));
    }

    public List<Menu> getDesserts() {
        return Collections.unmodifiableList(totalMenu.stream()
                .filter(menu -> menu instanceof Dessert)
                .collect(Collectors.toList()));
    }

    public void checkOnlyDrink() {
        if (isAllDrinks()) {
            throw new OrderOnlyDrinkException();
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Menu menu : totalMenu) {
            if (menu.isOrdered()) {
                totalPrice += menu.getPrice();
            }
        }
        return totalPrice;
    }

    public boolean isCustomerOrdered() {
        return totalOrderCount > 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Menu menu : totalMenu) {
            if (menu.isOrdered()) {
                result.append(menu);
            }
        }
        return result.toString();
    }

    private boolean isAllDrinks() {
        return totalMenu.stream()
                .filter(menu -> menu.isOrdered())
                .allMatch(menu -> menu instanceof Drink);
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

    private void validateOrder(String name, int orderCount) {
        validateDuplicate(name);
        validateOrderCount(orderCount);
    }

    private void validateDuplicate(String name) {
        findMenu(name)
                .ifPresentOrElse(
                        menu -> checkOrdered(menu),
                        () -> {
                            throw new NotValidOrderException();
                        }
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
        if (totalOrderCount + orderCount > MAX_ORDER_COUNT) {
            throw new OverOrderCountException();
        }
    }

    private void addOrderCount(int orderCount) {
        totalOrderCount += orderCount;
    }
}
