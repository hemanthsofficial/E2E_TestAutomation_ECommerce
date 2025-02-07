package com.qa.genericscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Certificate_handler {

	public static void main(String[] args) {
		// chromeoptions
		ChromeOptions option = new ChromeOptions();
		option.setAcceptInsecureCerts(true);

		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(option);

		// actions
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.close();
	}

}
