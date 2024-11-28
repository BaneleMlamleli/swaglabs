package com.saucedemo.pages;

import com.saucedemo.utilities.WaitsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
 * @author Banele Mlamleli
 * @version 1.0
 * @see BrowserManager
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

    @FindBy(xpath = "//img[@class='bot_column']")
    public WebElement imgBotImage;

    @FindBy(xpath = "//div[@class='product_label']")
    public WebElement productsElement;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;

    WaitsFactory waitsFactory;

    Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public Login(WebDriver driver) {
        logger.info("**** executing constructor for Login class ****");
        this.driver = driver;
        waitsFactory = new WaitsFactory(driver);
        PageFactory.initElements(driver, this);
    }

    public void assertLoginPage() {
        logger.info("**** initiate assertLoginPage method ****");
        waitsFactory.explicitWait(imgLoginLogo);
        waitsFactory.explicitWait(txtPassword);
        waitsFactory.explicitWait(txtPassword);
        waitsFactory.explicitWait(btnLogon);
        Assert.assertTrue(imgLoginLogo.isDisplayed());
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    public void loginPage(String correctUsername, String correctPassword) {
        logger.info("**** initiate loginPage method ****");
        assertLoginPage();
        try {
            txtUsername.sendKeys(correctUsername);
            txtPassword.sendKeys(correctPassword);
            btnLogon.click();
        } catch (Exception e) {
            logger.error("'" + e.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }

    public String verifyLoginPage(String username, String password) {
        logger.info("**** initiate verifyLoginPage method ****");
        try {
            txtUsername.sendKeys(username);
            txtPassword.sendKeys(password);
            btnLogon.click();
        } catch (Exception e) {
            logger.error("'" + e.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
        return errorMessage.getText();
    }
}