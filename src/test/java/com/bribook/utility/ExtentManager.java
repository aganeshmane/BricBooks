package com.bribook.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportPath;

    public static ExtentReports getInstance() {
        if (extent == null) {
            reportPath = generateReportFolder();

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath + "/ExtentReport.html");
            reporter.config().setReportName("BriBooks Automation Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "BriBooks");
            extent.setSystemInfo("Tester", "Ganesh");
        }
        return extent;
    }

    private static String generateReportFolder() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String path = "reports/" + timestamp;
        new File(path).mkdirs();
        return path;
    }
}
