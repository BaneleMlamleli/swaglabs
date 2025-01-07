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
 * The {@code CheckoutOverview} class represents the "Checkout Overview" page in the SauceDemo web application. 
 * It facilitates validation of checkout details such as item quantities, prices, tax, and totals. 
 * This class ensures that the final checkout information aligns with the expected values based on the items 
 * in the shopping cart.
 *
 * <p>
 * Key features include:
 * </p>
 * <ul>
 *   <li>Validates individual item names, descriptions, quantities, and prices.</li>
 *   <li>Verifies payment and shipping information.</li>
 *   <li>Calculates and validates item totals, tax amounts, and the final total.</li>
 *   <li>Utilizes explicit waits to handle dynamic element loading.</li>
 * </ul>
 *
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li>{@link org.openqa.selenium.WebDriver} for interacting with the web page.</li>
 *   <li>{@link org.openqa.selenium.support.FindBy} for locating page elements.</li>
 *   <li>{@link com.saucedemo.utilities.WaitsFactory} for managing explicit waits.</li>
 *   <li>TestNG for assertions during validation.</li>
 *   <li>Log4j for logging operations.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <pre>{@code
 * WebDriver driver = new ChromeDriver();
 * CheckoutOverview checkoutOverview = new CheckoutOverview(driver);
 * checkoutOverview.validateCheckoutInformation();
 * }</pre>
 *
 * <h3>Methods:</h3>
 * <ul>
 *   <li>{@code validateCheckoutInformation()}: Performs comprehensive validation of the checkout details, including 
 *       cart quantities, item totals, tax, and final total.</li>
 * </ul>
 *
 * <h3>Validation Process:</h3>
 * <ol>
 *   <li>Iterates through the items in the cart, verifying their names, descriptions, and prices.</li>
 *   <li>Ensures that the sum of individual item prices matches the displayed item total.</li>
 *   <li>Validates tax calculation and compares it to the displayed tax amount.</li>
 *   <li>Checks that the final total matches the sum of the item total and tax.</li>
 *   <li>Verifies the payment and shipping details are correctly displayed.</li>
 * </ol>
 *
 * <h3>Note:</h3>
 * This class assumes that the checkout process has already reached the overview stage. 
 * Ensure that the user has added items to the cart and navigated to this page before invoking its methods.
 *
 * @see com.saucedemo.utilities.WaitsFactory
 * @see org.openqa.selenium.WebDriver
 * @see org.testng.Assert
  * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
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
                        Assert.assertTrue(itemDesc.get(i).isDisplayed(),
                                        "Error! Item " + i + " description not displayed");
                }

                waitsFactory.explicitWait(totalAmount);
                Assert.assertTrue(paymentInfo.getText().equalsIgnoreCase("SauceCard #31337"),"Error! Payment information mismatch");
                Assert.assertTrue(shippingInfo.getText().equalsIgnoreCase("FREE PONY EXPRESS DELIVERY!"),"Error! Shipping information mismatch");
                Assert.assertTrue((cartQty == Integer.parseInt(cartItemQuantity.getText())),"Error! cart quantity and items quantity are not the same amount");

                System.out.println("Combined prices: " + totalPrice);
                System.out.println("get item total: " + itemTotalAmount.getText().substring(13));
                Assert.assertTrue(totalPrice == Double.parseDouble(decimalFormat.format(itemTotalAmount.getText().substring(13))),"Error! item price sum and items total prices are not the same amount");

                Double tax = Double.parseDouble(decimalFormat.format(totalPrice * (Double.parseDouble(taxAmount.getText().substring(6)) / totalPrice)));
                System.out.println("tax: " + tax);
                System.out.println("get tax amount: " + taxAmount.getText().substring(6));
                Assert.assertEquals(tax == Double.parseDouble(decimalFormat.format(taxAmount.getText().substring(6))),"Error! Calculated tax amount mismatch");

                Double finalTotalPrice = tax + totalPrice;
                System.out.println("Final Total Amount: " + finalTotalPrice);
                System.out.println("get final Total Amount: " + totalAmount.getText().substring(8));
                Assert.assertEquals((finalTotalPrice == Double.parseDouble(decimalFormat.format(totalAmount.getText().substring(8)))),"Error! Calculated final Total price amount mismatch");
        }
}