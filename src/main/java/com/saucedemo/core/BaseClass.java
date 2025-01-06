package com.saucedemo.core;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.saucedemo.utilities.ConfigReader;

public class BaseClass {

    protected static WebDriver driver;
    protected ChromeOptions chromeOptions;
    protected FirefoxOptions firefoxOptions;
    protected EdgeOptions edgeOptions;

    final String BROWSER = new ConfigReader().getProperty("browser");
    final String URL = new ConfigReader().getProperty("baseUrl");

    public Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    @BeforeClass
    public void initBrowser() {
        logger.info("**** initiating browser in the initBrowser method ****");
        logger.info("Browser: " + BROWSER);
        logger.info("url: " + URL);
        switch (BROWSER) {
            case "chrome":
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                if (driver == null) {
                    driver = new ChromeDriver(chromeOptions);
                    logger.info("Instance of driver in BaseClass: " + driver);
                    driver.manage().window().maximize();
                    driver.get(URL);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }
                break;
            case "firefox":
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                if (driver == null) {
                    driver = new FirefoxDriver(firefoxOptions);
                    logger.info("Instance of driver in BaseClass: " + driver);
                    driver.manage().window().maximize();
                    driver.get(URL);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }
                break;
            case "edge":
                edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                if (driver == null) {
                    driver = new EdgeDriver(edgeOptions);
                    logger.info("Instance of driver in BaseClass: " + driver);
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
