package com.saucedemo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * SortProducts
 */
public class SortProducts {

        WebDriver driver;

        @FindBy(xpath = "//select[@class='product_sort_container']")
        WebElement cmbSort;

        @FindBy(xpath = "//div[@class='inventory_item_name']")
        List<WebElement> invetoryItemNames;

        @FindBy(xpath = "//div[@class='inventory_item_price']")
        List<WebElement> invetoryItemPrices;

        Logger logger = LogManager.getLogger(new Object() {
        }.getClass().getName());

        public SortProducts(WebDriver driver) {
                logger.info("**** executing constructor for SortProducts class ****");
                this.driver = driver;
                PageFactory.initElements(driver, SortProducts.class);
        }

        public void sortFromAtoZ() {
                Select sortByName = new Select(cmbSort);
                sortByName.selectByValue("az");
                Assert.assertTrue(invetoryItemNames.get(0).getText().equals("Sauce Labs Backpack"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(invetoryItemNames.get(1).getText().equals("Sauce Labs Bike Light"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(invetoryItemNames.get(2).getText().equals("Sauce Labs Bolt T-Shirt"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(invetoryItemNames.get(3).getText().equals("Sauce Labs Fleece Jacket"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(invetoryItemNames.get(4).getText().equals("Sauce Labs Onesie"),
                                "Items not sorted from A to Z");
                Assert.assertTrue(invetoryItemNames.get(5).getText().equals("Test.allTheThings() T-Shirt (Red)"),
                                "Items not sorted from A to Z");
        }

        public void sortFromZtoA() {
                Select sortByName = new Select(cmbSort);
                sortByName.selectByValue("za");
                Assert.assertTrue(invetoryItemNames.get(0).getText().equals("Test.allTheThings() T-Shirt (Red)"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(invetoryItemNames.get(1).getText().equals("Sauce Labs Onesie"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(invetoryItemNames.get(2).getText().equals("Sauce Labs Fleece Jacket"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(invetoryItemNames.get(3).getText().equals("Sauce Labs Bolt T-Shirt"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(invetoryItemNames.get(4).getText().equals("Sauce Labs Bike Light"),
                                "Items not sorted from Z to A");
                Assert.assertTrue(invetoryItemNames.get(5).getText().equals("Sauce Labs Backpack"),
                                "Items not sorted from Z to A");
        }

        public void sortFromLowToHigh() {
                Select sortByPrice = new Select(cmbSort);
                sortByPrice.selectByValue("lohi");
                Assert.assertTrue(invetoryItemPrices.get(0).getText().equals("S$7.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(invetoryItemPrices.get(1).getText().equals("$9.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(invetoryItemPrices.get(2).getText().equals("$15.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(invetoryItemPrices.get(3).getText().equals("$29.99"),
                                "Item prices not sorted from Low to High");
                Assert.assertTrue(invetoryItemPrices.get(4).getText().equals("$49.99"),
                                "Item prices not sorted from Low to High");
        }

        public void sortFromHighToLow() {
                Select sortByPrice = new Select(cmbSort);
                sortByPrice.selectByValue("hilo");
                Assert.assertTrue(invetoryItemPrices.get(0).getText().equals("$49.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(invetoryItemPrices.get(0).getText().equals("$29.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(invetoryItemPrices.get(0).getText().equals("$15.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(invetoryItemPrices.get(0).getText().equals("$9.99"),
                                "Item prices not sorted from High to Low");
                Assert.assertTrue(invetoryItemPrices.get(0).getText().equals("$7.99"),
                                "Item prices not sorted from High to Low");
        }
}