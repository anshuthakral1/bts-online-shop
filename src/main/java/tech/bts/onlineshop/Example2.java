package tech.bts.onlineshop;

import tech.bts.onlineshop.business.ProductService;
import tech.bts.onlineshop.data.ProductDatabase;
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

        long requestedId = macBookId;
        System.out.println("There are " + ps1.getById(requestedId).getQuantity() + " units of " + ps1.getById(requestedId).getName() + " in stock");
    }
}




