package com.qa.genericscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cookies_handler {

	public static void main(String[] args) {
		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// actions
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().deleteCookieNamed("ad-privacy");
		// driver.manage().deleteAllCookies();
		driver.close();
	}

}
