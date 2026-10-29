package com.herve.saucedemo.tests;

import com.herve.saucedemo.base.BaseTest;
import com.herve.saucedemo.pages.InventoryPage;
import com.herve.saucedemo.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Valid user lands on the products page")
    public void validUserCanLogIn() {
        InventoryPage inventory = loginPage()
                .loginAs(ConfigReader.get("standardUser"), ConfigReader.get("password"));

        Assert.assertEquals(inventory.getTitle(), "Products");
    }

    @Test(groups = {"regression"}, description = "Locked-out user sees the correct error")
    public void lockedOutUserIsBlocked() {
        String error = loginPage()
                .attemptLogin(ConfigReader.get("lockedUser"), ConfigReader.get("password"))
                .getErrorMessage();

        Assert.assertTrue(error.contains("locked out"), "Unexpected error: " + error);
    }

    /** Negative cases: each row is username, password, expected error text. */
    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins() {
        return new Object[][]{
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"},
                {"standard_user", "wrong_password", "do not match"}
        };
    }

    @Test(groups = {"regression"}, dataProvider = "invalidLogins",
          description = "Invalid credentials show the right validation message")
    public void invalidLoginShowsError(String username, String password, String expectedError) {
        String error = loginPage().attemptLogin(username, password).getErrorMessage();

        Assert.assertTrue(error.contains(expectedError),
                "Expected '" + expectedError + "' but got: " + error);
    }
}
