package com.saucedemo.pages;

import java.text.DecimalFormat;
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
 * CheckoutOverview
 */
public class CheckoutOverview {

    WebDriver driver;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    WebElement cartItemQuantity;

    @FindBy(xpath = "//div[@class='summary_quantity']")
    List<WebElement> itemQuantity;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    List<WebElement> itemName;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    List<WebElement> itemDesc;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemPrice;

    @FindBy(xpath = "//div[normalize-space()='SauceCard #31337']")
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
        PageFactory.initElements(driver, this);
    }

    public void validateCheckoutInformation() {
        logger.info("**** Executing validateCheckoutInformation method in the CheckoutOverview class ****");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        waitsFactory.explicitWait(btnCancel);
        int cartQty = 0;
        double totalPrice = 0.0;

        for (int i = 0; i < itemQuantity.size(); i++) {
            cartQty += Integer.parseInt(itemQuantity.get(i).getText());
            totalPrice += Double.parseDouble(itemPrice.get(i).getText().substring(1));
            Assert.assertTrue(itemName.get(i).isDisplayed(), "Error! Item " + i + " name not displayed");
            Assert.assertTrue(itemDesc.get(i).isDisplayed(), "Error! Item " + i + " description not displayed");
        }

        waitsFactory.explicitWait(totalAmount);
        Assert.assertTrue(paymentInfo.getText().equalsIgnoreCase("SauceCard #31337"),
                "Error! Payment information mismatch");
        Assert.assertTrue(shippingInfo.getText().equalsIgnoreCase("FREE PONY EXPRESS DELIVERY!"),
                "Error! Shipping information mismatch");
        Assert.assertTrue((cartQty == Integer.parseInt(cartItemQuantity.getText())),
                "Error! cart quantity and items quantity are not the same amount");
        Assert.assertTrue((totalPrice == Double.parseDouble(itemTotalAmount.getText().substring(1))),
                "Error! item price sum and items total prices are not the same amount");
        String strTax = decimalFormat.format(Double.toString(totalPrice * 0.008004002));
        Assert.assertEquals("$" + strTax.equalsIgnoreCase(taxAmount.getText()),
                "Error! Calculated tax amount mismatch");
        Double finalTotalPrice = (Double.parseDouble(strTax) + totalPrice);
        Assert.assertTrue((finalTotalPrice == Double.parseDouble(decimalFormat.format(totalAmount.getText()))),
                "Error! Calculated final Total price amount mismatch");

    }

}