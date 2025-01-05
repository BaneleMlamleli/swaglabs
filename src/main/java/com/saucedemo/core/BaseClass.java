package com.saucedemo.core;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.saucedemo.utilities.ConfigReader;

public class BaseClass {

    protected static WebDriver driver;

    final String BROWSER = new ConfigReader().getProperty("browser");
    final String URL = new ConfigReader().getProperty("baseUrl");

    public Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    @BeforeClass
    public void initBrowser() {
        logger.info("**** initiating browser in the initBrowser method ****");
        logger.info("Instance of driver in BaseClass: " + driver);
        logger.info("Browser: " + BROWSER);
        logger.info("url: " + URL);
        switch (BROWSER) {
            case "chrome":
                if (driver == null) {
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.get(URL);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }
                break;
            case "firefox":
                if (driver == null) {
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.get(URL);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }
                break;
            case "edge":
                if (driver == null) {
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    driver.get(URL);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }
                break;
            default:
                logger.error(
                        "Entered browser '" + BROWSER + "' is not configured in the method '" + new Object() {
                        }.getClass().getEnclosingMethod().getName() + "'");
                System.out.println(
                        "Entered browser '" + BROWSER + "' is not configured in the method '" + new Object() {
                        }.getClass().getEnclosingMethod().getName() + "'");
                break;
        }
    }

    @AfterClass
    public void terminateBrowser() {
        logger.info("**** tear down of browser in the terminateBrowser method ****");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
