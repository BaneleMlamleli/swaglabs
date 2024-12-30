package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Login;
import com.saucedemo.utilities.ConfigReader;

public class LoginTest extends BaseClass {

    String correctUsername = new ConfigReader().getProperty("standard_user");
    String lockedOutUser = new ConfigReader().getProperty("locked_out_user");
    String correctPassword = new ConfigReader().getProperty("password");
    String incorrectPassword = "wrongpassword01";
    String incorrectUsername = "wrongUsername";

    @Test(priority = 1)
    public void validLoginDetails() {
        new Login(driver).loginPage(correctUsername, correctPassword);
    }

    @Test(enabled = false)
    public void invalidLoginDetails() {
        for (int i = 0; i <= 6; i++) {
            switch (i) {
                case 0:
                    Assert.assertTrue(new Login(driver).verifyLoginPage(lockedOutUser, correctPassword)
                            .equalsIgnoreCase("Epic sadface: Sorry, this user has been locked out."));
                    break;
                case 1:
                    Assert.assertTrue(
                            new Login(driver).verifyLoginPage(incorrectUsername, correctPassword)
                                    .equalsIgnoreCase(
                                            "Epic sadface: Username and password do not match any user in this service"));
                    break;
                case 2:
                    Assert.assertTrue(
                            new Login(driver).verifyLoginPage(correctUsername, incorrectPassword)
                                    .equalsIgnoreCase(
                                            "Epic sadface: Username and password do not match any user in this service"));
                    break;
                case 3:
                    Assert.assertTrue(
                            new Login(driver).verifyLoginPage(incorrectUsername, incorrectPassword)
                                    .equalsIgnoreCase(
                                            "Epic sadface: Username and password do not match any user in this service"));
                    break;
                case 4:
                    Assert.assertFalse(new Login(driver).verifyLoginPage("", correctPassword)
                            .equalsIgnoreCase("Epic sadface: Username is required"));
                    break;
                case 5:
                    Assert.assertFalse(new Login(driver).verifyLoginPage(correctUsername, "")
                            .equalsIgnoreCase("Epic sadface: Password is required"));
                    break;
                case 6:
                    Assert.assertFalse(
                            new Login(driver).verifyLoginPage("", "")
                                    .equalsIgnoreCase("Epic sadface: Username is required"));
                    break;
            }
        }
    }

}