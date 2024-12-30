package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.CheckoutInfo;
import com.saucedemo.pages.Login;
import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.TestDataProvider;

public class CheckoutInfoTest extends BaseClass {

    @Test
    public void login() {
        String correctUsername = new ConfigReader().getProperty("standard_user");
        String correctPassword = new ConfigReader().getProperty("password");
        Login login = new Login(driver);
        login.loginPage(correctUsername, correctPassword);
    }

    @Test(dependsOnMethods = {
            "login" }, priority = 1, dataProvider = "dtpCheckoutInfo", dataProviderClass = TestDataProvider.class)
    public void validateCheckoutUserInfo(String name, String lastname, String postalCode) {
        new CheckoutInfo(driver).checkoutUserInfo(name, lastname, postalCode);
    }

    @Test(priority = 2)
    public void verifyCancelCheckoutUserInfo() {
        new CheckoutInfo(driver).cancelCheckoutUserInfo();
    }

}
