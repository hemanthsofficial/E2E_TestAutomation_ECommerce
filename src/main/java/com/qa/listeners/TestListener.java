package com.qa.listeners;

import com.aventstack.extentreports.Status;
import com.qa.utilities.ExtentReportManager;
import com.qa.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener extends ExtentReportManager implements ITestListener {
    WebDriver remoteBot;

    public TestListener(WebDriver bot) {
        super(bot);
        this.remoteBot = bot;
    }

    @Override
    public void onStart(ITestContext context) {
        getExtentReport();
        Log.info("Test Execution Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        createTest(testName);
        Log.info("Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test Passed: " + result.getMethod().getMethodName());
        getTest().log(Status.PASS, "<span style='color:green; font-weight:bold;'>Test Passed</span>");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Test Failed: " + result.getMethod().getMethodName() + " - " + result.getThrowable());

        // Capture screenshot
        String screenshotPath = null;
        try {
            screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getTest().addScreenCaptureFromPath(screenshotPath);
        getTest().log(Status.FAIL, "<span style='color:red; font-weight:bold;'>Test Failed</span>");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("Test Skipped: " + result.getMethod().getMethodName());
        getTest().log(Status.SKIP, "<span style='color:blue; font-weight:bold;'>Test Skipped</span>");
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Test Execution Finished: " + context.getName());
        flushReport();
    }
}