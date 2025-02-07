package com.qa.genericscripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {

	@Test(dataProvider = "getData")
	public void passer(String name, String password) throws IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().window().maximize();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.id("exampleInputPassword1")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type = 'submit']")).click();
		File submit = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(submit, new File("D:\\TEST\\my-app\\target\\test-output\\screenshots\\"+name+" submission.png"));
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.id("exampleInputPassword1")).clear();
		File reset = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(reset,
				new File("D:\\\\TEST\\\\my-app\\\\target\\\\test-output\\\\screenshots\\\\"+name+" reset.png"));

		driver.close();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "hemanth";
		data[0][1] = "hemanth";
		data[1][0] = "sairam";
		data[1][1] = "sairam";

		return data;
	}

}
