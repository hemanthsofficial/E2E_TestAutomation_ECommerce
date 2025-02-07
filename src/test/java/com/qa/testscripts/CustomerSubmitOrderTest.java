package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class CustomerSubmitOrderTest extends BaseTest {

    public String extractor(String key) throws IOException {
        Properties userConfig = new Properties();
        FileInputStream userConfigFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/qa/testdata/UserConfig.properties");
        userConfig.load(userConfigFile);
        return userConfig.getProperty(key);
    }

    @Test(dataProvider = "getProductDetails")
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
        Assert.assertTrue(customerLoginPages.loginAction(extractor("email"), extractor("oldPassword")));
        customerAddToCartPages.addProductToCartAction(input.get("productName"));
        customerCartViewPages.checkOutAction(input.get("pageName"));
        customerPlaceOrderPages.placeOrderAction(extractor("cardNumber"), extractor("expiryMonth"), extractor("expiryYear"), extractor("cvv"), extractor("nameOnCard"), extractor("email"), extractor("country"));
    }

    @Test(dataProvider = "getOrderDetails", dependsOnMethods = {"submitOrder"}, groups = {"favourite"})
    public void verifyOrder(String pageName) throws IOException, InterruptedException {
        Assert.assertTrue(customerLoginPages.loginAction(extractor("email"), extractor("oldPassword")));
        Assert.assertTrue(customerCartViewPages.verifyOrderAction(pageName));
    }

    @Test()
    public void changeUserPassword() throws IOException {
        Assert.assertTrue(customerLoginPages.forgotPasswordAction(extractor("email"), extractor("newPassword")));
    }

    @DataProvider
    public Object[][] getProductDetails() throws IOException {
        return new Object[][] {{getJsonDataToMap(extractor("jsonFilePath")).get(0)}};
    }

    @DataProvider
    public Object[][] getOrderDetails() {
        Object[][] orderDetails = new Object[1][1];
        orderDetails[0][0] = "ORDERS";
        return orderDetails;
    }
}