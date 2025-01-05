package com.saucedemo.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void screenShot(String failedImageFile) {
        logger.info("**** Executing screenShot method in the ScreenshotUtil class ****");
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src,
                    new File(System.getProperty("user.dir") + "/reports/screenshot/" + failedImageFile + ".png"));
            logger.info("Successfully captured a screenshot");
        } catch (IOException ioe) {
            logger.error("**** IOException while taking screenshot ****");
            logger.error(ioe.getMessage());
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("**** Exception while taking screenshot ****");
            logger.error(e.getMessage());
        }
    }
}