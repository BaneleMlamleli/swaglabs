package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.utilities.WaitsFactory;

/**
 * The FinishOrder class represents the final step in the order completion
 * process
 * on the SauceDemo website. It provides methods to validate the order
 * completion page.
 *
 * <p>
 * This class contains the following functionality:
 * </p>
 * <ul>
 * <li>Validating the URL of the finish page</li>
 * <li>Verifying the presence of confirmation messages</li>
 * <li>Ensuring the "Pony Express" image is displayed</li>
 * </ul>
 *
 * @author Banele Mlamleli
 * @version 1.0
 */
public class FinishOrder {

        WebDriver driver;

        @FindBy(xpath = "//div[@class='subheader']")
        WebElement txtFinish;

        @FindBy(xpath = "//h2[@class='complete-header']")
        WebElement txtThankYouMessage;

        @FindBy(xpath = "//div[@class='complete-text']")
        WebElement txtMessage;

        @FindBy(xpath = "//img[@class='pony_express']")
        WebElement imgPonyExpressSauceLabs;

        WaitsFactory waitsFactory;

        Logger logger = LogManager.getLogger(new Object() {
        }.getClass().getName());

        public FinishOrder(WebDriver driver) {
                logger.info("**** Executing constructor for FinishOrder class ****");
                this.driver = driver;
                waitsFactory = new WaitsFactory(driver);
                PageFactory.initElements(driver, this);
        }

        /**
         * Verifies that the order completion page is displayed correctly.
         * <ul>
         * <li>Validates the URL of the page</li>
         * <li>Checks for the presence of the "Finish" header</li>
         * <li>Ensures the thank-you message is displayed</li>
         * <li>Verifies the additional confirmation message</li>
         * <li>Ensures the "Pony Express" image is visible</li>
         * </ul>
         * 
         * @throws AssertionError if any validation fails
         */
        public void verifyOrderCompletion() {
                logger.info("**** Executing verifyOrderCompletion method in the FinishOrder class ****");
                String checkoutCompleteUrl = driver.getCurrentUrl();
                Assert.assertTrue(
                                checkoutCompleteUrl.equalsIgnoreCase(
                                                "https://www.saucedemo.com/v1/checkout-complete.html"),
                                "Incorrect url for 'Finish' page");
                Assert.assertTrue(txtFinish.getText().equalsIgnoreCase("Finish"),
                                "Error, 'Finish' header not displayed");
                Assert.assertTrue(txtThankYouMessage.getText().equalsIgnoreCase("THANK YOU FOR YOUR ORDER"),
                                "Error, Thank you message not displayed");
                Assert.assertTrue(
                                txtMessage.getText().equalsIgnoreCase(
                                                "Your order has been dispatched, and will arrive just as fast as the pony can get there!"),
                                "Error, '...order has been dispatched...' message not displayed");
                // waitsFactory.explicitWait(imgPonyExpressSauceLabs);
                Assert.assertTrue(imgPonyExpressSauceLabs.isDisplayed(), "Error, image not displayed");
        }
}
