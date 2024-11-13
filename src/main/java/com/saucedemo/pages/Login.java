package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.utils.ConfigReader;
import com.ecommerce.utils.WaitsFactory;

/**
 * The Login class represents the login page of the e-commerce application.
 * It provides methods to interact with the login page elements and verify the
 * login functionality.
 * 
 * <p>
 * This class contains methods to assert the login page and perform the login
 * action:
 * </p>
 * <ul>
 * <li>{@link #assertLoginPage()}</li>
 * <li>{@link #loginPage()}</li>
 * </ul>
 * 
 * @see BrowserManager
 * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
 */
public class Login {

    WebDriver driver;

    @FindBy(id = "user-name")
    public WebElement txtUsername;

    @FindBy(id = "password")
    public WebElement txtPassword;

    @FindBy(id = "login-button")
    public WebElement btnLogon;

    @FindBy(xpath = "//div[@class='login_logo']")
    public WebElement imgLoginLogo;

    @FindBy(linkText = "//img[@class='bot_column']")
    public WebElement imgBotImage;

    @FindBy(xpath = "//div[@class='product_label']")
    public WebElement productsElement;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;

    // String correctUsername = new ConfigReader().getProperty("username");
    // String lockedOutUser = new ConfigReader().getProperty("locked_out_user");
    // String correctPassword = new ConfigReader().getProperty("password");
    // String incorrectPassword = "wrongpassword01";
    // String incorrectUsername = "wrongUsername";

    WaitsFactory waitsFactory = new WaitsFactory();

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void assertLoginPage() {
        waitsFactory.explicitWait(txtPassword);
        waitsFactory.explicitWait(txtPassword);
        waitsFactory.explicitWait(btnLogon);
        Assert.assertTrue(imgBotImage.isDisplayed());
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    public String loginPage(String correctUsername, String correctPassword) {
        logger.info("**** initiate loginPage method ****");
        try {
            txtUsername.sendKeys(correctUsername);
            txtPassword.sendKeys(correctPassword);
            btnLogon.click();
            // Assert/Check if the products page is displayed after successful login
            // Assert.assertTrue(productsElement.isDisplayed());
        } catch (Exception e) {
            logger.error("'" + e.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
        return errorMessage.getText();
    }

    public String verifyLoginPage(String username, String password, String errMsg) {
        try {
            loginPage(username, password);
        } catch (Exception e) {
            logger.error("'" + e.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }

    public void incorrectLoginDetails() {
        for (int i = 0; i <= 6; i++) {
            switch (i) {
                case 0:
                    verifyLoginPage(lockedOutUser, correctPassword,
                            "Epic sadface: Sorry, this user has been locked out.");
                    break;
                case 1:
                    verifyLoginPage(incorrectUsername, correctPassword,
                            "Epic sadface: Username and password do not match any user in this service");
                    break;
                case 2:
                    verifyLoginPage(correctUsername, incorrectPassword,
                            "Epic sadface: Username and password do not match any user in this service");
                    break;
                case 3:
                    verifyLoginPage(incorrectUsername, incorrectPassword,
                            "Epic sadface: Username and password do not match any user in this service");
                    break;
                case 4:
                    verifyLoginPage("", correctPassword, "Epic sadface: Username is required");
                    break;
                case 5:
                    verifyLoginPage(correctUsername, "", "Epic sadface: Password is required");
                    break;
                case 6:
                    verifyLoginPage("", "", "Epic sadface: Username is required");
                    break;
            }
        }
    }

}