package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

public class ProductServiceTest {

    @Test
    public void empty_catalog_has_no_products() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        int count = productService.getCount();
        Assert.assertEquals(0, count);
    }

    @Test
    public void add_product_to_catalog() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);
        int count = productService.getCount();
        Assert.assertEquals(1, count);
        Product p = productService.getProductById(pixelId);
        Assert.assertEquals("pixel", p.getName());
    }

    @Test
    public void product_availability() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        Assert.assertEquals(false, productService.checkProductAvailability(pixelId, 500));

        productService.addProductStock(pixelId, 500);

        Assert.assertEquals(true, productService.checkProductAvailability(pixelId, 500));
    }

    @Test
    public void product_available_quantity() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        Assert.assertEquals(0, productService.getAvailableQuantity(pixelId, 50));

        productService.addProductStock(pixelId, 100);

        Assert.assertEquals(50, productService.getAvailableQuantity(pixelId, 50));
        Assert.assertEquals(100, productService.getAvailableQuantity(pixelId, 200));
    }

    @Test
    public void purchase_reduces_product_stock() {

        //1 - setup the necessary objects

        ProductService productService = new ProductService(new ProductDatabase());

        long pixelId = productService.createProductAndAddStock(new Product("pixel", "Google", 800),100);
        long iPhoneId = productService.createProductAndAddStock(new Product("iPhone", "Apple", 1000),200);
        long iPadId = productService.createProductAndAddStock(new Product("iPad", "Apple", 1200),50);

        productService.addProductStock(pixelId, 100);

        ShoppingCart cart = new ShoppingCart();
        cart.add(new CartItem(pixelId,30));
        cart.add(new CartItem(iPhoneId,10));

        //2 - calling the method(s) we are testing

        productService.purchase(cart);

        //3 - check expected results

        Assert.assertEquals(170, productService.getProductById(pixelId).getQuantity());
        Assert.assertEquals(190, productService.getProductById(iPhoneId).getQuantity());
        Assert.assertEquals(50, productService.getProductById(iPadId).getQuantity());
    }

    @Test
    public void purchase_more_than_product_stock() {
        ProductService productService = new ProductService(new ProductDatabase());

        long pixelId = productService.createProductAndAddStock(new Product("pixel", "Google", 800),100);
        ShoppingCart cart = new ShoppingCart();
        cart.add(new CartItem(pixelId,200));

        productService.purchase(cart);

        Assert.assertEquals(0, productService.getProductById(pixelId).getQuantity());
    }
}