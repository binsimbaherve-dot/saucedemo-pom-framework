package com.herve.saucedemo.tests;

import com.herve.saucedemo.base.BaseTest;
import com.herve.saucedemo.pages.CartPage;
import com.herve.saucedemo.pages.InventoryPage;
import com.herve.saucedemo.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    @Test(groups = {"regression"}, description = "Adding two products updates the badge and the cart")
    public void addedProductsAppearInCart() {
        InventoryPage inventory = loginPage()
                .loginAs(ConfigReader.get("standardUser"), ConfigReader.get("password"))
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Bike Light");

        Assert.assertEquals(inventory.getCartCount(), 2, "Cart badge count");

        CartPage cart = inventory.openCart();
        List<String> items = cart.getItemNames();

        Assert.assertEquals(items.size(), 2);
        Assert.assertTrue(items.contains("Sauce Labs Backpack"));
        Assert.assertTrue(items.contains("Sauce Labs Bike Light"));
    }
}
