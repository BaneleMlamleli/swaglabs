package com.saucedemo.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public static Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public void screenShot(String failingMethod) {
        logger.info("**** Executing screenShot method in the ScreenshotUtil class ****");
        try {
            // To create reference of TakesScreenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            // Call method to capture screenshot
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            // Copy files to specific location
            // result.getName() will return name of test case so that screenshot name will
            // be same as test case name
            FileUtils.copyFile(src,
                    new File(System.getProperty("user.dir") + "/reports/screenshot/" + failingMethod + ".png"));
            logger.info("Successfully captured a screenshot");
        } catch (Exception e) {
            logger.error("**** Exception while taking screenshot ****");
            logger.error(e.getMessage());
        }
    }
}