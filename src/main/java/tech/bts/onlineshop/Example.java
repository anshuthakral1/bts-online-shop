package tech.bts.onlineshop;

import tech.bts.onlineshop.business.PurchaseService;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

import java.util.Arrays;
import java.util.List;

public class Example {

    public static void main(String[] args) {

        Product p1 = new Product("MacBook", "Apple", 1500);
        Product p2 = new Product("iPhone xs", "Apple", 1200);
        Product p3 = new Product("Pixel 3", "Google", 900);

        ProductDatabase productDatabase = new ProductDatabase();
        productDatabase.add(p1);
        productDatabase.add(p2);
        productDatabase.add(p3);

        Product product = productDatabase.get(3);
        System.out.println("The name of the product is: " + product.getName());

        int count = productDatabase.getCount();
        System.out.println("I have " + count + " products in the database");

        int countApple = productDatabase.getCountByBrand("Apple");
        System.out.println("I have " + countApple + " Apple products");

        List<Product> productsByApple = productDatabase.getByBrand("Apple");
        System.out.println("Products by Apple: " + productsByApple);
        for (Product p : productsByApple) {
            System.out.println(p.getName() + ", " + p.getBrand() + ", " + p.getPrice());
        }

        List<Product> under1000 = productDatabase.getByPriceRange(500, 1000);
        System.out.println("Products under 1000: " + under1000);
        for (Product p : under1000) {
            System.out.println(p.getName() + ", " + p.getBrand() + ", " + p.getPrice());
        }

        System.out.println("I will remove the iPhone XS");
        System.out.println(productDatabase.remove(2));
        // productDatabase.remove(2);
        System.out.println(productDatabase.getAll());

        long requestedId = 2;
        Product requestedProduct = productDatabase.get(requestedId);

        if (requestedProduct != null) {
            System.out.println(requestedProduct);
        } else {
            System.out.println("This product doesn't exist");
        }

        List<CartItem> items = Arrays.asList(
                new CartItem(p1, 2),
                new CartItem(p2, 3),
                new CartItem(p3, 1));

        ShoppingCart cart = new ShoppingCart(items);

        PurchaseService purchaseService = new PurchaseService();

        double total = purchaseService.calculateTotalAmount(cart);
        System.out.println("Total amount of cart: " + total);
    }
}
