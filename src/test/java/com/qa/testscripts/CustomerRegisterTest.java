package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomerRegisterTest extends BaseTest {

    @Test(dataProvider = "getDetails")
    public void newCustomerRegistration(String firstName, String lastName, String email, String phoneNumber, String occupation, String gender, String password){
        Assert.assertTrue(customerRegisterPages.customerRegistrationAction(firstName, lastName, email, phoneNumber, occupation, gender, password));
    }

    @Test(dataProvider = "getDetails")
    public void existingCustomerRegistration(String firstName, String lastName, String email, String phoneNumber, String occupation, String gender, String password){
        Assert.assertTrue(customerRegisterPages.customerNavigateToLoginAction(firstName, lastName, email, phoneNumber, occupation, gender, password));
    }

    @DataProvider
    public Object[][] getDetails(){
        Object[][] validDetails = new Object[1][7];
        validDetails[0][0] = "Khamzat";
        validDetails[0][1] = "Chimaev";
        validDetails[0][2] = "khamzatchimaev@gmail.com";
        validDetails[0][3] = "1234567890";
        validDetails[0][4] = "Engineer";
        validDetails[0][5] = "Female";
        validDetails[0][6] = "Khamzat@2021";
        return validDetails;
    }
}