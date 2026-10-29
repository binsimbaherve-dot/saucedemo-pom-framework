package com.herve.saucedemo.pages;

import com.herve.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Covers the three checkout steps: your information, overview and confirmation. */
public class CheckoutPage extends BasePage {

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterDetails(String first, String last, String postal) {
        type(firstName, first);
        type(lastName, last);
        type(postalCode, postal);
        click(continueButton);
        return this;
    }

    public CheckoutPage finish() {
        click(finishButton);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }
}
