package christmas.domain.menu;

public class Menu implements Comparable<String> {
    private static final String MENU_INFO = "%s %d개\n";

    protected String name;
    protected int price;
    protected int orderCount;

    protected Menu(String name, int price) {
        this.name = name;
        this.price = price;
        orderCount = 0;
    }

    public void order(int orderCount) {
        this.orderCount += orderCount;
    }

    public int getPrice() {
        return price * orderCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    @Override
    public String toString() {
        return MENU_INFO.formatted(name, orderCount);
    }

    @Override
    public int compareTo(String name) {
        if ((this.name).equals(name)) {
            return 1;
        }
        return 0;
    }

    public boolean isOrdered() {
        if ((this.orderCount) > 0) {
            return true;
        }
        return false;
    }
}
