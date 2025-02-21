package com.qa.pages;

import com.qa.utilities.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CustomerRegisterPages extends AbstractComponents {
    WebDriver remoteBot;

    public CustomerRegisterPages(WebDriver bot) {
        super(bot);
        this.remoteBot = bot;
        PageFactory.initElements(remoteBot, this);
    }

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerPageNavigatorButton;
    @FindBy(id = "firstName")
    WebElement firstNameTextBox;
    @FindBy(id = "lastName")
    WebElement lastNameTextBox;
    @FindBy(id = "userEmail")
    WebElement userEmailTextBox;
    @FindBy(id = "userMobile")
    WebElement phoneNumberTextBox;
    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationDropDown;
    @FindBy(xpath = "//input[@type='radio']/following-sibling::span")
    List<WebElement> genderRadioButtons;
    @FindBy(xpath = "//input[@type='password']")
    List<WebElement> userPasswordTextBoxes;
    @FindBy(css = "input[type='checkbox']")
    WebElement ageRestrictionCheckBox;
    @FindBy(id = "login")
    WebElement registerButton;
    @FindBy(xpath = "//h1[text()='Account Created Successfully']")
    WebElement successfulAccountCreationMessage;
    @FindBy(id = "toast-container")
    WebElement statusPopupNotification;
    @FindBy(xpath = "//p[@class='login-wrapper-footer-text']/a")
    WebElement navigateToLoginLink;


    public void genderRadioButton(String gender) {
        genderRadioButtons.stream().filter(sex -> sex.getText().equals(gender)).findFirst().ifPresent(sex -> waitForElementToBeVisible(sex.findElement(By.xpath("./preceding-sibling::input[@type='radio']"))).click());
    }

    public void fillCustomerDetails(String firstName, String lastName, String email, String phoneNumber, String occupation, String gender, String password) {
        waitForElementToBeVisible(firstNameTextBox).sendKeys(firstName);
        waitForElementToBeVisible(lastNameTextBox).sendKeys(lastName);
        waitForElementToBeVisible(userEmailTextBox).sendKeys(email);
        waitForElementToBeVisible(phoneNumberTextBox).sendKeys(phoneNumber);
        Select customerOccupation = new Select(waitForElementToBeVisible(occupationDropDown));
        customerOccupation.selectByVisibleText(occupation);
        genderRadioButton(gender);
        userPasswordTextBoxes.forEach(pwd -> waitForElementToBeVisible(pwd).sendKeys(password));
        waitForElementToBeVisible(ageRestrictionCheckBox).click();
    }

    public Boolean customerRegistrationAction(String firstName, String lastName, String email, String phoneNumber, String occupation, String gender, String password) {
        waitForElementToBeVisible(registerPageNavigatorButton).click();
        fillCustomerDetails(firstName, lastName, email, phoneNumber, occupation, gender, password);
        waitForElementToBeVisible(registerButton).click();
        return successfulAccountCreationMessage.getText().equals("Account Created Successfully") && remoteBot.getCurrentUrl().equals("https://rahulshettyacademy.com/client/auth/register");
    }

    public Boolean customerNavigateToLoginAction(String firstName, String lastName, String email, String phoneNumber, String occupation, String gender, String password){
        waitForElementToBeVisible(registerPageNavigatorButton).click();
        fillCustomerDetails(firstName, lastName, email, phoneNumber, occupation, gender, password);
        waitForElementToBeVisible(registerButton).click();
        if (waitForElementToBeVisible(statusPopupNotification).isDisplayed()) {
            scrollToElement(navigateToLoginLink);
            waitForElementToBeVisible(navigateToLoginLink).click();
        }
        return remoteBot.getCurrentUrl().equals("https://rahulshettyacademy.com/client/auth/login");
    }
}