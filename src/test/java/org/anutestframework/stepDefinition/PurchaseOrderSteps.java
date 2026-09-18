package org.anutestframework.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.anutestframework.TestComponents.BaseTest;
import org.anutestframework.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


public class PurchaseOrderSteps extends BaseTest {

    public LoginPage loginPage;
    public ProductCataloguePage productCataloguePage;
    public ConfirmationPage confirmationPage;


    @Given("Landed on Ecommerce page")
    public void landed_on_ecommerce_page() throws IOException {
        loginPage = launchApplication();
    }

    @Given("^Logged in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void logged_in_with_username_and_password(String username, String password) {
        productCataloguePage = loginPage.loginApplication(username, password);
    }

    @When("^Add \"([^\"]*)\" to the cart$")
    public void addProductToTheCart(String product) throws InterruptedException {
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(product);
    }

    @And("^checkout (.+) and submit the order$")
    public void checkoutAndSubmitTheOrder(String product) throws InterruptedException {
        CartPage cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(product);
        Assert.assertTrue(match);
        //Checkout Page Validation
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("validate {string} message is displayed")
    public void validateMessageDisplayed(String message) throws Throwable {
        String msg = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(msg.equalsIgnoreCase(message));
        driver.close();
    }

}
