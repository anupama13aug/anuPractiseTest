package org.anutestframework.Tests;

import org.anutestframework.TestComponents.BaseTest;
import org.anutestframework.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData",groups= {"Purchase","Smoke"})

    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

        String country = "India";
//product catalogue page validation
        ProductCataloguePage productCataloguePage = loginPage.loginApplication(input.get("username"), input.get("password"));
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart( input.get("product"));
//Cart Page Validations
        CartPage cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay( input.get("product"));
        Assert.assertTrue(match);
//Checkout Page Validation
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry(country);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        //Validate the confirmation message.
        String msg = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    //to verify the product in order section of the application after placing the order.

    @Test(dependsOnMethods = {"submitOrder"},dataProvider="getData")
    public void orderHistoryTest(HashMap<String, String> input) throws InterruptedException {

        ProductCataloguePage productCataloguePage = loginPage.loginApplication(input.get("username"), input.get("password"));
        OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("product")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//org//anutestframework//Data//PurchaseOrder.json");
        return new Object[][] { {data.get(0)}, {data.get(1)} };
    }


    /*@DataProvider
    public Object[][] getData1() {
        return new Object[][]{{"anuTest@gmail.com", "Welcome@123", "ZARA COAT 3" },{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
    }*/

   /* @DataProvider
    public Object[][] getData() throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", "anuTest@gmail.com");
        map.put("password", "Welcome@123");
        map.put("product", "ZARA COAT 3");


        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("username", "shetty@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("product", "ADIDAS ORIGINAL");

        return new Object[][] { {map}, {map1} };

    }*/
}
