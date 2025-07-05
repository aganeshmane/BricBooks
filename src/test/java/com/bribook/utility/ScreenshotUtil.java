package com.bribook.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destPath = "reports/screenshots/" + testName + "_" + timestamp + ".png";

            File destFile = new File(destPath);
            destFile.getParentFile().mkdirs(); 
            Files.copy(src.toPath(), destFile.toPath());

            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
