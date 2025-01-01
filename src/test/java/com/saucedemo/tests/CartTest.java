package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Cart;
import com.saucedemo.pages.Login;
import com.saucedemo.utilities.ConfigReader;

public class CartTest extends BaseClass {

    @Test
    public void login() {
        String correctUsername = new ConfigReader().getProperty("standard_user");
        String correctPassword = new ConfigReader().getProperty("password");
        Login login = new Login(driver);
        login.loginPage(correctUsername, correctPassword);
    }

    @Test(dependsOnMethods = { "login" }, priority = 1, groups = { "CartTest.validateSpanIsDisplayed" })
    public void validateSpanIsDisplayed() {
        new Cart(driver).validateSpanItemIsDisplayed();
    }

    @Test(dependsOnMethods = "validateSpanIsDisplayed", groups = { "CartTest.validateCartWithItems" })
    public void validateCartWithItems() {
        new Cart(driver).clickCartWithItems();
    }
}