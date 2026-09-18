package org.anutestframework.AbstractComponents;

import org.anutestframework.pageobjects.CartPage;
import org.anutestframework.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartLink ;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersLink ;

    public AbstractComponent(WebDriver driver) {
    this.driver=driver;
    PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppearBy(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToDisappear() throws InterruptedException {

        Thread.sleep(1000);
      /*  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(element));*/
    }

    public CartPage goToCartPage(){
        cartLink.click();
        CartPage cartPage= new CartPage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage(){
        ordersLink.click();
        OrdersPage orderPage= new OrdersPage(driver);
        return orderPage;
    }
}

