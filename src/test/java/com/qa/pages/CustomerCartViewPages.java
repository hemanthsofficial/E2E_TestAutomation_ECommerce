package com.qa.pages;

import com.qa.utility.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomerCartViewPages extends AbstractComponents {
    WebDriver remoteBot;
    CustomerAddToCartPages customerAddToCartPages;
    CustomerPlaceOrderPages customerPlaceOrderPages;

    public CustomerCartViewPages(WebDriver bot){
        super(bot);
        this.remoteBot = bot;
        customerAddToCartPages = new CustomerAddToCartPages(remoteBot);
        customerPlaceOrderPages = new CustomerPlaceOrderPages(remoteBot);
        PageFactory.initElements(remoteBot, this);
    }

    @FindBy(css = "div[class='cartSection'] h3")
    List<WebElement> addedProducts;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkOutButton;

    @FindBy(xpath = "//table/tbody/tr/th")
    WebElement displayedOrderID;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    WebElement displayedProductName;

    By addedProductsAppearance = By.cssSelector("div[class='cartSection'] h3");

    public List<WebElement> getAddedProductsList(){
        waitForElementsToBeVisible(addedProductsAppearance);
        return addedProducts;
    }

    public Boolean checkProductsAdded(String pageName){
        waitForElementToBeVisible(customerAddToCartPages.statusPopupNotification);
        waitForElementToBeInvisible(customerAddToCartPages.loadingAnimation);
        waitForElementToBeVisible(pageNavigator(pageName)).click();
        return getAddedProductsList().stream().anyMatch(product->product.getText().equals(customerAddToCartPages.productNameForFutureUse));
    }

    public void checkOutAction(String pageName) {
        if (checkProductsAdded(pageName)) {
            scrollToElement(checkOutButton);
            checkOutButton.click();
        }
    }

    public Boolean verifyOrderAction(String pageName) {
        waitForElementToBeVisible(pageNavigator(pageName)).click();
        return waitForElementToBeVisible(displayedOrderID).getText().equals(customerPlaceOrderPages.orderIdForFutureUse) && waitForElementToBeVisible(displayedOrderID).getText().equals(customerAddToCartPages.productNameForFutureUse);
    }
}