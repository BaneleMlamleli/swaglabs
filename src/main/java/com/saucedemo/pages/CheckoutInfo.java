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
 * Checkout
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