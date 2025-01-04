package com.saucedemo.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucedemo.core.BaseClass;
import com.saucedemo.pages.Login;
import com.saucedemo.pages.SortProducts;
import com.saucedemo.utilities.ConfigReader;

@Listeners(com.saucedemo.utilities.ExtentReportsUtil.class)
public class SortProductsTest extends BaseClass {

        @Test(priority = 1)
        public void login() {
                String correctUsername = new ConfigReader().getProperty("standard_user");
                String correctPassword = new ConfigReader().getProperty("password");
                Login login = new Login(driver);
                login.loginPage(correctUsername, correctPassword);
        }

        @Test(priority = 2)
        public void executeSortFromAtoZ() {
                List<WebElement> inventoryItemNames = new SortProducts(driver).sortFromAtoZ();
                Assert.assertTrue(inventoryItemNames.get(0).getText().equals("Sauce Labs Backpack"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(inventoryItemNames.get(1).getText().equals("Sauce Labs Bike Light"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(inventoryItemNames.get(2).getText().equals("Sauce Labs Bolt T-Shirt"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(inventoryItemNames.get(3).getText().equals("Sauce Labs Fleece Jacket"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(inventoryItemNames.get(4).getText().equals("Sauce Labs Onesie"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(inventoryItemNames.get(5).getText().equals("Test.allTheThings() T-Shirt (Red)"),
                                "Items not sorted from A to Z");
        }

        @Test(priority = 3)
        public void executeSortFromZtoA() {
                List<WebElement> inventoryItemNames = new SortProducts(driver).sortFromZtoA();
                Assert.assertTrue(inventoryItemNames.get(0).getText().equals("Test.allTheThings() T-Shirt (Red)"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(inventoryItemNames.get(1).getText().equals("Sauce Labs Onesie"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(inventoryItemNames.get(2).getText().equals("Sauce Labs Fleece Jacket"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(inventoryItemNames.get(3).getText().equals("Sauce Labs Bolt T-Shirt"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(inventoryItemNames.get(4).getText().equals("Sauce Labs Bike Light"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(inventoryItemNames.get(5).getText().equals("Sauce Labs Backpack"),
                                "Items not sorted from Z to A");
        }

        @Test(priority = 4)
        public void executeSortFromLowToHigh() {
                List<WebElement> inventoryItemPrices = new SortProducts(driver).sortFromLowToHigh();
                Assert.assertTrue(inventoryItemPrices.get(0).getText().equals("$7.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(inventoryItemPrices.get(1).getText().equals("$9.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(inventoryItemPrices.get(2).getText().equals("$15.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(inventoryItemPrices.get(3).getText().equals("$15.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(inventoryItemPrices.get(4).getText().equals("$29.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(inventoryItemPrices.get(5).getText().equals("$49.99"),
                                "Item prices not sorted from Low to High");
        }

        @Test(priority = 5)
        public void executeSortFromHighToLow() {
                List<WebElement> inventoryItemPrices = new SortProducts(driver).sortFromHighToLow();
                Assert.assertTrue(inventoryItemPrices.get(0).getText().equals("$49.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(inventoryItemPrices.get(1).getText().equals("$29.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(inventoryItemPrices.get(2).getText().equals("$15.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(inventoryItemPrices.get(3).getText().equals("$15.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(inventoryItemPrices.get(4).getText().equals("$9.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(inventoryItemPrices.get(5).getText().equals("$7.99"),
                                "Item prices not sorted from High to Low");
        }
}
