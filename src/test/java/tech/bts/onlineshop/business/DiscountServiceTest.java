package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.model.Discount;

public class DiscountServiceTest {

    @Test
    public void applyDiscount() {

        Discount discount1 = new Discount("SUMMERSALES","Summer Sales",25, true);
        Discount discount2 = new Discount("VOUCHER20","Voucher 20",20, false);
        Discount discount3 = new Discount("VOUCHER50","Voucher 50",50, false);

        DiscountService discountService = new DiscountService();

        discountService.createDiscount(discount1);
        discountService.createDiscount(discount2);

        Assert.assertEquals(75, discountService.calculateFinalAmount(discount1.getId(), 100),0.0);
        Assert.assertEquals(80, discountService.calculateFinalAmount(discount2.getId(), 100),0.0);
        Assert.assertEquals(100, discountService.calculateFinalAmount(discount3.getId(), 100),0.0);
    }
}
