package tech.bts.onlineshop.business;

import tech.bts.onlineshop.model.Discount;

import java.util.HashMap;
import java.util.Map;

public class DiscountService {

    private Map<String, Discount> discountMap;

    public DiscountService() {
        this.discountMap = new HashMap<>();
    }

    public void createDiscount(Discount discount) {
        discountMap.put(discount.getId(), discount);
    }

    public double calculateFinalAmount(String id, double originalAmount) {

        Discount discount = discountMap.get(id);
        boolean isPercentage = discount.isPercentage();

        if (discount != null) {
            if (isPercentage) { return originalAmount * (1 - discount.getAmount()/100);
            } else { return originalAmount - discount.getAmount();}
        } else { return originalAmount;}
    }
}
