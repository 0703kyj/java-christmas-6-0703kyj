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
import java.util.Map;
import java.util.function.Function;

public class MenuInitializer {

    private static List<Menu> totalMenu = new ArrayList<>();
    private static Map<List<MenuValue>,Function<MenuValue, Menu>> menuTypes = Map.ofEntries(
            Map.entry(List.of(AppetizerValue.values()),
                    value -> new Appetizer(value.getName(), value.getPrice())),
            Map.entry(List.of(MainMenuValue.values()),
                    value -> new MainMenu(value.getName(), value.getPrice())),
            Map.entry(List.of(DessertValue.values()),
                    value -> new Dessert(value.getName(), value.getPrice())),
            Map.entry(List.of(DrinkValue.values()),
                    value -> new Drink(value.getName(), value.getPrice()))
    );

    public static List<Menu> initMenu(){
        totalMenu.clear();

        for (List<MenuValue> menuValues : menuTypes.keySet()) {
            setMenu(menuValues,menuTypes.get(menuValues));
        }
        return totalMenu;
    }

    private static void setMenu(List<MenuValue> menuValues, Function<MenuValue, Menu> menuCreator) {
        for (MenuValue menuValue : menuValues) {
            totalMenu.add(menuCreator.apply(menuValue));
        }
    }
}
