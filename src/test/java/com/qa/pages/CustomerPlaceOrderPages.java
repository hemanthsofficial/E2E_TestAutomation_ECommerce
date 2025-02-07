package com.qa.pages;

import com.qa.utility.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerPlaceOrderPages extends AbstractComponents {
    WebDriver remoteBot;
    String orderIdForFutureUse;

    public CustomerPlaceOrderPages(WebDriver bot){
        super(bot);
        this.remoteBot = bot;
        PageFactory.initElements(remoteBot, this);
    }

    @FindBy(xpath = "//div[@class='form__cc']/div[1]/div/input")
    WebElement cardNumberTextBox;

    @FindBy(css = "body app-root select:nth-child(2)")
    WebElement getExpiryMonth;

    @FindBy(css = "body app-root select:nth-child(3)")
    WebElement getExpiryYear;

    @FindBy(xpath = "//div[@class='form__cc']/div[2]/div[2]/input")
    WebElement cvvTextBox;

    @FindBy(xpath = "//div[@class='form__cc']/div[3]/div/input")
    WebElement nameOnCardTextBox;

    @FindBy(xpath = "//div[@class='details__user']/div/input")
    WebElement userEmailTextBox;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryDropDown;

    @FindBy(xpath = "//button/span[text()=' India']")
    WebElement countryDropDownResult;

    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    WebElement placeOrderButton;

    @FindBy(css = "h1.hero-primary")
    WebElement orderConfirmationMessage;

    @FindBy(css = ".ng-star-inserted [css=\"5\"]")
    WebElement generatedOrderID;

    By placeOrderAppearance = By.cssSelector("div.payment div");

    public void personalInformation(String cardNumber, String expiryMonth, String expiryYear, String cvv, String nameOnCard){
        waitForElementToBeVisible(cardNumberTextBox).sendKeys(cardNumber);
        Select month = new Select(waitForElementToBeVisible(getExpiryMonth));
        month.selectByVisibleText(expiryMonth);
        Select year = new Select(waitForElementToBeVisible(getExpiryYear));
        year.selectByVisibleText(expiryYear);
        waitForElementToBeVisible(cvvTextBox).sendKeys(cvv);
        waitForElementToBeVisible(nameOnCardTextBox).sendKeys(nameOnCard);
    }

    public void shippingInformation(String email, String countryName){
        Actions actions = new Actions(remoteBot);
        if(waitForElementToBeVisible(userEmailTextBox).getText().equals(email)){
            userEmailTextBox.clear();
            actions.sendKeys(userEmailTextBox, email);
        }
        actions.sendKeys(waitForElementToBeVisible(countryDropDown), countryName);
        waitForElementToBeVisible(countryDropDownResult).click();
        waitForElementToBeVisible(placeOrderButton).click();
    }

    public void orderIdExtractor(WebElement generatedOrderID){
        String[] orderIdAfterSplit = generatedOrderID.getText().split(" ");
        String orderIdForFutureUse = orderIdAfterSplit[2].trim();
    }

    public Boolean placeOrderAction(String cardNumber, String expiryMonth, String expiryYear, String cvv, String name, String email, String countryName){
        waitForElementsToBeVisible(placeOrderAppearance);
        personalInformation(cardNumber, expiryMonth, expiryYear, cvv, name);
        shippingInformation(email, countryName);
        orderIdExtractor(waitForElementToBeVisible(generatedOrderID));
        return waitForElementToBeVisible(orderConfirmationMessage).isDisplayed() && generatedOrderID.isDisplayed();
    }
}