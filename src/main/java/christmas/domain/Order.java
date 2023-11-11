package christmas.domain;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MainMenu;
import christmas.domain.menu.Menu;
import christmas.resource.menu.AppetizerValue;
import christmas.resource.menu.DessertValue;
import christmas.resource.menu.DrinkValue;
import christmas.resource.menu.MainMenuValue;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Menu> totalMenu;

    public Order(){
        totalMenu = new ArrayList<>();
        initMenu();
    }

    private void initMenu() {
        setAppetizer();
        setMainMenu();
        setDessert();
        setDrink();
    }

    private void setAppetizer() {
        for (AppetizerValue appetizer : AppetizerValue.values()) {
            totalMenu.add(new Appetizer(appetizer.getName(),appetizer.getPrice()));
        }
    }

    private void setMainMenu() {
        for (MainMenuValue mainMenu : MainMenuValue.values()) {
            totalMenu.add(new MainMenu(mainMenu.getName(),mainMenu.getPrice()));
        }
    }

    private void setDessert() {
        for (DessertValue dessert : DessertValue.values()) {
            totalMenu.add(new Dessert(dessert.getName(),dessert.getPrice()));
        }
    }

    private void setDrink() {
        for (DrinkValue drink : DrinkValue.values()) {
            totalMenu.add(new Drink(drink.getName(), drink.getPrice()));
        }
    }
}
