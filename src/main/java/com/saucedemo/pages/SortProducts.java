package com.saucedemo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * The {@code SortProducts} class represents the functionality for sorting products on the inventory page 
 * of the SauceDemo web application. It allows sorting products by name (A to Z, Z to A) and price 
 * (low to high, high to low).
 *
 * <p>
 * Key features include:
 * </p>
 * <ul>
 *   <li>Sorting products alphabetically by name (A to Z and Z to A).</li>
 *   <li>Sorting products by price (low to high and high to low).</li>
 *   <li>Returning the sorted list of product names or prices for further verification.</li>
 * </ul>
 *
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li>{@link org.openqa.selenium.WebDriver} for interacting with the web page.</li>
 *   <li>{@link org.openqa.selenium.WebElement} for locating and interacting with page elements.</li>
 *   <li>{@link org.openqa.selenium.support.ui.Select} for handling dropdown menus.</li>
 *   <li>Log4j for logging operations.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <pre>{@code
 * WebDriver driver = new ChromeDriver();
 * SortProducts sortProducts = new SortProducts(driver);
 * List<WebElement> sortedProducts = sortProducts.sortFromAtoZ();
 * for (WebElement product : sortedProducts) {
 *     System.out.println(product.getText());
 * }
 * }</pre>
 *
 * <h3>Methods:</h3>
 * <ul>
 *   <li>{@code sortFromAtoZ()}: Sorts the product list alphabetically from A to Z and returns the sorted list of product names.</li>
 *   <li>{@code sortFromZtoA()}: Sorts the product list alphabetically from Z to A and returns the sorted list of product names.</li>
 *   <li>{@code sortFromLowToHigh()}: Sorts the product list by price in ascending order and returns the sorted list of prices.</li>
 *   <li>{@code sortFromHighToLow()}: Sorts the product list by price in descending order and returns the sorted list of prices.</li>
 * </ul>
 *
 * <h3>Note:</h3>
 * Ensure that the product list is displayed on the inventory page before invoking the sort methods.
 * The sorted results are returned as {@link List} of {@link WebElement} objects, and additional validation 
 * may be required in the calling method.
 *
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.WebElement
 * @see org.openqa.selenium.support.ui.Select
*
 * @author Banele Mlamleli
 * @version 1.0
 */

public class SortProducts {

        WebDriver driver;

        @FindBy(xpath = "//select[@class='product_sort_container']")
        WebElement cmbSort;

        @FindBy(xpath = "//div[@class='inventory_item_name']")
        List<WebElement> inventoryItemNames;

        @FindBy(xpath = "//div[@class='inventory_item_price']")
        List<WebElement> inventoryItemPrices;

        Logger logger = LogManager.getLogger(new Object() {
        }.getClass().getName());

        public SortProducts(WebDriver driver) {
                logger.info("**** Executing constructor for SortProducts class ****");
                this.driver = driver;
                PageFactory.initElements(driver, this);
        }

        public List<WebElement> sortFromAtoZ() {
                logger.info("**** Executing sortFromAtoZ method for SortProducts class ****");
                Select sortByName = new Select(cmbSort);
                cmbSort.click();
                sortByName.selectByValue("az");
                return inventoryItemNames;
        }

        public List<WebElement> sortFromZtoA() {
                logger.info("**** Executing sortFromZtoA method for SortProducts class ****");
                Select sortByName = new Select(cmbSort);
                cmbSort.click();
                sortByName.selectByValue("za");
                return inventoryItemNames;
        }

        public List<WebElement> sortFromLowToHigh() {
                logger.info("**** Executing sortFroLowToHigh method for SortProducts class ****");
                Select sortByPrice = new Select(cmbSort);
                cmbSort.click();
                sortByPrice.selectByVisibleText("Price (low to high)");
                return inventoryItemPrices;
        }

        public List<WebElement> sortFromHighToLow() {
                logger.info("**** Executing sortFromHighToLow method for SortProducts class ****");
                Select sortByPrice = new Select(cmbSort);
                cmbSort.click();
                sortByPrice.selectByVisibleText("Price (high to low)");
                return inventoryItemPrices;
        }

}
