package com.saucedemo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.utilities.WaitsFactory;

/**
 * The Cart class represents the shopping cart page of the e-commerce
 * application.
 * It provides methods to interact with the cart and verify cart actions.
 * 
 * <p>
 * This class contains methods to perform actions related to the cart and verify
 * items in the cart:
 * </p>
 * <ul>
 * <li>{@link #cartAction()}</li>
 * <li>{@link #cartWithItems()}</li>
 * </ul>
 * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
 */
public class Cart {

    WebDriver driver;

    @FindBy(xpath = "//*[name()='path' and contains(@fill,'currentCol')]")
    WebElement cart;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement spnItemsSelected;

    @FindBy(xpath = "//div[@class='inventory_list']//div[1]//div[3]//button[1]")
    WebElement btnBackpack;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement shoppingCartItemCounter;

    @FindBy(xpath = "//div[@class='cart_quantity']")
    List<WebElement> itemQuantity;

    WaitsFactory waitsFactory;

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public Cart(WebDriver driver) {
        logger.info("**** Executing constructor for Cart class ****");
        this.driver = driver;   
        waitsFactory = new WaitsFactory(driver);
        PageFactory.initElements(driver, this);
    }

    public void validateSpanItemIsDisplayed() {
        logger.info("**** Executing validateSpanItemIsDisplayed method ****");
        waitsFactory.explicitWaitButtonClickable(btnBackpack);

        btnBackpack.click();
        waitsFactory.explicitWait(spnItemsSelected);
        Assert.assertTrue(spnItemsSelected.isDisplayed(), "No item/s in the span element");
    }

    public void clickCartWithItems() {
        logger.info("**** Executing clickCartWithItems method ****");
        waitsFactory.explicitWait(cart);
        waitsFactory.explicitWaitButtonClickable(cart);
        Assert.assertTrue(cart.isDisplayed(), "Cart element not visible or actionable");

        cart.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/cart.html");

        // confirm number of items match the amount specified in shopping cart item counter
        Assert.assertTrue(Integer.parseInt(shoppingCartItemCounter.getText()) == itemQuantity.size(),
                "The amount of items does not match the amount specified in shopping cart item counter");

    }
}