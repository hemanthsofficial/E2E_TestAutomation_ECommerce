package com.qa.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AbstractComponents {
    WebDriver remoteBot;

    public AbstractComponents(WebDriver bot) {
        this.remoteBot = bot;
        PageFactory.initElements(remoteBot, this);
    }

    @FindBy(css = ".btn.btn-custom")
    List<WebElement> pageNavigationMenu;

    public WebElement pageNavigator(String pageName){
        return pageNavigationMenu.stream().filter(page->page.getText().contains(pageName)).findFirst().orElse(null);
    }

    public WebElement waitForElementToBeVisible(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(remoteBot, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            System.err.println("Element not visible: " + locator + ". Exception: " + e.getMessage());
            return null;
        }
    }

    public void waitForElementsToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(remoteBot, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) locator));
        } catch (Exception e) {
            System.err.println("Elements not visible: " + locator + ". Exception: " + e.getMessage());
        }
    }

    public WebElement waitForElementToBeClickable(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(remoteBot, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.err.println("Element not clickable: " + locator + ". Exception: " + e.getMessage());
            return null;
        }
    }

    public Boolean waitForElementToBeInvisible(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(remoteBot, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.invisibilityOf(locator));
        } catch (Exception e) {
            System.err.println("Element still visible: " + locator + ". Exception: " + e.getMessage());
            return false;
        }
    }

    public void scrollToElement(WebElement locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) remoteBot;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", locator);
    }

    public String captureScreenShot(String testCaseName) throws IOException {
        File source = ((TakesScreenshot) remoteBot).getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"//target//test-output//screenshots//"+testCaseName+".png");
        FileUtils.copyFile(source, target);
        return System.getProperty("user.dir")+"//target//test-output//screenshots//"+testCaseName+".png";
    }
}