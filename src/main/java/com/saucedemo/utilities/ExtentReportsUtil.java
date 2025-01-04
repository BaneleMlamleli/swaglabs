package com.saucedemo.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucedemo.core.BaseClass;

public class ExtentReportsUtil extends BaseClass implements ITestListener {
    public ExtentSparkReporter extentSparkReporter; // UI of the report
    public ExtentReports extentReports; // populate common information on the report
    public ExtentTest extentTest; // create test case entries in the report and update status of the test methods

    public Logger logger = LogManager.getLogger(new Object() {
    }.getClass().getName());

    public void onStart(ITestContext context) {
        logger.info("**** Executing onStart method in the ExtentReportsUtil class ****");
        String timestamp = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss").format(new Date());
        extentSparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/swaglabs_reports_" + timestamp + ".html");
        extentSparkReporter.config().setDocumentTitle("Swaglabs automation reports");
        extentSparkReporter.config().setReportName("Functional Testing");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        // REPORTS PATH: /home/banele/Documents/Programming/Java/swaglabs
        extentReports.setSystemInfo("computer name", System.getProperty("user.name"));
        extentReports.setSystemInfo("browser", new ConfigReader().getProperty("browser"));
        extentReports.setSystemInfo("os", System.getProperty("os.name"));
        extentReports.setSystemInfo("jdk",
                System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version"));

    }

    public void onTestSuccess(ITestResult result) {
        logger.info("**** Executing onTestSuccess method in the ExtentReportsUtil class ****");
        extentTest = extentReports.createTest(result.getName()); // create a new entry in the report
        extentTest.log(Status.PASS, "Test case PASSED: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        logger.info("**** Executing onTestFailure method in the ExtentReportsUtil class ****");
        new ScreenshotUtil(driver).screenShot(result.getName());
        extentTest = extentReports.createTest(result.getName())
                .addScreenCaptureFromPath(
                        System.getProperty("user.dir") + "/reports/screenshot/" + result.getName() + ".png")
                .fail(MediaEntityBuilder
                        .createScreenCaptureFromPath(
                                (System.getProperty("user.dir") + "/reports/screenshot/" + result.getName() + ".png"))
                        .build());
        extentTest.log(Status.FAIL, "Test case FAILED: " + result.getName());
        extentTest.log(Status.FAIL, "Test case FAILED CAUSE: " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName()); // create a new entry in the report
        extentTest.log(Status.SKIP, "Test case SKIPPED: " + result.getName());
    }

    public void onFinish(ITestContext context) {
        logger.info("**** Executing onFinish method in the ExtentReportsUtil class ****");
        extentReports.flush();
    }
}
