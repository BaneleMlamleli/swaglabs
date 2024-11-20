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

    @FindBy(xpath = "//div[@class='summary_quantity']")
    List<WebElement> quantity;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    List<WebElement> itemName;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    List<WebElement> itemDesc;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemPrice;

    @FindBy(xpath = "//div[@class='summary_info_label']")
    List<WebElement> infoLabel;

    @FindBy(xpath = "//div[@class='summary_value_label']")
    List<WebElement> valueLabel;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement subtotalAmount;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxAmount;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    WebElement totalAmount;

    @FindBy(xpath = "//a[@class='cart_cancel_link btn_secondary']")
    WebElement btnCancel;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    WebElement btnFinish;

    WaitsFactory waitsFactory = new WaitsFactory();

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public CheckoutOverview(WebDriver driver) {
        logger.info("**** executing constructor for CheckoutOverview class****");
        this.driver = driver;
        PageFactory.initElements(driver, CheckoutOverview.class);
    }

}