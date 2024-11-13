package com.saucedemo.utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.core.*;

/**
 * The WaitsFactory class extends BrowserManager to provide implicit and
 * explicit wait mechanisms.
 * These waits are used to synchronize the web driver with the web elements
 * during test automation.
 * 
 * <p>
 * This class contains methods to set implicit and explicit waits:
 * </p>
 * <ul>
 * <li>{@link #implicitWait()}</li>
 * <li>{@link #explicitWait(WebElement)}</li>
 * </ul>
 * 
 * @see BrowserManager
 * 
 * @author Banele Mlamleli
 * @version 1.0
 * 
 */
public class WaitsFactory extends ConfigBrowser {

    private static final Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public void implicitWait() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (NotFoundException nfe) {
            logger.error("'" + nfe.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        } catch (NoSuchElementException nse) {
            logger.error("'" + nse.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }

    public void explicitWait(WebElement webElement) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (NotFoundException nfe) {
            logger.error("'" + nfe.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        } catch (NoSuchElementException nse) {
            logger.error("'" + nse.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }

    public void explicitWaitButtonClickable(WebElement btnElement) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(btnElement));
        } catch (NotFoundException nfe) {
            logger.error("'" + nfe.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        } catch (NoSuchElementException nse) {
            logger.error("'" + nse.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }

    public void explicitWaitInvisibilityOfElement(WebElement webElement) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        } catch (NotFoundException nfe) {
            logger.error("'" + nfe.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        } catch (NoSuchElementException nse) {
            logger.error("'" + nse.getMessage() + "' in method '" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "'");
        }
    }
}
