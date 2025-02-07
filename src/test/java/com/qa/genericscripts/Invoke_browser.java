package com.qa.genericscripts;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Invoke_browser {

	public static void main(String[] args) {

		// scanner
		Scanner input = new Scanner(System.in);
		System.out.println("Which browser (chrome/edge/firefox) ?");
		String browser = input.nextLine();
		input.close();

		// browser
		WebDriver driver;
		if (browser.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.contentEquals("edge")) {
			System.setProperty("webdriver.edge.driver",
					"D:\\TEST\\driverbinaries\\edgedriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// actions
		driver.navigate().to("https://www.amazon.in/");
		driver.manage().window().fullscreen();
		System.out.println(driver.getTitle());
		driver.close();
	}

}
