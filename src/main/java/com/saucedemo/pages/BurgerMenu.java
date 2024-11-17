package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.WaitsFactory;

/**
 * The BurgerMenu class represents the burger menu in the e-commerce
 * application.
 * It provides methods to interact with the menu options and perform various
 * actions such as navigating to different pages, logging out, and resetting the
 * application state.
 * 
 * <p>
 * This class contains methods to perform the following actions:
 * </p>
 * <ul>
 * <li>{@link #login()}</li>
 * <li>{@link #clickAllItems()}</li>
 * <li>{@link #clickAbout()}</li>
 * <li>{@link #clickLogout()}</li>
 * <li>{@link #clickResetAppState()}</li>
 * </ul>
 * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
 */
public class BurgerMenu {

    WebDriver driver;

    @FindBy(xpath = "//button[normalize-space()='Open Menu']")
    protected WebElement openMenu;

    @FindBy(id = "inventory_sidebar_link")
    protected WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    protected WebElement about;

    @FindBy(id = "logout_sidebar_link")
    protected WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    protected WebElement resetAppState;

    @FindBy(xpath = "//div[@class='inventory_list']//div[1]//div[3]//button[1]")
    protected WebElement btnBackpack;

    @FindBy(id = "user-name")
    protected WebElement txtUsername;

    @FindBy(id = "password")
    protected WebElement txtPassword;

    @FindBy(id = "login-button")
    protected WebElement btnLogon;

    public String username = new ConfigReader().getProperty("username");
    public String password = new ConfigReader().getProperty("password");

    WaitsFactory waitsFactory = new WaitsFactory();

    public BurgerMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login() {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogon.click();
    }

    @Test(dependsOnMethods = "login")
    public void clickAllItems() {
        waitsFactory.explicitWait(openMenu);
        openMenu.click();
        waitsFactory.explicitWait(allItems);
        allItems.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='product_label']")).isDisplayed(),
                "Products page not displayed");
    }

    @Test(dependsOnMethods = "login")
    public void clickAbout() {
        waitsFactory.explicitWait(openMenu);
        openMenu.click();
        waitsFactory.explicitWaitButtonClickable(about);
        about.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Test(dependsOnMethods = "login")
    public void clickLogout() {
        waitsFactory.explicitWait(openMenu);
        openMenu.click();
        waitsFactory.explicitWaitInvisibilityOfElement(logout);
        waitsFactory.explicitWait(logout);
        logout.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/index.html");
    }

    @Test(dependsOnMethods = "login")
    public void clickResetAppState() {
        waitsFactory.explicitWait(openMenu);
        openMenu.click();
        waitsFactory.explicitWait(resetAppState);
        waitsFactory.explicitWaitInvisibilityOfElement(btnBackpack);
        waitsFactory.explicitWaitButtonClickable(btnBackpack);
        btnBackpack.click();
        resetAppState.click();
        Assert.assertFalse(
                driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).isDisplayed());
    }
}