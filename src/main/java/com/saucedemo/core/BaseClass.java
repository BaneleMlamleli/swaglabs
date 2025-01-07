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
/**
 * The {@code BaseClass} serves as the foundation for test classes in the SauceDemo automation framework. 
 * It handles browser initialization and termination, providing a standardized setup and teardown process 
 * for all tests. This class supports multiple browsers (Chrome, Firefox, and Edge) and employs a headless 
 * configuration for efficient execution.
 *
 * <p>
 * Key features include:
 * </p>
 * <ul>
 *   <li>Dynamic browser selection based on properties configured in {@link com.saucedemo.utilities.ConfigReader}.</li>
 *   <li>Headless mode setup for Chrome, Firefox, and Edge for faster test execution.</li>
 *   <li>Implicit wait configuration for synchronization.</li>
 *   <li>Global WebDriver management to ensure a single driver instance is reused across tests.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <ul>
 *   <li>Annotate test classes with {@code @BeforeClass} and {@code @AfterClass} to invoke browser setup and teardown.</li>
 *   <li>Extend this class in test-specific classes to access the shared {@code WebDriver} instance.</li>
 * </ul>
 *
 * <h3>Dependencies:</h3>
 * <ul>
 *   <li>Selenium WebDriver for browser automation.</li>
 *   <li>TestNG for lifecycle annotations and assertions.</li>
 *   <li>Log4j for logging operations.</li>
 *   <li>{@link com.saucedemo.utilities.ConfigReader} for reading browser and URL configurations.</li>
 * </ul>
 *
 * <h3>Example:</h3>
 * <pre>{@code
 *
 * <h3>Browsers Supported:</h3>
 * <ul>
 *   <li>Chrome</li>
 *   <li>Firefox</li>
 *   <li>Edge</li>
 * </ul>
 *
 * @see com.saucedemo.utilities.ConfigReader
 * @see org.testng.annotations.BeforeClass
 * @see org.testng.annotations.AfterClass
* 
* @author Banele Mlamleli
* @version 1.0
* 
*/
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
