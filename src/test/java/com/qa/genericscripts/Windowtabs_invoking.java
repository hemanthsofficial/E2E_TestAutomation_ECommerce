package com.qa.genericscripts;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class Windowtabs_invoking {

	public static void main(String[] args) {
		//browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//actions
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		String parentWindow = iterator.next();
		String childWindow = iterator.next();
		driver.switchTo().window(childWindow);
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.switchTo().window(parentWindow);
		
		driver.quit();
	}

}
