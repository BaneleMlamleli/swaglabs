package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.utilities.WaitsFactory;

/**
 * The {@code CheckoutInfo} class represents the checkout information page 
 * in the SauceDemo web application. It provides methods to interact with 
 * and automate user actions on the checkout form, including entering user 
 * information, selecting items, and navigating through the checkout process.
 *
 * <p>
 * This class utilizes the Page Object Model (POM) design pattern to separate 
 * the representation of the page and its functionality. It relies on Selenium 
 * WebDriver for interacting with web elements and TestNG assertions for 
 * validation.
 * </p>
 *
 * <h3>Key Features:</h3>
 * <ul>
 *   <li>Provides methods to cancel the checkout process.</li>
 *   <li>Automates the process of adding items to the cart and proceeding with checkout.</li>
 *   <li>Includes explicit waits for handling dynamic loading of elements.</li>
 * </ul>
 *
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li>{@link org.openqa.selenium.WebDriver} for browser automation.</li>
 *   <li>{@link org.openqa.selenium.WebElement} for interacting with page elements.</li>
 *   <li>{@link org.openqa.selenium.support.FindBy} for locating elements.</li>
 *   <li>{@link com.saucedemo.utilities.WaitsFactory} for managing explicit waits.</li>
 *   <li>TestNG for assertions.</li>
 *   <li>Log4j for logging operations.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <pre>{@code
 * WebDriver driver = new ChromeDriver();
 * CheckoutInfo checkoutInfo = new CheckoutInfo(driver);
 * checkoutInfo.checkoutUserInfo("John", "Doe", "12345");
 * }</pre>
 *
 * @see com.saucedemo.utilities.WaitsFactory
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.support.PageFactory
 * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
 */

public class CheckoutInfo {

    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement txtFirstname;

    @FindBy(id = "last-name")
    WebElement txtLastname;

    @FindBy(id = "postal-code")
    WebElement txtPostalCode;

    @FindBy(xpath = "//a[normalize-space()='CANCEL']")
    WebElement btnCancel;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    WebElement btnContinue;

    @FindBy(xpath = "//*[name()='path' and contains(@fill,'currentCol')]")
    WebElement btnCart;

    @FindBy(xpath = "//div[@class='inventory_list']//div[1]//div[3]//button[1]")
    WebElement btnBackpack;

    @FindBy(xpath = "//body//div[@id='page_wrapper']//div[@id='inventory_container']//div[@id='inventory_container']//div[2]//div[3]//button[1]")
    WebElement btnBikeLight;

    @FindBy(xpath = "//div[3]//div[3]//button[1]")
    WebElement btnBoltTShirt;

    @FindBy(xpath = "//a[@class='btn_action checkout_button']")
    WebElement btnCheckout;

    WaitsFactory waitsFactory;

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public CheckoutInfo(WebDriver driver) {
        logger.info("**** Executing constructor for Checkout class ****");
        this.driver = driver;
        waitsFactory = new WaitsFactory(driver);
        PageFactory.initElements(driver, this);
    }

    public void cancelCheckoutUserInfo() {
        driver.navigate().back();
        waitsFactory.explicitWait(btnCancel);
        btnCancel.click();
        boolean btnContinueShoppintIsDisplayed = driver.findElement(By.xpath("//a[@class='btn_secondary']"))
                .isDisplayed();
        boolean btnCheckoutIsDisplayed = driver.findElement(By.xpath("//a[@class='btn_action checkout_button']"))
                .isDisplayed();
        Assert.assertTrue(btnContinueShoppintIsDisplayed && btnCheckoutIsDisplayed,
                "Cart items information page displayed");
    }

    public void checkoutUserInfo(String name, String surname, String postalCode) {
        btnBackpack.click();
        btnBikeLight.click();
        btnBoltTShirt.click();
        btnCart.click();
        btnCheckout.click();
        waitsFactory.explicitWait(txtFirstname);
        waitsFactory.explicitWait(btnContinue);
        txtFirstname.sendKeys(name);
        txtLastname.sendKeys(surname);
        txtPostalCode.sendKeys(postalCode);
        btnContinue.click();
        Assert.assertTrue(
                driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/v1/checkout-step-two.html"));
    }
}