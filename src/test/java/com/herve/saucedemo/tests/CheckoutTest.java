package com.herve.saucedemo.tests;

import com.herve.saucedemo.base.BaseTest;
import com.herve.saucedemo.pages.CheckoutPage;
import com.herve.saucedemo.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Happy path: a user can buy a product end to end")
    public void userCanCompleteCheckout() {
        String confirmation = loginPage()
                .loginAs(ConfigReader.get("standardUser"), ConfigReader.get("password"))
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .enterDetails("Herve", "Simba", "2001")
                .finish()
                .getConfirmationMessage();

        Assert.assertEquals(confirmation, "Thank you for your order!");
    }

    @Test(groups = {"regression"}, description = "Checkout is blocked when the postal code is missing")
    public void checkoutRequiresPostalCode() {
        CheckoutPage checkout = loginPage()
                .loginAs(ConfigReader.get("standardUser"), ConfigReader.get("password"))
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .enterDetails("Herve", "Simba", "");

        Assert.assertTrue(checkout.getErrorMessage().contains("Postal Code is required"));
    }
}
