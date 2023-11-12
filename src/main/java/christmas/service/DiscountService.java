package christmas.service;

import christmas.domain.Order;
import christmas.domain.discount.Discount;
import christmas.domain.discount.GiveawayDiscount;
import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    private Order order;
    private List<Discount> discounts;

    public DiscountService(Order order) {
        this.order = order;
        this.discounts = new ArrayList<>();
    }

    public List<Discount> getTotalDiscounts() {
        return discounts;
    }

    public int calculateTotalDiscountPrice(){
        int discountPrice = 0;

        for (Discount discount : discounts) {
            discountPrice -= discount.calculate();
        }
        return discountPrice;
    }

    public int calculateTotalDiscountExceptGiveaway(){
        int discountPrice = 0;

        for (Discount discount : discounts) {
            if(discount instanceof GiveawayDiscount){
                continue;
            }
            discountPrice -= discount.calculate();
        }
        return discountPrice;
    }

    public void calculateDiscount(Discount discount) {

        if (!discount.canDiscount()) {
            return;
        }
        discounts.add(discount);
    }
}
