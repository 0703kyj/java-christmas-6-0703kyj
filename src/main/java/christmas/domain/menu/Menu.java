package christmas.domain.menu;

public class Menu {
    protected String name;
    protected int price;
    protected int orderCount;

    protected Menu(String name, int price) {
        this.name = name;
        this.price = price;
        orderCount = 0;
    }
}
