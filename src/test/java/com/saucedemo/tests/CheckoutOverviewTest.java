package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.CheckoutInfo;
import com.saucedemo.pages.CheckoutOverview;
import com.saucedemo.pages.Login;
import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.TestDataProvider;

public class CheckoutOverviewTest extends BaseClass {

    @Test
    public void login() {
        String correctUsername = new ConfigReader().getProperty("standard_user");
        String correctPassword = new ConfigReader().getProperty("password");
        Login login = new Login(driver);
        login.loginPage(correctUsername, correctPassword);
    }

    @Test(dependsOnMethods = {
            "login" }, groups = "execute_validate_checkout_information_method", priority = 1, dataProvider = "dtpCheckoutInfo", dataProviderClass = TestDataProvider.class)
    public void executeValidateCheckoutInformationMethod(String name, String lastname, String postalCode) {
        try {
            new CheckoutInfo(driver).checkoutUserInfo(name, lastname, postalCode);
            new CheckoutOverview(driver).validateCheckoutInformation();
        } catch (Exception e) {
            logger.error("**** Exception error ****");
            logger.error(e.getMessage());
        }
    }
}
