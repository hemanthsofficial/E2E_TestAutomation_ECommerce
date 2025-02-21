package com.qa.pages;

import com.qa.utilities.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomerAddToCartPages extends AbstractComponents {
    WebDriver remoteBot;
    String productNameForFutureUse;

    public CustomerAddToCartPages(WebDriver bot){
        super(bot);
        this.remoteBot = bot;
        PageFactory.initElements(remoteBot, this);
    }
    @FindBy(css = ".card")
    List<WebElement> productsList;

    @FindBy(id = "toast-container")
    WebElement statusPopupNotification;

    @FindBy(css = ".ng-animating")
    WebElement loadingAnimation;

    By productListAppearance= By.cssSelector(".card");
    By addToCartButton = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductsList(){
        waitForElementsToBeVisible(productListAppearance);
        return productsList;
    }

    public WebElement getProductByName(String productName){
        return getProductsList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCartAction(String productName){
        productNameForFutureUse = productName;
        int retries = 0;
        while (retries < 3) {
            try{
                waitForElementToBeVisible(statusPopupNotification);
                waitForElementToBeInvisible(loadingAnimation);
                waitForElementToBeClickable(getProductByName(productName).findElement(addToCartButton)).click();
                break;
            }
            catch (StaleElementReferenceException e){
                System.out.println("StaleElementReferenceException caught. Retrying...");
                retries++;
            }
        }
    }
}