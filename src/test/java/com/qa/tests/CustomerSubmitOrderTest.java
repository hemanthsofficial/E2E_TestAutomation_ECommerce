package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class CustomerSubmitOrderTest extends BaseTest {
    @Test(dataProvider = "getProductDetails")
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
        Assert.assertTrue(customerLoginPages.loginAction(getPropertyExtractor("email"), getPropertyExtractor("oldPassword")));
        customerAddToCartPages.addProductToCartAction(input.get("productName"));
        customerCartViewPages.checkOutAction(input.get("pageName"));
        customerPlaceOrderPages.placeOrderAction(getPropertyExtractor("cardNumber"), getPropertyExtractor("expiryMonth"), getPropertyExtractor("expiryYear"), getPropertyExtractor("cvv"), getPropertyExtractor("nameOnCard"), getPropertyExtractor("email"), getPropertyExtractor("country"));
    }

    @Test(dataProvider = "getOrderDetails", dependsOnMethods = {"submitOrder"})
    public void verifyOrder(String pageName) throws IOException, InterruptedException {
        Assert.assertTrue(customerLoginPages.loginAction(getPropertyExtractor("email"), getPropertyExtractor("oldPassword")));
        Assert.assertTrue(customerCartViewPages.verifyOrderAction(pageName));
    }

    @Test()
    public void changeUserPassword() throws IOException {
        Assert.assertTrue(customerLoginPages.forgotPasswordAction(getPropertyExtractor("email"), getPropertyExtractor("newPassword")));
    }

    @DataProvider
    public Object[][] getProductDetails() throws IOException {
        return new Object[][] {{getJsonDataToMap(getPropertyExtractor("jsonFilePath")).getFirst()}};
    }

    @DataProvider
    public Object[][] getOrderDetails() {
        Object[][] orderDetails = new Object[1][1];
        orderDetails[0][0] = "ORDERS";
        return orderDetails;
    }
}