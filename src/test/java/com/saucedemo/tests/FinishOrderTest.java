package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.CheckoutInfo;
import com.saucedemo.pages.FinishOrder;
import com.saucedemo.pages.Login;
import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.TestDataProvider;

public class FinishOrderTest extends BaseClass {

    @Test
    public void login() {
        String correctUsername = new ConfigReader().getProperty("standard_user");
        String correctPassword = new ConfigReader().getProperty("password");
        Login login = new Login(driver);
        login.loginPage(correctUsername, correctPassword);
    }

    @Test(dependsOnMethods = {
            "login" }, groups = "execute_verify_order_completion_method", priority = 1, dataProvider = "dtpCheckoutInfo", dataProviderClass = TestDataProvider.class)
    public void executeVerifyOrderCompletionMethod(String name, String lastname, String postalCode) {
        try {
            System.out.println("executeVerifyOrderCompletionMethod");
            new CheckoutInfo(driver).checkoutUserInfo(name, lastname, postalCode);
            new FinishOrder(driver).verifyOrderCompletion();
        } catch (Exception e) {
            logger.warn("*** Exception error ***");
            logger.info(e.getMessage());
        }
    }

}
