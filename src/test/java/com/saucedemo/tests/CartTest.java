package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Cart;

public class CartTest extends BaseClass {

    @Test(priority = 1)
    public void validateSpanIsDisplayed() {
        new Cart(driver).validateSpanItemIsDisplayed();
    }

    @Test(priority = 2)
    public void validateCartWithItems() {
        new Cart(driver).clickCartWithItems();
    }
}