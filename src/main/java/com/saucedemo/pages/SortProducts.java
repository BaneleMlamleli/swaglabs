package com.saucedemo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
                logger.info("**** executing constructor for SortProducts class ****");
                this.driver = driver;
                PageFactory.initElements(driver, this);
        }

        public List<WebElement> sortFromAtoZ() {
                logger.info("**** executing sortFromAtoZ method for SortProducts class ****");
                Select sortByName = new Select(cmbSort);
                cmbSort.click();
                sortByName.selectByValue("az");
                return inventoryItemNames;
        }

        public List<WebElement> sortFromZtoA() {
                logger.info("**** executing sortFromZtoA method for SortProducts class ****");
                Select sortByName = new Select(cmbSort);
                cmbSort.click();
                sortByName.selectByValue("za");
                return inventoryItemNames;
        }

        public List<WebElement> sortFromLowToHigh() {
                logger.info("**** executing sortFroLowToHigh method for SortProducts class ****");
                Select sortByPrice = new Select(cmbSort);
                cmbSort.click();
                sortByPrice.selectByVisibleText("Price (low to high)");
                return inventoryItemPrices;
        }

        public List<WebElement> sortFromHighToLow() {
                logger.info("**** executing sortFromHighToLow method for SortProducts class ****");
                Select sortByPrice = new Select(cmbSort);
                cmbSort.click();
                sortByPrice.selectByVisibleText("Price (high to low)");
                return inventoryItemPrices;
        }

}
