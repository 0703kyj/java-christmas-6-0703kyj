package christmas.util;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MainMenu;
import christmas.domain.menu.Menu;
import christmas.resource.menu.AppetizerValue;
import christmas.resource.menu.DessertValue;
import christmas.resource.menu.DrinkValue;
import christmas.resource.menu.MainMenuValue;
import christmas.resource.menu.MenuValue;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MenuInitializer {

    private static List<Menu> totalMenu = new ArrayList<>();

    public static List<Menu> initMenu(){
        totalMenu.clear();

        setMenu(List.of(AppetizerValue.values()),
                value -> new Appetizer(value.getName(), value.getPrice()));
        setMenu(List.of(MainMenuValue.values()),
                value -> new MainMenu(value.getName(), value.getPrice()));
        setMenu(List.of(DessertValue.values()),
                value -> new Dessert(value.getName(), value.getPrice()));
        setMenu(List.of(DrinkValue.values()),
                value -> new Drink(value.getName(), value.getPrice()));

        return totalMenu;
    }

    private static void setMenu(List<MenuValue> menuValues, Function<MenuValue, Menu> menuCreator) {
        for (MenuValue menuValue : menuValues) {
            totalMenu.add(menuCreator.apply(menuValue));
        }
    }
}
