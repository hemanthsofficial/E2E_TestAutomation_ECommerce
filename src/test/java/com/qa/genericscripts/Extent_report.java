package com.qa.genericscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Extent_report {
    ExtentReports extent;
    
    @BeforeTest
    public void config(){
        String path = System.getProperty("user.dir")+"/test-output/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("automation result");
        reporter.config().setDocumentTitle("execution results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("tester","hemanth");
    }

    @Test
    public void main(){
        WebDriver driver;
        extent.createTest("main");
        System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.getTitle());
        driver.close();
        extent.flush();
    }
}
