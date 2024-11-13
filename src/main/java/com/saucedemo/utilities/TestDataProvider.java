package com.saucedemo.utilities;

import org.testng.annotations.*;

/**
 * DataProvider
 */
public class TestDataProvider {

    @DataProvider(name = "dtpCheckoutInfo")
    public Object[][] checkoutUserDetails() {
        return new Object[][] {
                { "Sam", "Mlamleli", "7100" }
        };
    }

}