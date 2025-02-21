package com.qa.pages;

import com.qa.utilities.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

public class CustomerLoginPages extends AbstractComponents {
    WebDriver remoteBot;

    public CustomerLoginPages(WebDriver bot) {
        super(bot);
        this.remoteBot = bot;
        PageFactory.initElements(remoteBot, this);
    }

    //loginAction
    @FindBy(id = "userEmail")
    WebElement userEmailTextBox;
    @FindBy(id = "userPassword")
    WebElement userPasswordTextBox;
    @FindBy(id = "login")
    WebElement loginButton;
    //forgotPasswordAction
    @FindBy(linkText = "Forgot password?")
    WebElement forgotPasswordLink;
    By changePasswordWindow = By.cssSelector("div.card.login-form div");
    @FindBy(xpath = "//input[@type='email']")
    WebElement existingUserEmailTextBox;
    @FindBy(css = "label[for='confirmPassword']")
    WebElement confirmPasswordTextLabel;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement savePasswordButton;

    public Boolean loginAction(String email, String oldPassword) throws InterruptedException {
        waitForElementToBeVisible(userEmailTextBox).sendKeys(email);
        waitForElementToBeVisible(userPasswordTextBox).sendKeys(oldPassword);
        waitForElementToBeVisible(loginButton).click();
        Thread.sleep(3000);
        return remoteBot.getCurrentUrl().equals("https://rahulshettyacademy.com/client/dashboard/dash");
    }

    public Boolean forgotPasswordAction(String email, String newPassword) {
        waitForElementToBeVisible(forgotPasswordLink).click();
        waitForElementToBeVisible(existingUserEmailTextBox).sendKeys(email);
        waitForElementToBeVisible(remoteBot.findElement(RelativeLocator.with(By.tagName("input")).above(confirmPasswordTextLabel))).sendKeys(newPassword);
        waitForElementToBeVisible(remoteBot.findElement(RelativeLocator.with(By.tagName("input")).below(confirmPasswordTextLabel))).sendKeys(newPassword);
        waitForElementToBeVisible(savePasswordButton).click();
        return remoteBot.getCurrentUrl().equals("https://rahulshettyacademy.com/client/auth/login");
    }
}