package com.herve.saucedemo.pages;

import com.herve.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators live here, and only here
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /** Successful login moves the user to the inventory page. */
    public InventoryPage loginAs(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new InventoryPage(driver);
    }

    /** Failed login stays on this page so the test can read the error. */
    public LoginPage attemptLogin(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
