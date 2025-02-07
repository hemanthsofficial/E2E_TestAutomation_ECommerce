package com.qa.genericscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Brokenlink_finder {
	    public static void main(String[] args) {
	        // Set up WebDriver
	    	WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

	        // Find all links on the webpage
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	        System.out.println("Total links found: " + links.size());

	        // Iterate through links and check status
	        for (WebElement link : links) {
	            String url = link.getAttribute("href");
	            if (url != null && !url.isEmpty()) {
	                checkBrokenLink(url);
	            } else {
	                System.out.println("Invalid link (no href): " + link.getText());
	            }
	        }

	        // Close browser
	        driver.quit();
	    }

	    public static void checkBrokenLink(String linkUrl) {
	        try {
	            URL url = new URL(linkUrl);
	            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	            httpURLConnection.setRequestMethod("HEAD"); // Faster than GET
	            httpURLConnection.connect();
	            
	            int responseCode = httpURLConnection.getResponseCode();
	            if (responseCode >= 400) {
	                System.out.println("❌ Broken link: " + linkUrl + " (Response: " + responseCode + ")");
	            } else {
	                System.out.println("✅ Valid link: " + linkUrl);
	            }
	        } catch (IOException e) {
	            System.out.println("❌ Error checking link: " + linkUrl);
	        }
	    }
}
