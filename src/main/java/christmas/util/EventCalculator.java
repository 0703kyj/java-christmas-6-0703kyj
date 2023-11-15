package christmas.util;

import christmas.domain.EventDate;
import christmas.domain.discount.DdayDiscount;
import christmas.domain.discount.Discount;
import christmas.domain.discount.GiveawayDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import java.util.Collections;
import java.util.List;

public class EventCalculator {
    private OrderService orderService;
    private DiscountService discountService;
    private String orderMenu;
    private int beforeDiscountPrice;
    private String giveawayMenu;
    private List<Discount> totalDiscounts;
    private int totalDiscountPrice;
    private int totalDiscountPriceExpectGiveaway;
    private String badge;

    public EventCalculator(OrderService orderService, DiscountService discountService) {
        this.orderService = orderService;
        this.discountService = discountService;
    }

    public void calculateDiscounts(EventDate eventDate) {
        orderMenu = orderService.printOrder();
        beforeDiscountPrice = orderService.getPriceBeforeDiscount();
        setDiscounts(eventDate);
        giveawayMenu = discountService.calculateGiveaway();
        totalDiscounts = discountService.getTotalDiscounts();
        totalDiscountPrice = discountService.calculateTotalDiscountPrice();
        totalDiscountPriceExpectGiveaway = discountService.calculateTotalDiscountExceptGiveaway();
        badge = orderService.getBadge(totalDiscountPrice);
    }

    public String getOrderMenu() {
        return orderMenu;
    }

    public int getBeforeDiscountPrice() {
        return beforeDiscountPrice;
    }

    public String getGiveawayMenu() {
        return giveawayMenu;
    }

    public List<Discount> getTotalDiscounts() {
        return Collections.unmodifiableList(totalDiscounts);
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public int getTotalDiscountPriceExpectGiveaway() {
        return totalDiscountPriceExpectGiveaway;
    }

    public String getBadge() {
        return badge;
    }

    private void setDiscounts(EventDate eventDate) {
        List<Discount> discounts = getDiscounts(eventDate);

        for (Discount discount : discounts) {
            discountService.setDiscount(discount);
        }
    }

    private List<Discount> getDiscounts(EventDate eventDate) {
        return List.of(
                new DdayDiscount(eventDate, beforeDiscountPrice),
                new WeekdayDiscount(eventDate, orderService.getCountOfDesserts(), beforeDiscountPrice),
                new WeekendDiscount(eventDate, orderService.getCountOfMainMenus(), beforeDiscountPrice),
                new SpecialDiscount(eventDate, beforeDiscountPrice),
                new GiveawayDiscount(beforeDiscountPrice)
        );
    }
}
