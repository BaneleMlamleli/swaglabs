package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.utilities.WaitsFactory;

/**
 * The BurgerMenu class represents the burger menu in the e-commerce
 * application.
 * It provides methods to interact with the menu options and perform various
 * actions such as viewing all items, about page, logout, and reset app state
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

    WaitsFactory waitsFactory;

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public BurgerMenu(WebDriver driver) {
        logger.info("**** executing constructor for BurgerMenu class ****");
        this.driver = driver;
        waitsFactory = new WaitsFactory(this.driver);
        PageFactory.initElements(driver, this);
    }

    public boolean clickAllItems() {
        openMenu.click();
        waitsFactory.explicitWait(allItems);
        allItems.click();
        return driver.findElement(By.xpath("//div[@class='product_label']")).isDisplayed();
    }

    public String clickAbout() {
        openMenu.click();
        waitsFactory.explicitWaitButtonClickable(about);
        about.click();
        String currentUrl = driver.getCurrentUrl();
        driver.navigate().back();
        WebElement closeMenu = driver.findElement(By.xpath("//button[normalize-space()=\"Close Menu\"]"));
        if (closeMenu.isDisplayed()) {
            closeMenu.click();
        }
        return currentUrl;
    }

    public boolean clickResetAppState() {
        btnBackpack.click();
        boolean cartEmpty = driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']"))
                .isDisplayed();
        openMenu.click();
        resetAppState.click();
        WebElement closeMenu = driver.findElement(By.xpath("//button[normalize-space()=\"Close Menu\"]"));
        if (closeMenu.isDisplayed()) {
            closeMenu.click();
        }
        return cartEmpty;
    }

    public String clickLogout() {
        openMenu.click();
        waitsFactory.explicitWaitInvisibilityOfElement(logout);
        waitsFactory.explicitWait(logout);
        logout.click();
        return driver.getCurrentUrl();
    }
}