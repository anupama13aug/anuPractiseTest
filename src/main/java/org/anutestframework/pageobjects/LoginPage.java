package org.anutestframework.pageobjects;

import org.anutestframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail") WebElement userEmailInput ;

    @FindBy(id = "userPassword") WebElement passwordInput ;

    @FindBy(id = "login") WebElement loginBtn ;

    @FindBy(css = "[class*='flyInOut']") WebElement errorMessage ;

    public ProductCataloguePage loginApplication(String email, String password){
        userEmailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginBtn.click();
        return new ProductCataloguePage(driver);
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }
}
