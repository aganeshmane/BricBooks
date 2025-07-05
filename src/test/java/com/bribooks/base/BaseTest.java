package com.bribooks.base;

import com.aventstack.extentreports.*;
import com.bribook.utility.ExtentManager;
import com.bribook.utility.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties testData;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void loadTestData() throws Exception {
        testData = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/testdata.properties");
        testData.load(fis);
    }

    @BeforeMethod
    public void setupBrowser(Method method) {
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        // ✅ Initialize ExtentTest for this test method
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void teardownBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.fail("Test failed: " + result.getThrowable());
            if (path != null) {
                test.addScreenCaptureFromPath(path);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped: " + result.getThrowable());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
