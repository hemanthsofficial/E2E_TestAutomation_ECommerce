package com.qa.genericscripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scroll_operations {

	public static void main(String[] args) {
		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// actions
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement box = driver.findElement(By.id("autocomplete"));
		WebElement discount = driver.findElement(By.xpath("//a[text()='Discount Coupons']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		js.executeScript("arguments[0].scrollTop=500;", box);

		js.executeScript("arguments[0].scrollIntoView(true);", discount);
		driver.close();
	}

}
