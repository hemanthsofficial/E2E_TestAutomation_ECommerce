package com.qa.genericscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Relative_locators {

    public static void main(String[] args) {
        // browser
        System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // actions
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            driver.manage().window().maximize();

            WebElement radioButtonLabel = driver.findElement(By.xpath("//label[@for='radio1']"));
            System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(radioButtonLabel)).getText());

            System.out.println(driver.findElement(RelativeLocator.with(By.tagName("label")).below(radioButtonLabel)).getText());

            WebElement radioButtonHeading = driver.findElement(By.xpath("//legend[text()='Radio Button Example']"));
            System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).toRightOf(radioButtonHeading)).getText());

            WebElement suggestionClassHeading = driver.findElement(By.xpath("//legend[text()='Suggession Class Example']"));
            System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).toLeftOf(suggestionClassHeading)).getText());

            System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).near(radioButtonHeading)).getText());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {

            driver.close();
        }
    }
}
