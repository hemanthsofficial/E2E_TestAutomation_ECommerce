package com.qa.genericscripts;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;

public class Proxy_customize {

	public static void main(String[] args) {
		// proxy
		BrowserMobProxyServer proxy = new BrowserMobProxyServer();
		proxy.start(0);
		System.out.println("active port - " + proxy.getPort());
		proxy.newHar();
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

		// chromeoptions
		ChromeOptions option = new ChromeOptions();
		option.setProxy(seleniumProxy);

		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(option);

		// actions
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		System.out.println("Captured HAR: " + proxy.getHar().toString());
		proxy.stop();
		driver.close();
	}

}
