package org.anutestframework.pageobjects;

import org.anutestframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryDropdown;

    @FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
    WebElement countryItem;

    @FindBy(css = ".action__submit")
    WebElement submitButton;

    By dropdownResult= By.cssSelector(".ta-results");

    public void selectCountry(String country) throws InterruptedException {
        Actions action = new Actions(driver);
        action.sendKeys(countryDropdown, country).build().perform();
        waitForElementToAppearBy(dropdownResult);
        countryItem.click();
    }
    public ConfirmationPage submitOrder(){
        submitButton.click();
        return new ConfirmationPage(driver);
    }

}
