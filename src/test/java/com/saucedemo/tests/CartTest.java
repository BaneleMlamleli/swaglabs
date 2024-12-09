package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Cart;

public class CartTest extends BaseClass {

    @Test(priority = 1, groups = { "CartTest.validateSpanIsDisplayed" })
    public void validateSpanIsDisplayed() {
        new Cart(driver).validateSpanItemIsDisplayed();
    }

    @Test(dependsOnMethods = "validateSpanIsDisplayed", groups = { "CartTest.validateCartWithItems" })
    public void validateCartWithItems() {
        new Cart(driver).clickCartWithItems();
    }
}