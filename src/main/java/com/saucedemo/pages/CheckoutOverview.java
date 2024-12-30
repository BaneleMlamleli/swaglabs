package com.saucedemo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.utilities.WaitsFactory;

/**
 * CheckoutOverview
 */
public class CheckoutOverview {

    WebDriver driver;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement cartItemQuantity;

    @FindBy(xpath = "//div[@class='summary_quantity']")
    List<WebElement> quantity;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    List<WebElement> itemName;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    List<WebElement> itemDesc;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemPrice;

    @FindBy(xpath = "(//div[normalize-space()='SauceCard #31337'])[1]")
    WebElement paymentInfo;

    @FindBy(xpath = "//div[normalize-space()='FREE PONY EXPRESS DELIVERY!']")
    WebElement shippingInfo;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement itemTotalAmount;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxAmount;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    WebElement totalAmount;

    @FindBy(xpath = "//a[@class='cart_cancel_link btn_secondary']")
    WebElement btnCancel;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    WebElement btnFinish;

    WaitsFactory waitsFactory;

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public CheckoutOverview(WebDriver driver) {
        logger.info("**** Executing constructor for CheckoutOverview class ****");
        this.driver = driver;
        waitsFactory = new WaitsFactory(driver);
        PageFactory.initElements(driver, CheckoutOverview.class);
    }

    public void validateCheckoutInformation() {
        logger.info("**** Executing validateCheckoutInformation method in the CheckoutOverview class ****");
        int cartQuantity = Integer.parseInt(cartItemQuantity.getText());
        System.out.println("cartQuantity: " + cartQuantity);
        String firstItemName = itemName.get(0).getText();
        System.out.println("firstItemName: " + firstItemName);
        String firstItemDescrioption = itemDesc.get(0).getText();
        System.out.println("firstItemDescrioption: " + firstItemDescrioption);
        String firstItemPrice = itemPrice.get(0).getText();
        System.out.println("firstItemPrice: " + firstItemPrice);
    }

}