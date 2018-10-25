package tech.bts.onlineshop;

import tech.bts.onlineshop.business.ProductService;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;

public class Example2 {

    public static void main(String[] args) {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService ps1 = new ProductService(productDatabase);

        long macBookId = ps1.createProduct(new Product("MacBook", "Apple", 1500));
        long iphoneID = ps1.createProduct(new Product("iPhone xs", "Apple", 1200));

        ps1.addProductStock(macBookId,100);
        ps1.addProductStock(iphoneID, 350);
        ps1.addProductStock(macBookId,100);

        long requestedId = iphoneID;
        Product requestedProduct = ps1.getById(requestedId);
        System.out.println("There are " + requestedProduct.getQuantity() + " units of " + requestedProduct.getName() + " in stock");

        int requestedQuantity = 120;
        boolean availability = ps1.checkAvailability(requestedId,requestedQuantity);
        System.out.println(availability);

        System.out.println(ps1.possibleDelivery(requestedId,requestedQuantity));

        CartItem cart = new CartItem(requestedId, requestedQuantity);
        CartItem cart1 = ps1.deliverableCart(cart);
        System.out.println("Cart includes " + cart1.getQuantity() + " units of " + requestedProduct.getName());

    }
}


