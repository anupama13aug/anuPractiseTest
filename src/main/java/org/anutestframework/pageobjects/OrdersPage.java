package org.anutestframework.pageobjects;

import org.anutestframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public Boolean verifyOrderDisplay(String productName) {
        Boolean match = productNames.stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

}
