package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.BurgerMenu;

class BurgerMenuTest extends BaseClass {

    @Test(priority = 1)
    public void validateClickOnAllItemsMenuOption() {
        Assert.assertTrue(new BurgerMenu(driver).clickAllItems(), "Products page not displayed");
    }

    @Test(priority = 2)
    public void validateClickOnAboutMenuOption() {
        Assert.assertEquals(new BurgerMenu(driver).clickAbout(), "https://saucelabs.com/");
    }

    @Test(priority = 3)
    public void validateClickOnResetAppStateMenuOption() {
        Assert.assertTrue(new BurgerMenu(driver).clickResetAppState(), "App state not reset");
    }

    @Test(priority = 4)
    public void validateClickOnLogoutMenuOption() {
        Assert.assertEquals(new BurgerMenu(driver).clickLogout(),
                "https://www.saucedemo.com/v1/index.html");
    }

}