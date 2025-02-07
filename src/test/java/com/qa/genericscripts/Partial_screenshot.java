package com.qa.genericscripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Partial_screenshot {

	public static void main(String[] args) throws IOException {
		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// actions
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		WebElement header = driver.findElement(By.xpath("//div[@style='text-align: right;margin-top: -30px;']"));
		File file = header.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,
				new File("D:\\TEST\\my-app\\target\\test-output\\screenshots" + "\\resultOfPartialScreenshot.png"));
		driver.close();
	}

}
