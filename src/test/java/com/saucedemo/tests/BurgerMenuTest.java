package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.BurgerMenu;
import com.saucedemo.pages.Login;
import com.saucedemo.utilities.ConfigReader;

@Listeners(com.saucedemo.utilities.ExtentReportsUtil.class)
class BurgerMenuTest extends BaseClass {

    @Test
    public void login() {
        String correctUsername = new ConfigReader().getProperty("standard_user");
        String correctPassword = new ConfigReader().getProperty("password");
        Login login = new Login(driver);
        login.loginPage(correctUsername, correctPassword);
    }

    @Test(dependsOnMethods = { "login" }, priority = 1)
    public void validateClickOnAllItemsMenuOption() {
        Assert.assertTrue(new BurgerMenu(driver).clickAllItems(), "Products page not displayed");
    }

    @Test(dependsOnMethods = { "login" }, priority = 2)
    public void validateClickOnAboutMenuOption() {
        Assert.assertEquals(new BurgerMenu(driver).clickAbout(), "https://saucelabs.com/");
    }

    @Test(dependsOnMethods = { "login" }, priority = 3)
    public void validateClickOnResetAppStateMenuOption() {
        Assert.assertTrue(new BurgerMenu(driver).clickResetAppState(), "App state not reset");
    }

    @Test(dependsOnMethods = { "login" }, priority = 4)
    public void validateClickOnLogoutMenuOption() {
        Assert.assertEquals(new BurgerMenu(driver).clickLogout(),
                "https://www.saucedemo.com/v1/index.html");
    }

}