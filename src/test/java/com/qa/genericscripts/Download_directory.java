package com.qa.genericscripts;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Download_directory {

	public static void main(String[] args) {

		// chromeoptions
		ChromeOptions option = new ChromeOptions();
		Map<String, Object> dir = new HashMap<>();
		dir.put("download.default_directory", "C:\\Downloads");
		dir.put("download.prompt_for_download", false); // Disable download prompt
		dir.put("download.directory_upgrade", true);
		dir.put("safebrowsing.enabled", true); // Enable safe browsing for downloads
		option.setExperimentalOption("dir", dir);

		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(option);

		// actions
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.close();
	}

}
