package com.saucedemo.tests;

import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.CheckoutInfo;
import com.saucedemo.utilities.TestDataProvider;

public class CheckoutInfoTest extends BaseClass {

    @Test(priority = 1, dataProvider = "dtpCheckoutInfo", dataProviderClass = TestDataProvider.class)
    public void validateCheckoutUserInfo(String name, String lastname, String postalCode) {
        new CheckoutInfo(driver).checkoutUserInfo(name, lastname, postalCode);
    }

    @Test
    public void verifyCancelCheckoutUserInfo() {
        new CheckoutInfo(driver).cancelCheckoutUserInfo();
    }

}
