package com.herve.saucedemo.pages;

import com.herve.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    /** Builds the add-to-cart locator from the product name, e.g. "Sauce Labs Backpack". */
    public InventoryPage addToCart(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(id));
        return this;
    }

    public int getCartCount() {
        return isDisplayed(cartBadge) ? Integer.parseInt(getText(cartBadge)) : 0;
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
