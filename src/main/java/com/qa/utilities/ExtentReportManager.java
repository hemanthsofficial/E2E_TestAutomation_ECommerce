package com.qa.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExtentReportManager {
    WebDriver remoteBot;
    ExtentReports extentReports;
    ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public ExtentReportManager(WebDriver bot) {
        this.remoteBot = bot;
    }

    public ExtentReports getExtentReport() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("test-output/reports/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }

    public void createTest(String testName) {
        ExtentTest test = getExtentReport().createTest(testName);
        testThread.set(test);
    }

    public ExtentTest getTest() {
        return testThread.get();
    }

    public void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public String captureScreenshot(String testName) throws IOException {
        File srcFile = ((TakesScreenshot) remoteBot).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "test-output/screenshots" + testName + ".png";
        File destFile = new File(screenshotPath);
        Files.copy(srcFile.toPath(), destFile.toPath());
        return screenshotPath;
    }
}